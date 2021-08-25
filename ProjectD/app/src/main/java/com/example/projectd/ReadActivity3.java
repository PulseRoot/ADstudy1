package com.example.projectd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReadActivity3 extends AppCompatActivity {
    Button back;
    ListView someview;
    RatingBar star;
    TextView textView;
    TextView textView2;
    String id;
    String text;
    float rate;
    ListAdapter adapter =  new ListAdapter();
    String[] name = new String[50]; //초기값 ""
    String[] talk = new String[50];
    float[] starnum = new float[50];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);



        star = (RatingBar) findViewById(R.id.star);
        textView = (TextView) findViewById(R.id.textview);
        textView2 = (TextView) findViewById(R.id.textview2);
        back = (Button) findViewById(R.id.back);
        someview = (ListView) findViewById(R.id.someview);
//
        someview.setAdapter(adapter);

        Intent intent = getIntent();

        for(int i = 0; i < 50 ; i++){
            name[i] = intent.getStringExtra("id"+ i);
            talk[i] = intent.getStringExtra("text"+i);
            starnum[i] = intent.getFloatExtra("rate"+i, 0f);
            if(name[i] == null || talk[i] == ""){
                break;
            }
            adapter.addItem(new ListItem(name[i], talk[i], starnum[i], R.drawable.user1));
        }
        adapter.notifyDataSetChanged();

//        id = intent.getStringExtra("id");
//        text = intent.getStringExtra("text");
//        rate = intent.getFloatExtra("rate", 0f);





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });

    }

    class ListAdapter extends BaseAdapter {
        ArrayList<ListItem> items = new ArrayList<ListItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ListItem item) {
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
            ListItemView listItemView = null;

            if (convertView == null) {
                listItemView = new ListItemView(getApplicationContext());
            } else {
                listItemView = (ListItemView) convertView;
            }
            ListItem item = items.get(position);
            listItemView.setName(item.getName());
            listItemView.setTalk(item.getTalk());
            listItemView.setStarnum(item.getStarnum());
            listItemView.setImage(item.getRedId());
            return listItemView;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            id = data.getStringExtra("id");
            text = data.getStringExtra("text");
            rate = data.getFloatExtra("rate", 0f);

            adapter.addItem(new ListItem(id, text, rate, R.drawable.user1));
            adapter.notifyDataSetChanged();


        }
    }
}

