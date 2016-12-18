package com.example.yujin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MoimContent extends AppCompatActivity {

    ToggleButton Attendbtn;
    String Title, Date, Time, location;
    TextView Date_t, Time_t, location_t;
    Button myLocationBtn;
    Button info, map, attend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moim_content);


        info = (Button) findViewById(R.id.bt_info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(),"asdfafj",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MoimContent.class);
                startActivity(intent);
            }
        });

        map = (Button) findViewById(R.id.bt_map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(),"a23132j",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MoimContent.this, PublicMap.class);
                startActivity(intent);
            }
        });
        attend = (Button) findViewById(R.id.bt_attend);
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(),"a322262646437547373sdfj",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MoimContent.this, MoimAttendance.class);
                startActivity(intent);
            }
        });

        //현재위치 전송
        myLocationBtn = (Button)findViewById(R.id.myLocationBtn);
        myLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoimContent.this, MapActivity.class);
                startActivity(intent);
            }
        });


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
