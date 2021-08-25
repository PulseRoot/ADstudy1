package com.example.mylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate() 호출됨.",Toast.LENGTH_LONG).show();

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "onStart() 호출됨.",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "onStop() 호출됨.",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy() 호출됨.",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "onPause() 호출됨.",Toast.LENGTH_LONG).show();

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", "BTS");
        editor.commit(); //이 명령어를 입력해야 저장됨
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "onResume() 호출됨.",Toast.LENGTH_LONG).show();

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if(pref != null){
            String name =  pref.getString("name", "");
            Toast.makeText(this, "복귀된 이름 : "+ name,Toast.LENGTH_LONG).show();
        }
    }
}