package com.example.yujin.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by SSU on 2016-12-18.
 */
public class AttendanceListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AttendanceInfo> arrData;
    private LayoutInflater inflater;

    public AttendanceListAdapter(Context context, ArrayList<AttendanceInfo> list) {
        this.context = context;
        this.arrData = list;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_moim_attendance, parent, false);
        }
        return convertView;
    }

}
