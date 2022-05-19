package com.example.xq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.locks.ReentrantLock;

public class PlpActivity extends AppCompatActivity {
    private ImageView ll_add, ll_message, ll_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activiyt_plp);
        Button lpz = findViewById(R.id.button5);
        Button rpz = findViewById(R.id.button7);
        TextView hint = findViewById(R.id.hint);
        Intent intent = getIntent();
        ImageView jz = findViewById(R.id.jz);
        ll_add = findViewById(R.id.iv_add);
        ll_message = findViewById(R.id.iv_message);
        ll_home = findViewById(R.id.iv_home);
        ll_add.setOnClickListener(v -> {
            Intent intent2 = new Intent();
            intent2.putExtra("uid", intent.getStringExtra("uid"));
            intent2.setClass(this, AddActivity.class);
            startActivity(intent2);
        });
        ll_message.setOnClickListener(v -> {
            Intent intent2 = new Intent();
            intent2.putExtra("uid", intent.getStringExtra("uid"));
            intent2.setClass(this, MessageActivity.class);
            startActivity(intent2);
        });
        ll_home.setOnClickListener(v -> {
            Intent intent2 = new Intent();
            intent2.putExtra("uid", intent.getStringExtra("uid"));
            intent2.setClass(this, IndexActivity.class);
            startActivity(intent2);
        });
        lpz.setOnClickListener(v -> {
            hint.setText("正在捞取瓶子");
            jz.setImageResource(R.mipmap.jz);
            new Handler().postDelayed(() -> {
                hint.setText("");
                jz.setImageResource(0);
                Intent intent2 = new Intent();
                intent2.putExtra("uid",intent.getStringExtra("uid"));
                intent2.setClass(this, PlpHomeActivity.class);
                startActivity(intent2);
            }, 2000);

        });
        rpz.setOnClickListener(v -> {
            Intent intent2 = new Intent();
            intent2.putExtra("uid",intent.getStringExtra("uid"));
            intent2.setClass(this, PlpPushActivity.class);
            startActivity(intent2);
        });

    }
}