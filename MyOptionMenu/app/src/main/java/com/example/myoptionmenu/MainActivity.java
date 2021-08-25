package com.example.myoptionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar abar = getSupportActionBar(); //액션바 안보이게하기
//        abar.hide();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 클릭", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_search:
                Toast.makeText(this, "검색 클릭", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_settings:
                Toast.makeText(this, "설정 클릭", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}