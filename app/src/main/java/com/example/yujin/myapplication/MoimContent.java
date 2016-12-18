package com.example.yujin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MoimContent extends AppCompatActivity {

    ToggleButton Attendbtn;
    String Title, Date, Time, location;
    TextView Date_t, Time_t, location_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moim_content);

        //토글버튼
        Attendbtn = (ToggleButton)findViewById(R.id.attendBtn);
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

        Intent intent = getIntent();
        Title = intent.getExtras().getString("Title");
        Date = intent.getExtras().getString("Date");
        Time = intent.getExtras().getString("Time");
        location = intent.getExtras().getString("local");

        setText();

        Date_t.setText(Date);
        Time_t.setText(Time);
        location_t.setText(location);
    }

    public void setText(){

        Date_t = (TextView)findViewById(R.id.content_date);
        Time_t = (TextView)findViewById(R.id.content_time);
        location_t = (TextView)findViewById(R.id.content_location);

    }
}
