package com.example.yujin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

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

}
