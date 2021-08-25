package com.example.projecta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void oneclicked1(View v){
        Toast.makeText(getApplicationContext(), "한줄평을 남겼습니다!!", Toast.LENGTH_LONG).show();
    }
    public void facebookclick(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com"));
        startActivity(intent);
    }
    public void buyticket(View v){
        Toast.makeText(getApplicationContext(), "감사합니다. 구매되었습니다.", Toast.LENGTH_LONG).show();
    }
}