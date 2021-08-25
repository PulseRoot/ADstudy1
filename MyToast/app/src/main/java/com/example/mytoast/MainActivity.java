package com.example.mytoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               Toast toast = Toast.makeText(getApplicationContext(), "위치가 바뀐 토스트", Toast.LENGTH_LONG);
               toast.setGravity(Gravity.TOP|Gravity.LEFT, 200,200);
               toast.show();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               LayoutInflater inflater = getLayoutInflater();
               View layout = inflater.inflate(R.layout.toastborder, (ViewGroup)findViewById(R.id.toast_layout_root));

               TextView text = (TextView)layout.findViewById(R.id.text);
               text.setText("모양을 바꾼 토스트");

               Toast toast = new Toast(getApplicationContext());
               toast.setGravity(Gravity.CENTER, 0, -100);
               toast.getDuration();
               toast.setView(layout);

               toast.show();
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "스낵바입니다.", Snackbar.LENGTH_LONG).show();
            }
        });

    }
}