package com.example.mybroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity2 extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    EditText editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms2);

        editText = (EditText) findViewById(R.id.editText); //발신자
        editText2 = (EditText) findViewById(R.id.editText2); //시각
        editText3 = (EditText) findViewById(R.id.editText3); // 내용
        Button button = (Button) findViewById(R.id.button);//닫기 버튼
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);

        super.onNewIntent(intent);
    }

    private void processCommand(Intent intent){
           if(intent != null){
               String sender = intent.getStringExtra("sender");
               String contents = intent.getStringExtra("contents");
               String receivedDate = intent.getStringExtra("receivedDate");

               editText.setText(sender);
               editText3.setText(contents);
               editText2.setText(receivedDate);
           }
    }
}