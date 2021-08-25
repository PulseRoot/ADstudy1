package com.example.mybroadcastreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       int permissonCheck =  ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS); // 권한을 가졌는지 확인
       if(permissonCheck == PackageManager.PERMISSION_GRANTED){
           //권한이 주어져 있음
           Toast.makeText(this,"SMS수신권한 주어져 있음", Toast.LENGTH_LONG).show();
       }else{
           Toast.makeText(this,"SMS수신권한 없음", Toast.LENGTH_LONG).show();

           if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)){
               Toast.makeText(this,"SMS 권한 설명 필요함", Toast.LENGTH_LONG).show();
           }else{
               ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS}, 101);
           } //권한 부여 요청
       }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "SMS수신권한을 사용자가 승인함", Toast.LENGTH_LONG).show();
                    } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(this, "SMS수신권한 사용자가 거부함", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "SMS수신권한 승인 받지 못함", Toast.LENGTH_LONG).show();
                }
        }
    }
}