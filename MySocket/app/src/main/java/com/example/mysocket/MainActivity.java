package com.example.mysocket;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView textview;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        textview = (TextView)findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             ClientThread thread = new ClientThread();
             thread.start();
            }
        });
    }
    class ClientThread extends Thread{
        public void run(){
          String host = "localhost"; //ip지정
          int port = 5001;//서버의 포트와 동일하게 설정해야 함.
          try {
              Socket socket = new Socket(host, port); //소켓 객체 생성

            ObjectOutputStream outstream  = new ObjectOutputStream(socket.getOutputStream());//data 보내기 위한 통로 설정
            outstream.writeObject("안녕!");
            outstream.flush();
            Log.d("ClientThread", "서버로 보냄.");

            ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
            final Object input = (String) instream.readObject();
            Log.d("ClientThread", "받은 데이터 = "+input);

            //스레드이기 때문에 핸들러를 통해서 접근해야한다.
            handler.post(new Runnable(){
                public void run() {
                    textview.setText("받은 데이터 : " + input);
                }
            });


          }catch(Exception e){
              e.printStackTrace();
          }
        }
    }
}