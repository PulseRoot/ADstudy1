package com.example.myhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    String urlStr;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.textView);

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlStr = editText.getText().toString();


                RequestThread thread = new RequestThread();
                thread.start();
            }
        });
    }
    public void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data + "\n"); //스크롤 뷰에 텍스트 뷰 추가
            }
        });
    }
    class RequestThread extends Thread{
       public void run(){

          try {
              URL url = new URL(urlStr);

              HttpURLConnection conn =(HttpURLConnection)url.openConnection();
              if(conn != null){
                  conn.setConnectTimeout(10000); //10초 대기 후 응답없으면 끝내기
                  conn.setRequestMethod("GET"); //메서드는 GET방식으로 한다.
                  conn.setDoInput(true);
                  conn.setDoOutput(true); //서버와의 입력과 출력 허용

                  int resCode = conn.getResponseCode(); //연결
                  BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream()))); //데이터 들어오는 통로 생성
                  String line = null;

                  while(true){
                      line = reader.readLine();
                      if(line == null){
                          break;    //만약 다 읽었다면 브레이크
                      }
                      println(line); //텍스트가 있다면 출력

                  }
                  reader.close();
                  conn.disconnect();
              }
          }catch (Exception e){
              e.printStackTrace();
          }
       }
    }

}