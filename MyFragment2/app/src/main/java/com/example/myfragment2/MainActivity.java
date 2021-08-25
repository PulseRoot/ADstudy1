package com.example.myfragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ListFragment fragment1;
    ViewerFragment fragment2;
    FragmentManager manager; // 프래그먼트는 뷰와는 다르게 선언을 하기 때문에 매니저 선언 후 findFragmentById로 찾아야함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        fragment1 = (ListFragment)manager.findFragmentById(R.id.listFragment);
        fragment2 = (ViewerFragment)manager.findFragmentById(R.id.viewerFragment);
    }

    public void onImageChange(int index){
      fragment2.setImage(index);
    }

}