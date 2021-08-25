package com.example.myfragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class ViewerFragment extends Fragment {
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_viewer, container, false);
        imageView = (ImageView)rootView.findViewById(R.id.imageView);

        return rootView;
    }

    public void setImage(int index){
        if(index == 0){
            imageView.setImageResource(R.drawable.icon00);
        }else if(index == 1){
            imageView.setImageResource(R.drawable.icon01);
        }else if(index == 2){
            imageView.setImageResource(R.drawable.icon02);
        }
    }

}
