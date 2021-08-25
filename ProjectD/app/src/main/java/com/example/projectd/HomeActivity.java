package com.example.projectd;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class HomeActivity extends Fragment {
    ViewGroup rootView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        int status = NetworkStatus.getConnectivityStatus(getContext());

        if(status == NetworkStatus.TYPE_MOBILE||status == NetworkStatus.TYPE_WIFI){
            Toast.makeText(getContext(), "인터넷 연결 양호", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(), "인터넷 연결 없음", Toast.LENGTH_LONG).show();
        }


        ViewPager pager = rootView.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(6);

        MoviePagerAdapter adapter = new MoviePagerAdapter(getChildFragmentManager());

        Movie1 movie1 = new Movie1();
        adapter.addItem(movie1);
        Movie2 movie2 = new Movie2();
        adapter.addItem(movie2);
        Movie3 movie3 = new Movie3();
        adapter.addItem(movie3);
        Movie4 movie4 = new Movie4();
        adapter.addItem(movie4);
        Movie5 movie5 = new Movie5();
        adapter.addItem(movie5);

        pager.setAdapter(adapter);

        return rootView;


    }


}
class MoviePagerAdapter extends FragmentStatePagerAdapter{

    ArrayList<Fragment> items = new ArrayList<Fragment>();

    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addItem(Fragment item){
        items.add(item);
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }
    @Override
    public int getCount() {
        return items.size();
    }

}
