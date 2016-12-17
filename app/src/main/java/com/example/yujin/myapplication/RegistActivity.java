package com.example.yujin.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by YuJin on 2016-12-17.
 */
public class RegistActivity extends AppCompatActivity {
    private EditText input_id;
    private EditText input_passwd;
    private EditText input_passwd2;
    private Button bt_signup;//회원가입
    private Button Rbt_Check; //중복확인

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        input_id = (EditText) findViewById(R.id.R_id);
        input_passwd = (EditText) findViewById(R.id.R_passwd);
        input_passwd2 = (EditText) findViewById(R.id.R_passwd2);
        bt_signup = (Button) findViewById(R.id.bt_signup);
        Rbt_Check = (Button) findViewById(R.id.Rbt_Check);

        // 비밀번호 일치 검사
        input_passwd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = input_passwd.getText().toString();
                String confirm = input_passwd2.getText().toString();

                if( password.equals(confirm) ) {
                    input_passwd.setBackgroundColor(Color.GREEN);
                    input_passwd2.setBackgroundColor(Color.GREEN);
                } else {
                    input_passwd.setBackgroundColor(Color.RED);
                    input_passwd2.setBackgroundColor(Color.RED);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Rbt_Check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(RegistActivity.this, "사용 가능한 Email입니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 이메일 입력 확인
                if( input_id.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "Email을 입력하세요!", Toast.LENGTH_SHORT).show();
                    input_id.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if( input_passwd.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    input_passwd.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                if( input_passwd2.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    input_passwd2.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if( !input_passwd.getText().toString().equals(input_passwd2.getText().toString()) ) {
                    Toast.makeText(RegistActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    input_passwd.setText("");
                    input_passwd2.setText("");
                    input_passwd.requestFocus();
                    return;
                }

                Intent result = new Intent();
                result.putExtra("email", input_id.getText().toString());

                // 자신을 호출한 Activity로 데이터를 보낸다.
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
}
