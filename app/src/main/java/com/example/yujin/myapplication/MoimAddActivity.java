package com.example.yujin.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MoimAddActivity extends AppCompatActivity {

    SimpleDateFormat fmDate = new SimpleDateFormat("yyyy년 M월 d일");
    SimpleDateFormat fmTime = new SimpleDateFormat("hh:mm a");
    //DateFormat fmDate = DateFormat.getDateInstance();
    //DateFormat fmTime = DateFormat.getTimeInstance();
    TextView dateLabel, timeLabel;
    Calendar date = Calendar.getInstance();
    Calendar time = Calendar.getInstance();
    Button Datebtn, Timebtn;

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, monthOfYear);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            time.set(Calendar.HOUR_OF_DAY,hourOfDay);
            time.set(Calendar.MINUTE, minute);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moim_add);

        dateLabel = (TextView)findViewById(R.id.date);
        timeLabel = (TextView)findViewById(R.id.time);
        Datebtn = (Button)findViewById(R.id.dateBtn);

        Datebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                new DatePickerDialog(MoimAddActivity.this, d, date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Timebtn = (Button)findViewById(R.id.timeBtn);

        Timebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                new TimePickerDialog(MoimAddActivity.this, t, time.get(Calendar.HOUR_OF_DAY),
                        time.get(Calendar.MINUTE),
                        true).show();

            }
        });

        updateLabel();

    }

    private void updateLabel(){
        dateLabel.setText(fmDate.format(date.getTime()));
        timeLabel.setText(fmTime.format(time.getTime()));
    }
}
