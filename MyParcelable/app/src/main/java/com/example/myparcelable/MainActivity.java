package com.example.myparcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

                ArrayList<String> names = new ArrayList();
                names.add("이주함");
                names.add("이건우");

                intent.putExtra("names", names);

                SimpleData data = new SimpleData(100, "Hello");
                intent.putExtra("data", data);

                startActivityForResult(intent, 101);
            }
        });
    }
}