package com.example.myconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = NetworkStatus.getConnectivityStatus(getApplicationContext());

                if(status == NetworkStatus.TYPE_MOBILE){
                    textView.setText("모바일로 연결됨.");
                }else if(status == NetworkStatus.TYPE_WIFI) {
                    textView.setText("무선랜으로 연결됨");
                }else{
                    textView.setText("인터넷 연결 안됨.");
                }
            }
        });

    }
}