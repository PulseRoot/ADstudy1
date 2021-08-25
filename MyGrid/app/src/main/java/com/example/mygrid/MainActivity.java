package com.example.mygrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SingerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView)findViewById(R.id.gridView);

        adapter = new SingerAdapter();
        adapter.addItem(new Singeritem("소녀시대", "010-2222-1111", R.drawable.icon1));
        adapter.addItem(new Singeritem("엑소", "010-2311-1111", R.drawable.icon2));
        adapter.addItem(new Singeritem("BTS", "010-2242-1231", R.drawable.icon3));
        adapter.addItem(new Singeritem("이주함", "010-2122-1551", R.drawable.icon4));
        adapter.addItem(new Singeritem("최용태", "010-2214-1452", R.drawable.icon5));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Singeritem item =  (Singeritem)adapter.getItem(position);

                Toast.makeText(getApplicationContext(), "선택 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    class SingerAdapter extends BaseAdapter {
        ArrayList<Singeritem> items = new ArrayList<Singeritem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(Singeritem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingerItemView view = null;
            if(convertView == null){
                view = new SingerItemView(getApplicationContext());
            }else{
                view = (SingerItemView) convertView;
            }

            Singeritem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());
            return view;
        }
    }

}