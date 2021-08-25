package com.example.myframelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;

    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

    }
    public void onButton1Cilcked(View v){
       index += 1;
       if(index > 1){
           index = 0;
       }
       if(index == 0){
           imageView.setVisibility(View.VISIBLE);
           imageView2.setVisibility(View.INVISIBLE);
       }
        if(index == 1){
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
        }
    }
}