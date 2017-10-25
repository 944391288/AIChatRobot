import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class MyClient {
    private static ServerSocket myServer=null;
    private static Socket socket=null;
    private static BufferedOutputStream os=null;
    private static BufferedInputStream is=null;
    static BufferedReader br=null;
    static BufferedWriter bw=null;
    private static InetAddress clientAddr=null;

    public static void main(String[] args) throws IOException, InterruptedException {
        socket=new Socket("127.0.0.1",8989);
        is=new BufferedInputStream(socket.getInputStream());
        os=new BufferedOutputStream(socket.getOutputStream());
        br=new BufferedReader(new InputStreamReader(is));
        bw=new BufferedWriter(new OutputStreamWriter(os));
        System.out.println("\n已连接");
        int i=0;
        while(i<10){
            bw.write("clien");
            System.out.println("\n已发送");
            i++;
        }



    }
}
