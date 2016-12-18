package com.example.yujin.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by SSU on 2016-12-18.
 */
public class MoimMaps extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        int resId = R.layout.activity_moim_attendance;
        Toast.makeText(this.getContext(), "2", Toast.LENGTH_SHORT).show();
        return inflater.inflate(resId, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }
}
