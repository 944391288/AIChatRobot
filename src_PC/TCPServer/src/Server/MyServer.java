package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Bean.*;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import util.CommandTransfer;


public class MyServer {

    private static ExecutorService myThreadPool = Executors.newCachedThreadPool();
    private static DBServer dbserver=null;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private final static int PORT = 8989;

    public static void main(String[] args) throws IOException {
        MyServer server = new MyServer();
        server.serverSocket = new ServerSocket(PORT);
        dbserver=new DBServer();
        server.listen();    //开始监听，每监听到一个连接，创建一个线程处理相关
    }

    public void listen() throws IOException {
        while (true) {
            socket = serverSocket.accept();
            System.out.println("A new client connect\n");
            myThreadPool.execute(() -> {
                try {
                    handleSocket(socket);
                } catch (IOException e) {
                    System.out.println("\n Error: "+e.getMessage());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void handleSocket(Socket clientSocket) throws IOException, SQLException, ClassNotFoundException {
        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());

        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(output));
        String msg;
        while (true){
            CommandTransfer comTrans=null;
            comTrans= (CommandTransfer) ois.readObject();
            switch (comTrans.getCmd()){
                case "talk":   //接收的数据属于对话
                    msg=comTrans.getData().toString();
//                    List<Term> words=ToAnalysis.parse(msg).getTerms();
                    System.out.println(msg);
                    String sql="select Qid,Qanswer from question where Qkey = '"+msg+"'";
                    ResultSet rs=dbserver.executeQuery(sql);

                    int col=rs.getMetaData().getColumnCount();
                    while(rs.next()){
                        for(int i=1;i<=col;i++){
                            comTrans.setResult(rs.getString(i));
                            oos.writeObject(comTrans);
                        }
                    }
                    break;
                case "login":
                    break;
                default:
                    break;
            }

        }
    }

}
