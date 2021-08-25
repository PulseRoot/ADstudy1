package com.example.mybroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver {
    private  static final String TAG = "MyReceiver";

    private  static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //시간을 스트링형으로 변환
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceiver() 호출됨.");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle); //SMS데이터가 들어감

        if(messages.length >0){
            String sender = messages[0].getOriginatingAddress(); //발신자 주소
            Log.d(TAG, "sender : "+sender);

            String contents = messages[0].getMessageBody().toString(); // 메세지 내용
            Log.d(TAG, "contents : "+contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis()); //발신시간
            Log.d(TAG, "receivedDate: "+receivedDate);

            sendToActivity(context, sender, contents, receivedDate);
        }
    }

    private void sendToActivity(Context context, String sender, String contents, Date receiveDate){
        Intent intent = new Intent(context, SmsActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                Intent.FLAG_ACTIVITY_SINGLE_TOP|
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("sender", sender);
        intent.putExtra("contents", contents);
        intent.putExtra("receiveDate", format.format(receiveDate));

        context.startActivity(intent);
    }


    private SmsMessage[] parseSmsMessage(Bundle bundle){
         Object[] objs = (Object[])bundle.get("pdus");
         SmsMessage[] messages = new SmsMessage[objs.length];
         for(int i = 0 ; i < objs.length ; i++){
             if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  // 마쉬멜로 이상버전
                 String format = bundle.getString("format");
                 messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
             }else{                                                // 마쉬멜로 이하버전
                 messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
             }
         }
         return messages;
    }

}