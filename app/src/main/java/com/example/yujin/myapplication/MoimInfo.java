package com.example.yujin.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by SSU on 2016-12-18.
 */public class MoimInfo extends Fragment {

    ToggleButton Attendbtn;
    String Title, Date, Time, location;
    TextView Date_t, Time_t, location_t;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        int resId = R.layout.activity_moim_info;
        Toast.makeText(this.getContext(), "1", Toast.LENGTH_SHORT).show();
        Attendbtn = (ToggleButton)container.findViewById(R.id.attendBtn);
        Date_t = (TextView)container.findViewById(R.id.content_date);
        Time_t = (TextView)container.findViewById(R.id.content_time);
        location_t = (TextView)container.findViewById(R.id.content_location);
        return inflater.inflate(resId, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        Attendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Attendbtn.isChecked()){
                    Attendbtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn1_s));
                }else {
                    Attendbtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn1_s));
                }
            }
        });

        //    Intent intent = getIntent();

        Bundle extra = getArguments();
        Title = extra.getString("Title");
        Date = extra.getString("Date");
        Time = extra.getString("Time");
        location = extra.getString("local");
//        Title = intent.getExtras().getString("Title");
//        Date = intent.getExtras().getString("Date");
//        Time = intent.getExtras().getString("Time");
//        location = intent.getExtras().getString("local");


        Date_t.setText(Date);
        Time_t.setText(Time);
        location_t.setText(location);
    }


}