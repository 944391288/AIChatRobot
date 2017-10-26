package com.example.angela.helloworld;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Message;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    EditText message;
    Button send;
    TextView display;
    ClientThread clientThread;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (EditText)findViewById(R.id.message);
        send = (Button)findViewById(R.id.send);
        display = (TextView)findViewById(R.id.display);

        handler =new Handler()
        {
            public void handleMessage(Message msg)
            {
                // 如果消息来自于子线程
                if (msg.what == 0x123)
                {
                    // 将读取的内容追加显示在文本框中
                    display.append("\n" + msg.obj.toString());
                }
            }
        };

        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try
                {
                    // 当用户按下按钮之后，将用户输入的数据封装成Message
                    // 然后发送给子线程Handler
                    // new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("正在发消息！").setPositiveButton("确定", null).show();
                    Message msg = new Message();
                    msg.what = 0x345;
                    msg.obj = message.getText().toString();
                    display.append("\n" + msg.obj.toString());
                    message.setText("");
                    clientThread.revHandler.sendMessage(msg);
                } catch (Exception e) {}
            }
        });
    }
}
