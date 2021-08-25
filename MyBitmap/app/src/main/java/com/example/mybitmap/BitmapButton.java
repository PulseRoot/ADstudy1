package com.example.mybitmap;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class BitmapButton extends AppCompatButton {
    public BitmapButton(Context context) {
        super(context);

        init(context);
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        //배경 이미지
        setBackgroundResource(R.drawable.title_bitmap_button_normal);
        float textsize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textsize);
        setTextColor(Color.WHITE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.title_bitmap_button_clicked);

                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.drawable.title_bitmap_button_normal);

                break;
        }
        invalidate();
        //다시 그리게 하는 함수
        return true;
    }
}

