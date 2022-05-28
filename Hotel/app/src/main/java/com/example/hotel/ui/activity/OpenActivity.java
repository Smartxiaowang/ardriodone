package com.example.hotel.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel.R;
import com.example.hotel.util.SPUtils;


/**
 * 欢迎页
 */
public class OpenActivity extends AppCompatActivity {
    private Button in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        in = findViewById(R.id.in);
        Integer userId= (Integer) SPUtils.get(OpenActivity.this, SPUtils.USER_ID,0);
        CountDownTimer timer = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                in.setText(millisUntilFinished / 1000 + "秒");
            }
            public void onFinish() {
                Intent intent = new Intent();
                if (userId > 0) {//已登录
                    intent.setClass(OpenActivity.this, MainActivity.class);
                }else {
                    intent.setClass(OpenActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        };
        timer.start();
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (userId > 0) {//已登录
                    intent.setClass(OpenActivity.this, MainActivity.class);
                }else {
                    intent.setClass(OpenActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                timer.cancel();
                finish();
            }
        });
    }
}