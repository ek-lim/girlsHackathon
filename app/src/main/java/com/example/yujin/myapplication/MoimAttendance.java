package com.example.yujin.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by SSU on 2016-12-18.
 */
public class MoimAttendance extends Fragment {

    ListView attend_list;
    ViewGroup rootView;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  Toast.makeText(, "사용 가능한 Email입니다.", Toast.LENGTH_SHORT).show();
        rootView = (ViewGroup)inflater.inflate(R.layout.activity_moim_attendance, container,false);
        //attend_list = (ListView)rootView.findViewById(R.id.MoimAttendance);
        ListAdapter adapter;
        ArrayList<String> list = new ArrayList<String>();
        list.add(0,"Hello, World!");
        return rootView;
    }
}
