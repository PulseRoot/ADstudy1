package com.example.projectd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WriteActivity5 extends AppCompatActivity {
    Button write;
    ListView listView;
    EditText editText;
    EditText editText2;
    RatingBar star;
    Button back2;
    Button enter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        int status = NetworkStatus.getConnectivityStatus(getApplicationContext()); //추가
        write = (Button) findViewById(R.id.write);
        listView = (ListView) findViewById(R.id.someview);
        editText = (EditText) findViewById(R.id.edittext);
        editText2 = (EditText) findViewById(R.id.edittext2);
        star = (RatingBar) findViewById(R.id.star);
        back2 = (Button) findViewById(R.id.back2);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == NetworkStatus.TYPE_MOBILE||status == NetworkStatus.TYPE_WIFI) {
                Intent intent = new Intent();
                String id = editText2.getText().toString();
                String text = editText.getText().toString();
                float rate = star.getRating();

                    intent.putExtra("id", id);
                    intent.putExtra("text", text);
                    intent.putExtra("rate", rate);

                    setResult(RESULT_OK,intent);
                    Toast.makeText(getApplicationContext(), "한줄평을 작성했습니다.", Toast.LENGTH_LONG).show();
                    finish();
            }else{
                Toast.makeText(getApplicationContext(), "인터넷 연결 없음.", Toast.LENGTH_LONG).show();
            }
            }
        });
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}