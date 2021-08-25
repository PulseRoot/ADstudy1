package com.example.myserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ServerThread thread = new ServerThread();
//                thread.start();

                Intent intent = new Intent(getApplicationContext(), ChatService.class); // 주석으로 실행하던 것을
                //따로 자바 서비스 클래스를 생성하여 거기서 실행하도록 함.
                startService(intent);
            }
        });
    }
//    class ServerThread extends Thread{
//        public void run(){
//          int port = 5001;
//          try {
//              ServerSocket server = new ServerSocket(port);
//              Log.d("ServerThread", "서버가 실행됨");
//
//              while(true){
//                 Socket socket = server.accept(); //대기 상태
//
//                 ObjectInputStream instream = new ObjectInputStream(socket.getInputStream()); //들어오는 데이터 처리
//                 Object input = instream.readObject();
//                 Log.d("ServerThread", "input = "+input);
//
//                 ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
//                 outstream.writeObject(input + " from server.");
//                 outstream.flush(); //버퍼에 남아있을 수도 있기 떄문에 삭제
//                 Log.d("ServerThread", "output 보냄.");
//
//                 socket.close();// 연결해제, 리소스를 아끼기 위해
//              }
//          }catch (Exception e){
//              e.printStackTrace();
//          }
//        }
//    }
}