package com.example.zp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zp.sqlUntils.SqlUntils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt_job_cp = findViewById(R.id.bt_job_cp);
        Button bt_job_kf = findViewById(R.id.bt_job_kf);
        Button bt_job_cs = findViewById(R.id.bt_job_cs);
        ImageButton jl = findViewById(R.id.jl);
        ImageButton search = findViewById(R.id.search);
        ImageButton message = findViewById(R.id.message);
        RecyclerView list = findViewById(R.id.gangWeiInfo);
        Intent getIntent = getIntent();
        list.setLayoutManager(new LinearLayoutManager(this));
        list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        list.setAdapter(new IndexAdapter(new SqlUntils(this).qryZpinfo("开发"), this,getIntent.getStringExtra("userName")));
        bt_job_cp.setOnClickListener(v -> {
            list.setAdapter(new IndexAdapter(new SqlUntils(this).qryZpinfo("产品"), this,getIntent.getStringExtra("userName")));
            Toast.makeText(this, "产品", Toast.LENGTH_SHORT).show();

        });
        bt_job_kf.setOnClickListener(v -> {
            list.setAdapter(new IndexAdapter(new SqlUntils(this).qryZpinfo("开发"), this,getIntent.getStringExtra("userName")));
            Toast.makeText(this, "开发", Toast.LENGTH_SHORT).show();

        });
        bt_job_cs.setOnClickListener(v -> {
            list.setAdapter(new IndexAdapter(new SqlUntils(this).qryZpinfo("测试"), this,getIntent.getStringExtra("userName")));
            Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show();
        });

        jl.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("userName",getIntent.getStringExtra("userName"));
            intent.setClass(this,MyselfActivity.class);
            startActivity(intent);
        });
        search.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("username",getIntent.getStringExtra("userName"));
            intent.setClass(this,SerachActivity.class);
            startActivity(intent);
        });
        message.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(this,MessageActivity.class);
            intent.putExtra("userName",getIntent.getStringExtra("userName"));
            startActivity(intent);
        });
    }

}