package com.example.xq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        TextView myselfmessage = findViewById(R.id.myselfmessage);
        TextView nickname = findViewById(R.id.nickname);
        TextView setting = findViewById(R.id.setting);
        TextView about = findViewById(R.id.about);
        TextView jj = findViewById(R.id.jj);
        ImageView tx = findViewById(R.id.tx);
        ImageView sex = findViewById(R.id.sex);
        TextView myinfo = findViewById(R.id.myinfo);
        Intent intent = getIntent();
        List<HomeDataBean> beans = new MyHelper(this).getHomeDataInfo(intent.getStringExtra("uid"));
        HomeDataBean homeDataBean = beans.get(0);
        nickname.setText(homeDataBean.getNickname());
        jj.setText(homeDataBean.getJj());
        if (homeDataBean.getSex().equals("女")){
            tx.setImageResource(R.drawable.txv);
            sex.setImageResource(R.mipmap.v);
        }
        myselfmessage.setOnClickListener(v -> {
            Intent intent2 = new Intent();
            intent2.putExtra("uid", intent.getStringExtra("uid"));
            intent2.setClass(this, MyselfPushActivity.class);
            startActivity(intent2);
        });
        myinfo.setOnClickListener(v -> {
            Intent intent2 = new Intent();
            intent2.putExtra("uid", intent.getStringExtra("uid"));
            intent2.setClass(this, PinfoActivity.class);
            startActivity(intent2);
        });
        about.setOnClickListener(v -> {
            Intent intent2 = new Intent();
            intent2.putExtra("uid", intent.getStringExtra("uid"));
            intent2.setClass(this, AboutActivity.class);
            startActivity(intent2);
        });
        setting.setOnClickListener(v -> {
            Intent intent2 = new Intent();
            intent2.putExtra("uid", intent.getStringExtra("uid"));
            intent2.setClass(this, SettingActivity.class);
            startActivity(intent2);
        });
    }
}