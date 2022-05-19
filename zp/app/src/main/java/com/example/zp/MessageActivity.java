package com.example.zp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zp.sqlUntils.SqlUntils;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        Intent getIntent = getIntent();
        ImageButton jl = findViewById(R.id.jl);
        RecyclerView tdjl = findViewById(R.id.tdjl);
        tdjl.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        tdjl.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //设置适配器
        tdjl.setAdapter(new MessageAdapter(new SqlUntils(this).qryTdinfo(getIntent.getStringExtra("userName")), this));
        ImageButton qy = findViewById(R.id.qy);

        jl.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("userName",getIntent.getStringExtra("userName"));
            intent.setClass(this,MyselfActivity.class);
            startActivity(intent);
        });
        qy.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("userName",getIntent.getStringExtra("userName"));
            intent.setClass(this,MainActivity.class);
            startActivity(intent);
        });

    }
}
