package com.example.xq;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class PinfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        Intent getIntent = getIntent();
        String uid = getIntent.getStringExtra("uid");
        HashMap<String, String> userInfo = new MyHelper(this).getUserInfo(uid);
        Button person_back = findViewById(R.id.person_back);
        Button btn_change_info = findViewById(R.id.btn_change_info);
        TextView tv_info_name = findViewById(R.id.tv_info_name);
        TextView tv_info_phone = findViewById(R.id.tv_info_phone);
        TextView tv_info_birth = findViewById(R.id.tv_info_birth);
        TextView xj = findViewById(R.id.xj);
        TextView sex = findViewById(R.id.sex);
        tv_info_phone.setText(userInfo.get("number"));
        tv_info_birth.setText(userInfo.get("birsday"));
        xj.setText(userInfo.get("xz"));
        sex.setText(userInfo.get("sex"));
        tv_info_name.setText(userInfo.get("nickname"));
        person_back.setOnClickListener(v -> {
            finish();
        });
        btn_change_info.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("uid", uid);
            intent.setClass(this, ChangePersonInfoActivity.class);
            startActivity(intent);
        });
    }
}