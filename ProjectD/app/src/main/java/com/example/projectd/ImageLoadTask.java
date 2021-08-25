package com.example.projectd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;
import java.util.HashMap;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
//위쪽 메서드부터 순차대로 실행됨.
    private String urlStr;
    private ImageView imageView;

    private static HashMap<String, Bitmap> bitmapHash = new HashMap<String, Bitmap>(); //url과 해당 bitmap을 매칭 시켜두어 이전에 요청이
    //있었던 정보라면 이전 정보를 삭제하여 메모리 낭비를 줄인다.

    public ImageLoadTask(String urlStr, ImageView imageView){
      this.urlStr = urlStr;
      this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;
        try {

            if(bitmapHash.containsKey(urlStr)){
            //이미 있던 정보라면
                Bitmap oldbitmap = bitmapHash.remove(urlStr);
                if(oldbitmap != null){
                    oldbitmap.recycle();
                    oldbitmap = null;
                }
            }
            URL url = new URL(urlStr);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream()); //주소로 접속해서 인풋 스트림으로 받아오고
            //그 이미지 정보를 비트맵을 통해 디코드해준다.
            bitmapHash.put(urlStr, bitmap); // 정보를 해쉬에 넣어 나중에 중복여부를 확인한다.    
        }catch (Exception e){
            e.printStackTrace();      //예외가 발생하면 예외 상황 출력
        }
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap); //파라미터로 비트맵을 받고
        imageView.invalidate();//다시 그려주는 역할
    }

}
