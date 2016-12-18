package com.example.yujin.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MoimListviewActivity extends AppCompatActivity {

    ListView listView;
    MoimCustomListviewAdapter adapter;
    String Title,Date,Time,location,secret,pwd;
    Drawable secret_d;
//    private ArrayList<MoimItemData> MoimItemDatas = null;
//    private MoimCustomListviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moim_listview);



        adapter = new MoimCustomListviewAdapter();

        listView = (ListView)findViewById(R.id.moim_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);

        //Drawable lock, String title, String date, String time, String location
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_lock),"2017년 송년회 모임","2016.12.22","PM 07:00","강남역 6번 출구 앞","true","1111");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.empty),"SW Welcome girls! 2016 HACKATHON","2016.12.17","AM 10:00","연세대학교 새천년관","false","0");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_lock),"2017년 송년회 모임","2016.12.22","PM 07:00","강남역 6번 출구 앞","true","1111");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_lock),"2017년 송년회 모임","2016.12.22","PM 07:00","강남역 6번 출구 앞","true","1111");



        ImageButton addbtn = (ImageButton)findViewById(R.id.MoimAddbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoimListviewActivity.this, MoimAddActivity.class);
                startActivityForResult(intent,0);
            }
        });


    }





    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final MoimItemData itemData_temp = (MoimItemData) adapter.getItem(position);

            if(itemData_temp.getSecret().toString().equals("false")) {
                AlertDialog.Builder popUp = new AlertDialog.Builder(MoimListviewActivity.this);
                popUp.setTitle(itemData_temp.Title).setMessage("해당모임에 참여하시겠습니까?")
                        .setCancelable(false)
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).setPositiveButton("확인", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Intent intent = new Intent(MoimListviewActivity.this, MoimContent.class);
                        intent.putExtra("Title", itemData_temp.getTitle());
                        intent.putExtra("Date", itemData_temp.getDate());
                        intent.putExtra("Time", itemData_temp.getTime());
                        intent.putExtra("local", itemData_temp.getLocation());
                        startActivityForResult(intent, 0);

                    }
                }).show();
            }if(itemData_temp.getSecret().toString().equals("true")){
                AlertDialog.Builder alert = new AlertDialog.Builder(MoimListviewActivity.this);
                alert.setTitle("비밀방").setMessage("비밀번호를 입력해주세요.");

                final EditText input = new EditText(MoimListviewActivity.this);
                alert.setView(input);

                alert.setPositiveButton("입력",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String secret = input.getText().toString();
                        Log.e("secret pwd",secret);
                            if(itemData_temp.getPwd().toString().equals(secret)){
                                Intent intent = new Intent(MoimListviewActivity.this, MoimContent.class);
                                intent.putExtra("Title", itemData_temp.getTitle());
                                intent.putExtra("Date", itemData_temp.getDate());
                                intent.putExtra("Time", itemData_temp.getTime());
                                intent.putExtra("local", itemData_temp.getLocation());
                                startActivityForResult(intent, 0);

                            }else {
                                Toast.makeText(MoimListviewActivity.this, "비밀번호가 틀렸습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                            }
                    }
                });

                alert.setNegativeButton("취소", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
            }
        };


    protected void  onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (resultCode){
            case 1 :


                Title = data.getExtras().getString("Title");
                Date = data.getExtras().getString("Date");
                Time = data.getExtras().getString("Time");
                location = data.getExtras().getString("location");
                secret = data.getExtras().getString("secret");
                pwd = data.getExtras().getString("pwd");
                Log.e("secret",secret);


                if(secret.equals("false")){
                    secret_d = getResources().getDrawable(R.drawable.empty);
                    Log.e("secret","true");

                }if(secret.equals("true")) {
                secret_d = getResources().getDrawable(R.drawable.ic_lock);
                Log.e("secret","false");
            }

                adapter.addItem(secret_d,Title,Date,Time,location,secret,pwd);

                NotificationSomthings();

//                //Notification
//                PendingIntent mPendingIntent = PendingIntent.getActivity(
//                        getApplicationContext(),0, new Intent(getApplicationContext(),
//                                MoimListviewActivity.class),
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );
//
//                Notification mNoti = new NotificationCompat.Builder(getApplicationContext())
//                        .setContentTitle("모임추가")
//                        .setContentText("새로운 모임이 추가되었습니다.")
//                        .setSmallIcon(R.drawable.logo)
//                        .setTicker("모임")
//                        .setContentIntent(mPendingIntent)
//                        .build();
//

                break;

        }
    }

    public void NotificationSomthings() {

        Resources res = getResources();

        Intent notificationIntent = new Intent(this, MoimListviewActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle("모임추가")
                .setContentText("새로운 모임이 추가되었습니다.")
                .setTicker("새로운 모임추가")
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(Notification.CATEGORY_MESSAGE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC);
        }


    }


}





