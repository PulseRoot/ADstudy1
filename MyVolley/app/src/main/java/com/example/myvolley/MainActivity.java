package com.example.myvolley;

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
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
TextView textView;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.imageView);
        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendRequest();
            }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendImageRequest();
            }
        });

        if(AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

    }

    public void sendImageRequest(){   //이미지 뷰를 표시하기 위한 메서드로서 스레드와 인터넷을 써야하므로 클래스를 따로 지정해야함.
      String url = "https://movie-phinf.pstatic.net/20180131_133/1517364091525GaDWN_JPEG/movie_image.jpg?type=m427_320_2";

      ImageLoadTask task = new ImageLoadTask(url, imageView);
      task.execute();//실행
    }

    public void sendRequest(){
        String url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20120101";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답->"+response);

                        processResponse(response);
                        }
                    },
                    new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러->"+error.getMessage());
                        }
                    }
        ){
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

    public void processResponse(String response){
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(response, MovieList.class); //받은 데이터를 MovieList.class형태로 바꾼다.


    }

    public void println(String data){
        textView.append(data + "\n");
    }
}