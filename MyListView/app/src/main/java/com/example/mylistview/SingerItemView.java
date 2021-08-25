package com.example.mylistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SingerItemView extends LinearLayout {
    TextView textView;
    TextView textView2;
    ImageView imageView;
    public SingerItemView(Context context) {
        super(context);

        init(context);
    }

    public SingerItemView(Context context,  AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
         LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         inflater.inflate(R.layout.singeritem, this, true);

        textView = (TextView)findViewById(R.id.textview);
        textView2 = (TextView)findViewById(R.id.textview2);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setMobile(String mobile){
        textView2.setText(mobile);
    }
    public void setImage(int resId){
        imageView.setImageResource(resId);
    }

}
