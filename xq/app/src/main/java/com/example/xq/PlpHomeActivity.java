package com.example.xq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PlpHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_plp_home);
        ImageView back = findViewById(R.id.back);
        RecyclerView list = findViewById(R.id.plplist);
        TextView title = findViewById(R.id.title);
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");
        Random random = new Random();
        int i = random.nextInt(9);
        List<HashMap<String, String>> plpList = new MyHelper(this).getPlpList(uid, String.valueOf(i));
        title.setText("捞到了"+plpList.size()+"个瓶子");
        list.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //设置适配器
        list.setAdapter(new PlpHomeAdapter(plpList,this));
        back.setOnClickListener(v->{
            finish();
        });

    }
}