package com.example.mycallintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editTextTextPersonName);
        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String receiver = editText.getText().toString();

             Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + receiver));
             startActivity(intent);

             Intent intent2 = new Intent();
                ComponentName name = new ComponentName("com.example.mycallintent", "com.example.mycallintent.MenuActivity");
                intent2.setComponent(name);
                startActivity(intent2);
            }
        });

    }
}