package com.example.yujin.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MoimAddActivity extends AppCompatActivity {

    SimpleDateFormat fmDate = new SimpleDateFormat("yyyy. M. d");
    SimpleDateFormat fmTime = new SimpleDateFormat("hh:mm a");
    //DateFormat fmDate = DateFormat.getDateInstance();
    //DateFormat fmTime = DateFormat.getTimeInstance();
    TextView dateLabel, timeLabel, title, location;
    Calendar date = Calendar.getInstance();
    Calendar time = Calendar.getInstance();
    Button  MoimAddbtn_;
    ImageButton Datebtn, Timebtn;
    CheckBox secret;
    String secret_s,pwd;
    EditText pwd_e;


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


        pwd_e = (EditText)findViewById(R.id.pwd_e);
        location = (TextView)findViewById(R.id.location_add);
        title = (TextView)findViewById(R.id.Title_add);
        dateLabel = (TextView)findViewById(R.id.date_add);
        timeLabel = (TextView)findViewById(R.id.time_add);
        Datebtn = (ImageButton)findViewById(R.id.dateBtn);
        secret = (CheckBox)findViewById(R.id.secret);
        MoimAddbtn_ = (Button)findViewById(R.id.MoimAddBtn_);
        MoimAddbtn_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(secret.isChecked()){
                    secret_s = "true";
                    pwd = pwd_e.getText().toString();

                }else {
                    secret_s = "false";
                    pwd = "0";
                }

                Intent intent = getIntent();

                intent.putExtra("Title",title.getText().toString());
                Log.e("title",title.getText().toString());

                intent.putExtra("Date",dateLabel.getText());
                Log.e("date",dateLabel.toString());

                intent.putExtra("Time",timeLabel.getText());
                Log.e("time",timeLabel.getText().toString());

                intent.putExtra("location",location.getText().toString());
                Log.e("LOCATION",location.getText().toString());
                intent.putExtra("secret",secret_s);
                Log.e("SECRET",secret_s);

                intent.putExtra("pwd",pwd);

                setResult(1,intent);
                finish();
            }
        });

        Datebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                new DatePickerDialog(MoimAddActivity.this, d, date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Timebtn = (ImageButton)findViewById(R.id.timeBtn);

        Timebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                new TimePickerDialog(MoimAddActivity.this, t, time.get(Calendar.HOUR_OF_DAY),
                        time.get(Calendar.MINUTE),
                        true).show();

            }
        });

        updateLabel();


        //checkbox 여부


    }

    private void updateLabel(){
        dateLabel.setText(fmDate.format(date.getTime()));
        timeLabel.setText(fmTime.format(time.getTime()));
    }
}
