package com.example.myprojectb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buy_button;
    Button good;
    Button notgood;
    Button facebook;
    TextView joa;
    TextView notjoa;
    int a1 = 121;
    int a2 = 22;
    Button enter;
    Button write;
    EditText editText;
    EditText editText2;
    RatingBar star;
    Button allsee;
    ListView someview;
    String id;
    String text;
    float rate;
    MainActivity.ListAdapter adapter;
    String[] name = new String[50]; //초기값 ""
    String[] talk = new String[50];
    float[] starnum = new float[50];
    int num = 0;


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        buy_button = (Button) findViewById(R.id.buy_button);
        good = (Button) findViewById(R.id.good);
        notgood = (Button) findViewById(R.id.notgood);
        facebook = (Button) findViewById(R.id.facebook);
        joa = (TextView) findViewById(R.id.goodtext);
        notjoa = (TextView) findViewById(R.id.notgoodtext);
        enter = (Button) findViewById(R.id.enter);
        write = (Button) findViewById(R.id.write);
        editText = (EditText) findViewById(R.id.edittext);
        editText2 = (EditText) findViewById(R.id.edittext2);
        star = (RatingBar) findViewById(R.id.star);
        allsee = (Button) findViewById(R.id.allsee);
        someview = (ListView)findViewById(R.id.someview);

        adapter = new ListAdapter();

        someview.setAdapter(adapter);

        buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "구매가 완료되었습니다.", Toast.LENGTH_LONG).show();

            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivityForResult(intent, 101);
            }
        });

        allsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReadActivity.class);
                for(int i = 0; i < num ; i++) {
                    intent.putExtra("id"+ i, name[i]);
                    intent.putExtra("text"+ i, talk[i]);
                    intent.putExtra("rate"+ i, starnum[i]);
                }
                startActivity(intent);
            }
        });

        joa.setText("" + a1); //""를 넣는 이유는 문자열로 인식 시켜서 출력하기 위해
        notjoa.setText("" + a2);
        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notgood.isSelected()) {
                    good.setSelected(true);
                    notgood.setSelected(false);
                    a1 = a1 + 1;
                    a2 = a2 - 1;
                    joa.setText("" + a1);
                    notjoa.setText("" + a2);
                } else if (good.isSelected()) {
                    good.setSelected(false);
                    a1 = a1 - 1;
                    joa.setText("" + a1);
                } else {
                    good.setSelected(true);
                    a1 = a1 + 1;
                    joa.setText("" + a1);
                }
                Toast.makeText(getApplicationContext(), "좋아요!", Toast.LENGTH_LONG).show();

            }
        });
        notgood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (good.isSelected()) {
                    notgood.setSelected(true);
                    good.setSelected(false);
                    a1 = a1 - 1;
                    joa.setText("" + a1);
                    a2 = a2 + 1;
                    notjoa.setText("" + a2);
                } else if (notgood.isSelected()) {
                    notgood.setSelected(false);
                    a2 = a2 - 1;
                    notjoa.setText("" + a2);
                } else {
                    notgood.setSelected(true);
                    a2 = a2 + 1;
                    notjoa.setText("" + a2);
                }
                Toast.makeText(getApplicationContext(), "싫어요!", Toast.LENGTH_LONG).show();

            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.facebook.com"));
                startActivity(intent);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
             id = data.getStringExtra("id");
             text = data.getStringExtra("text");
             rate = data.getFloatExtra("rate", 0f);

             name[num] = id;
             talk[num] = text;
             starnum[num] = rate;
             num = num + 1;

             adapter.addItem(new ListItem(id, text, rate, R.drawable.user1));
             adapter.notifyDataSetChanged();
        }
    }

}
