package com.example.yujin.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnMap, btnSearch, btnPublicMap, btn;
    TextView tvLocation;
    String lol="";
    EditText input_id, input_passwd;
    Button bt_login, bt_findPW, bt_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_id = (EditText) findViewById(R.id.input_id);
        input_passwd = (EditText) findViewById(R.id.input_passwd);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Intent intent = new Intent(getApplicationContext(), MoimListviewActivity.class);
                Intent intent = new Intent(getApplicationContext(), Moim.class);
                startActivity(intent);
            }
        });

        bt_findPW = (Button) findViewById(R.id.bt_findPW);

        bt_signup = (Button) findViewById(R.id.bt_signup);
        bt_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), RegistActivity.class);
                startActivity(intent);
            }
        });
    }

        Button btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoimListviewActivity.class);
                startActivity(intent);
            }
        });



        btnMap=(Button)findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettongLocation.class);
                startActivity(intent);
            }
        });

        btnPublicMap = (Button)findViewById(R.id.btnPublicMap);
        btnPublicMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PublicMap.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences test = getSharedPreferences("주소", MODE_PRIVATE);
        lol = test.getString("주소", "empty");

        if(lol.equals("empty")){
            Log.i("가져온정보", "없음");
        }else{
            Message msg1 = handler.obtainMessage();
            msg1.what=1;
            handler.sendMessage(msg1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences pref = getSharedPreferences("주소", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("주소");
        editor.commit();


    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                    tvLocation = (TextView)findViewById(R.id.tvLocation);
                    tvLocation.setText(lol);
                    break;
            }
        }
    };
}
