package com.example.projectd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

public class Movie2 extends Fragment {
    private Button button_m2;
    private String url_json;
    private String text_json;
    private ImageView imageView;
    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView  = (ViewGroup)inflater.inflate(R.layout.movie2, container, false);

        button_m2 = rootView.findViewById(R.id.button_m2);
        imageView = rootView.findViewById(R.id.imageView2);
        textView = rootView.findViewById(R.id.textView2);
        if(AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getContext());
        }
        sendRequest();
        button_m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MovieActivity2.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
    public void sendImageRequest(){//이미지 뷰를 표시하기 위한 메서드로서 스레드와 인터넷을 써야하므로 클래스를 따로 지정해야함.
        String url = url_json;
        ImageLoadTask task = new ImageLoadTask(url, imageView);
        task.execute();//실행
    }

    public void sendRequest(){
        String url = "http://boostcourse-appapi.connect.or.kr:10000/movie/readMovieList?type=1";

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
            MovieList movieList = gson.fromJson(response, MovieList.class);//받은 데이터를 MovieList.class형태로 바꾼다.
            List<Result> getitems = movieList.getitems();
            url_json = getitems.get(1).getImage().toString();
            text_json = getitems.get(1).getTitle().toString();
            sendImageRequest();
            textView.setText(text_json);
        } catch(
                Exception e)

        {
            e.printStackTrace();
        }
    }

}
