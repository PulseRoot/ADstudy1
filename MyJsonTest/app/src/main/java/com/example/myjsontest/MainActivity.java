package com.example.myjsontest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myjsontest.AppHelper;
import com.example.myjsontest.ImageLoadTask;
import com.example.myjsontest.MovieList;
import com.example.myjsontest.R;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    String url_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendImageRequest();
            }
        });

        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

    }

    public void sendImageRequest() {   //이미지 뷰를 표시하기 위한 메서드로서 스레드와 인터넷을 써야하므로 클래스를 따로 지정해야함.
        String url = url_image;

        ImageLoadTask task = new ImageLoadTask(url, imageView);
        task.execute();//실행
    }

    public void sendRequest() {
        String url = "http://boostcourse-appapi.connect.or.kr:10000/movie/readMovieList?type=1";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답->" + response);

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러->" + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //파라미터 불러오기
                return params;
            }
        };
        request.setShouldCache(false); //false 로 설정해야 이전 결과가 있더라도 새로 응답해서 결과를 보여줌
        AppHelper.requestQueue.add(request);
        println("요청 보냄.");
    }

    public void processResponse(String response) {

        try {
            String json = response.toString();
            Gson gson = new Gson();
            MovieList movieList = gson.fromJson(response, MovieList.class);//받은 데이터를 MovieList.class형태로 바꾼다.
            List<Result> getitems = movieList.getitems();
            url_image = getitems.get(0).getImage().toString();
            for (int i = 0; i < 5; i++) {
                println(getitems.get(i).getImage().toString());
            }
        } catch(
    Exception e)

    {
        e.printStackTrace();
    }

}



    public void println(String data){
        textView.append(data + "\n");
    }
}