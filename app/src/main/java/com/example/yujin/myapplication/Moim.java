package com.example.yujin.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by SSU on 2016-12-17.
 */
public class Moim extends AppCompatActivity {

    ToggleButton Attendbtn;
    String Title, Date, Time, location;
    TextView Date_t, Time_t, location_t;
    Button myLocationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moim);

       // GridLayout gridlayout = (GridLayout) findViewById(R.id.grid);
        Button info, map, attend;
        info = (Button) findViewById(R.id.bt_info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"asdfafj",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MoimContent.class);
                startActivity(intent);
            }
        });

        map = (Button) findViewById(R.id.bt_map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"a23132j",Toast.LENGTH_SHORT).show();
                /* Intent intent = new Intent(getApplicationContext(), PublicMap.class);
                startActivity(intent);*/
            }
        });
        attend = (Button) findViewById(R.id.bt_attend);
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"a322262646437547373sdfj",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MoimAttendance.class);
                startActivity(intent);
            }
        });
    }
}
