package com.example.projectd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity1 extends AppCompatActivity {
    Button buy_button;
    Button good;
    Button notgood;
    Button facebook;
    private   TextView joa; //
    private   TextView notjoa; //
    private int a1; //
    private int a2; //
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
    ListAdapter adapter;
    String[] name = new String[50]; //초기값 ""
    String[] talk = new String[50];
    float[] starnum = new float[50];
    int num = 0;

    private String url_json;
    private String text_json;
    private ImageView imageView;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;



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
        setContentView(R.layout.movie1_main);

        imageView = (ImageView)findViewById(R.id.imageView1);
        textView = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.name);
        textView3 = (TextView)findViewById(R.id.date1);
        textView4 = (TextView)findViewById(R.id.genre1);

        if(AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        sendRequest();


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
        int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
        adapter = new ListAdapter();

        someview.setAdapter(adapter);

        buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == NetworkStatus.TYPE_MOBILE||status == NetworkStatus.TYPE_WIFI) {
                Toast.makeText(getApplicationContext(), "구매가 완료되었습니다.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "인터넷 연결 없음.", Toast.LENGTH_LONG).show();
                }

            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity1.class);
                startActivityForResult(intent, 101);
            }
        });

        allsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReadActivity1.class);
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
                if(status == NetworkStatus.TYPE_MOBILE||status == NetworkStatus.TYPE_WIFI) {
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
            }else{
                Toast.makeText(getApplicationContext(), "인터넷 연결 없음.", Toast.LENGTH_LONG).show();
            }
            }
        });
        notgood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(status == NetworkStatus.TYPE_MOBILE||status == NetworkStatus.TYPE_WIFI) {
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
        }else{
            Toast.makeText(getApplicationContext(), "인터넷 연결 없음.", Toast.LENGTH_LONG).show();
        }
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
    public void sendImageRequest(){//이미지 뷰를 표시하기 위한 메서드로서 스레드와 인터넷을 써야하므로 클래스를 따로 지정해야함.
        String url = url_json;
        ImageLoadTask task = new ImageLoadTask(url, imageView);
        task.execute();//실행
    }

    public void sendRequest(){
        String url = "http://boostcourse-appapi.connect.or.kr:10000/movie/readMovie?id=1";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                        Log.i("onResponse", "onResponse");
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("onErrorResponse", "onErrorResponse");
                    }
                }
        );
        request.setShouldCache(false); //false 로 설정해야 이전 결과가 있더라도 새로 응답해서 결과를 보여줌
        AppHelper.requestQueue.add(request);
    }

    public void processResponse(String response){
        try {
            String json = response.toString();
            Gson gson = new Gson();
            MovieInfo1 movieinfo = gson.fromJson(response, MovieInfo1.class);//받은 데이터를 MovieList.class형태로 바꾼다.
            List<MovieInfo_into1> getitems = movieinfo.getitems1();
            url_json = getitems.get(0).getThumb1().toString();
            text_json = getitems.get(0).getSynopsis1().toString();
            sendImageRequest();
            textView.setText(text_json);
            joa.setText(getitems.get(0).getLike1().toString());
            notjoa.setText(getitems.get(0).getDislike1().toString());
            textView2.setText(getitems.get(0).getTitle1().toString());
            textView3.setText(getitems.get(0).getDate1().toString());
            textView4.setText(getitems.get(0).getGenre1().toString());
            a1 = Integer.parseInt(getitems.get(0).getLike1().toString());
            a2 = Integer.parseInt(getitems.get(0).getDislike1().toString());
        } catch(
                Exception e)

        {
            e.printStackTrace();
        }
    }
}
