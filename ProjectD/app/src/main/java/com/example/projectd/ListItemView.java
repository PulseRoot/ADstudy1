package com.example.projectd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ListItemView extends LinearLayout {
    TextView textView, textView2;
    ImageView imageView;
    RatingBar star;
    RatingBar star2;
    float starnum;

    public ListItemView(Context context) {
        super(context);
        init(context);
    }

    public ListItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_write,this,true);

        textView = findViewById(R.id.textview);
        textView2 = findViewById(R.id.textview2);
        imageView = findViewById(R.id.imageView);
        star = findViewById(R.id.star);
        star2 = findViewById(R.id.star);
    }
    public void setName(String name){
        textView.setText(name);
    }
    public void setTalk(String talk){
        textView2.setText(talk);
    }
    public void setImage(int resId){
        imageView.setImageResource(resId);
    }
    public void setStarnum(float starnum){star2.setRating(starnum);}
}
