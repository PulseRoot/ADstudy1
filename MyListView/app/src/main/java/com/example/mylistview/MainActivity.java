package com.example.mylistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SingerAdapter adapter;

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       editText = (EditText)findViewById(R.id.editText);
       editText2 = (EditText)findViewById(R.id.editText2);

        ListView listView = (ListView) findViewById(R.id.listView);

       adapter = new SingerAdapter();
       adapter.addItem(new Singeritem("소녀시대", "010-2222-1111", R.drawable.icon1));
        adapter.addItem(new Singeritem("엑소", "010-2311-1111", R.drawable.icon2));
        adapter.addItem(new Singeritem("BTS", "010-2242-1231", R.drawable.icon3));
        adapter.addItem(new Singeritem("이주함", "010-2122-1551", R.drawable.icon4));
        adapter.addItem(new Singeritem("최용태", "010-2214-1452", R.drawable.icon5));

       listView.setAdapter(adapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Singeritem item =  (Singeritem)adapter.getItem(position);

               Toast.makeText(getApplicationContext(), "선택 : " + item.getName(), Toast.LENGTH_LONG).show();
           }
       });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String name = editText.getText().toString();
               String mobile = editText2.getText().toString();

               adapter.addItem(new Singeritem(name, mobile, R.drawable.ic_launcher_foreground));

               adapter.notifyDataSetChanged();//갱신
            }
        });

    }

    class SingerAdapter extends BaseAdapter{
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