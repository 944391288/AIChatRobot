package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Bean.*;


public class MyServer {

    private static ExecutorService myThreadPool = Executors.newCachedThreadPool();
    private static DBServer dbserver=null;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private final static int PORT = 8989;
    private User user=null;

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
                    handleSocket(socket,user);
                } catch (IOException e) {
                    System.out.println("\n Error: "+e.getMessage());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void handleSocket(Socket clientSocket,User user) throws IOException, SQLException {
        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(output));
        String msg;
        while (true){
            msg=input.readUTF().toString();

            String sql="select Qanswer from question where Qkey = '"+msg+"'";

            System.out.println(sql);
            ResultSet rs=dbserver.executeQuery(sql);
            int col=rs.getMetaData().getColumnCount();
            while(rs.next()){
                for(int i=1;i<=col;i++){
                    output.writeUTF(rs.getString(i));
                }
                System.out.println();
            }


        }
    }

}
