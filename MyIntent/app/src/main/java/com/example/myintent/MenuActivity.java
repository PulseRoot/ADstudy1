package com.example.myintent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike");//해석하지 않고 인텐트를 통과시킴

                setResult(Activity.RESULT_OK, intent); //정보를 보냄

                finish(); //메뉴가 실행되면서 메인이 아래 깔리는데 메뉴를 종료함에 따라서 다시 메인이 보이게 만듦.
            }
        });
    }
}