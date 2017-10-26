package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Bean.User;

public class MyServer {

    private static ExecutorService myThreadPool = Executors.newCachedThreadPool();
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private final static int PORT = 8989;

    public static void main(String[] args) throws IOException {
        MyServer server = new MyServer();
        server.serverSocket = new ServerSocket(PORT);
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
                }
            });
        }
    }

    public void handleSocket(Socket clientSocket) throws IOException {
        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

        String msg=null;
        while ((msg=input.readUTF().toString())!=null){
            System.out.println(msg);
        }
    }

}
