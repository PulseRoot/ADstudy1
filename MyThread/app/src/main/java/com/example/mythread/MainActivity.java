package com.example.mythread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView;
Button button;
Button button2;

ValueHandler handler = new ValueHandler(); //클래스용 핸들러

Handler handler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  BackgroundThread thread = new BackgroundThread();
//                  thread.start();          클래스를 만들어 사용할 수 있으나 실무에서는 사용하지 않는다.
                //대신 아래와 같은 방법을 사용한다.
                new Thread(new Runnable() {
                    boolean running = false;
                    int value = 0;
                    @Override
                    public void run() {
                        running =true;

                        while(running) {
                            value += 1;

                            handler2.post(new Runnable() { // 값을 던져줌
                                  public void run(){
                                      textView.setText("현재 값 : "+ value);
                                  }
                            });
                            try {           //1초마다 반복하도록 설정
                                Thread.sleep(1000);
                            }catch (Exception e){}
                        }
                    }
                }).start();
            }
        });
    }
    class BackgroundThread extends Thread{
        boolean running = false;
        int value = 0;

        public void run(){
            running =true;
           while(running){
               value += 1;

               Message message = handler.obtainMessage(); //message 객체 생성
               Bundle bundle = new Bundle();  //번들 생성
               bundle.putInt("value", value); // 번들에 값을 집어넣고
               message.setData(bundle); // 메세지에 번들값을 넣어줌
               handler.sendMessage(message); // 핸들러로 메세지 값을 전송

              try {           //1초마다 반복하도록 설정
                  Thread.sleep(1000);
              }catch (Exception e){}

           }
        }
    }
    class ValueHandler extends Handler{
        //메인 스레드가 아닌 스레드에서 위젯에 접근하기 위해 필요함 = 핸들러

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData(); //메세지로 도착한 값을 번들에 저장
            int value = bundle.getInt("value"); // 밸류라는 변수에 번들 값 저장
            textView.setText("현재 값 : "+ value); //텍스트 뷰의 값 설정
        }
    }
}