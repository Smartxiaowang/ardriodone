package com.example.xq;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class IndexActivity extends AppCompatActivity {

    private ImageView ll_add, ll_message, ll_home, iv_plp;
    private RecyclerView list;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        ll_add = findViewById(R.id.iv_add);
        ll_message = findViewById(R.id.iv_message);
        ll_home = findViewById(R.id.iv_home);
        iv_plp = findViewById(R.id.iv_plp);
        list = findViewById(R.id.indexlist);
        iv_plp.setOnClickListener(v -> {
            Intent intent2 = new Intent();
            intent2.putExtra("uid", intent.getStringExtra("uid"));
            intent2.setClass(this, PlpActivity.class);
            startActivity(intent2);
            finish();
        });
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
        list.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //设置适配器
        list.setAdapter(new IndexAdapter(new MyHelper(this).getHomeDataInfo(""), this));
        ll_home.setOnClickListener(v -> {
            list.setAdapter(new IndexAdapter(new MyHelper(this).getHomeDataInfo(""), this));
        });
    }

}
