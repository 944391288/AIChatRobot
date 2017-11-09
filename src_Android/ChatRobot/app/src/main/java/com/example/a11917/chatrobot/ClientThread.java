package com.example.a11917.chatrobot;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.lang.Runnable;

import util.CommandTransfer;

/**
 * Created by Angela on 2017/10/14.
 */

public class ClientThread implements Runnable{
    private Socket s;
    // 定义向UI线程发送消息的Handler对象
    Handler handler;
    // 定义接收UI线程的Handler对象
    Handler revHandler;
    // 该线程处理Socket所对用的输入输出流
    BufferedReader br = null;
    DataOutputStream os=null;
    DataInputStream is=null;
    ObjectInputStream ois=null;
    ObjectOutputStream oos=null;

    public ClientThread(Handler handler)
    {
        this.handler = handler;
    }

    public void run()
    {
        try
        {
            s=new Socket("192.168.199.129",8989);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = new DataOutputStream(s.getOutputStream());
            is=new DataInputStream(s.getInputStream());
            oos=new ObjectOutputStream(s.getOutputStream());
            ois=new ObjectInputStream(s.getInputStream());

            // 启动一条子线程来读取服务器相应的数据
            new Thread()
            {
                @Override
                public void run()
                {
//                    String content = null;
                    CommandTransfer comTransfer=null;
                    // 不断的读取Socket输入流的内容
                    try
                    {
                        while ((comTransfer = (CommandTransfer) ois.readObject()) != null)
                        {
                            // 每当读取到来自服务器的数据之后，发送的消息通知程序
                            // 界面显示该数据
                            Message msg = new Message();
                            msg.what = 0x123;
                            msg.obj = comTransfer.getResult().toString();
                            handler.sendMessage(msg);
                        }
                    }
                    catch (IOException io)
                    {
                        io.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            // 为当前线程初始化Looper
            Looper.prepare();
            // 创建revHandler对象
            revHandler = new Handler() {
                @Override
                public void handleMessage(Message msg)
                {
                    // 接收到UI线程的中用户输入的数据
                    if (msg.what == 0x345)
                    {
                        // 将用户在文本框输入的内容写入网络
                        try
                        {
                            CommandTransfer comTransfer=new CommandTransfer();
                            comTransfer.setCmd("talk");
                            comTransfer.setData(msg.obj.toString());
                            oos.writeObject(comTransfer);
//                            os.writeUTF(msg.obj.toString());
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            };
            // 启动Looper
            Looper.loop();
        }
        catch (SocketTimeoutException e)
        {
            Message msg = new Message();
            msg.what = 0x123;
            msg.obj = "网络连接超时！";
            handler.sendMessage(msg);
        }
        catch (IOException io)
        {
            io.printStackTrace();
        }
    }
}
