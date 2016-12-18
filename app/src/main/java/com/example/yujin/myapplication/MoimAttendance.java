package com.example.yujin.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SSU on 2016-12-18.
 */
public class MoimAttendance extends Fragment {

    ListView attend_list;
    ViewGroup rootView;
    ListAdapter adapter;
    ArrayList<AttendanceInfo> list = new ArrayList<AttendanceInfo>();

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setData();
        Toast.makeText(this.getContext(), "3", Toast.LENGTH_SHORT).show();
        rootView = (ViewGroup)inflater.inflate(R.layout.activity_moim_attendance, container,false);

        //attend_list = (ListView)rootView.findViewById(R.id.MoimAttendance);



        adapter = new AttendanceListAdapter(this.getContext(), list);

        attend_list = (ListView)rootView.findViewById(R.id.AttendList);
        attend_list.setAdapter(adapter);

        return rootView;
    }

    private void setData() {
        list = new ArrayList<AttendanceInfo>();
        list.add(new AttendanceInfo("Janet Perkins","도착 12: 02"));
        list.add(new AttendanceInfo("Avril Lavigne","미도착"));
        list.add(new AttendanceInfo("Ria Park","도착 12: 01"));
        list.add(new AttendanceInfo("Jessie J ","미도착"));
    }
}


/*

  @Override
        @Deprecated
        protected Dialog onCreateDialog(int id) {
                switch (id) {
                        case DIALOG_MULTICHOICE:

                                        AlertDialog.Builder builder =
                                                new AlertDialog.Builder(TextActivity.this);
                                        final String data[] = {"바베큐", "수영장", "와이파이", "복층"};
                                        final boolean checked[] = {false, false, false, false};
                                        builder.setTitle("MultiChoice 다이얼로그")
                                                .setPositiveButton("선택완료", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                                String str = ">>";
                                                                for (int i = 0; i < checked.length; i++) {
                                                                        if (checked[i]) {
                                                                                str = str + data[i] + ",";

                                                                        }
                                                                }
                                                                tv.setText(str);
                                                        }
                                                }).setNegativeButton("취소", null)
                                                .setMultiChoiceItems(data, checked, new DialogInterface.OnMultiChoiceClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                                                checked[which] = isChecked;
                                                        }
                                                });
                                        return builder.create();

                                }



                return super.onCreateDialog(id);

        }}
 */
