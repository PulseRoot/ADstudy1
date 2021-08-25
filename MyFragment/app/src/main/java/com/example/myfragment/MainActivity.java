package com.example.myfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MainFragment fragment1;
    MenuFragment fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new MainFragment();
        fragment2 = new MenuFragment();

        Button button = (Button)findViewById(R.id.button);
        Button button2 = (Button)findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                //프래그먼트 생성 및 추가 container는 프레임 레이아웃 id임
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
            }
        });
    }

    public void onFragmentChange(int index){
        if(index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        }else if(index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
        }
    }

}