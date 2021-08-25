package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate 호출됨"); //디버그 로그
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand 호출됨"); //디버그 로그 이 부분은 리턴으로 받기때문에 위에 지정해야함.
        //서비스는 한번 실행되면 계속 실행하기 때문에 intent와 같은 것은 onStartCommand에서 처리한다.
        if(intent == null){
            return Service.START_STICKY; // 서비스가 종료되어도 다시 자동으로 실행한다.
        }else{
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "전달받은 데이터 : "+ command +", " + name);

        try {
            Thread.sleep(5000); //5초동안 휴식
        }catch (Exception e){}

        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class); //반대로 서비스에서 메인으로 정보보내기
        //서비스 시작시 Task가 아니라도 문제를 발생시키지 않기 위해 Flag가 필요함.
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  //화면이 없는 곳에서 화면 띄우기 가능하도록
                Intent.FLAG_ACTIVITY_SINGLE_TOP| //만들어져 있는 액티비티라면 그걸 재활용하라
                Intent.FLAG_ACTIVITY_CLEAR_TOP); //혹시 그 위에 다른 화면이 있다면 그걸 지워줘라
        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name + " from Service.");

        startActivity(showIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy 호출됨"); //디버그 로그
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}