package com.example.myservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MyService.class);

                intent.putExtra("command", "www");
                intent.putExtra("name", name);

                startService(intent);
            }
        });

        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }

    @Override //맨위는 onCreate문이기 때문에 서비스에서 보낸 정보를 가져오려면 메서드를 생성해야한다.
    //혹은 onCreate에서 Intent 변수 = getIntent()를 통해 받아야한다.
    //그 후, 만들어둔 processCommand(변수)를 통해 출력한다.
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent intent) {
        if (intent != null) {
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "서비스로부터 전달받은 데이터" + command + ", " + name, Toast.LENGTH_LONG).show();
        }
    }
}