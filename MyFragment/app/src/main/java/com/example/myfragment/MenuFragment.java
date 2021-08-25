package com.example.myfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {
    MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (MainActivity)getActivity(); //액티비티 참조
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null; //액티비티 참조 해제
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);

        Button button = (Button)rootView.findViewById(R.id.button);//최상위 레이아웃이 rootView이기 떄문에 여기서 찾아야함
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange(0);
            }
        });

        return rootView;
    }
}
