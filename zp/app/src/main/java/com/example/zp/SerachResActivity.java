package com.example.zp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zp.sqlUntils.SqlUntils;

public class SerachResActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serachres);
        Intent intent = getIntent();
        String ltdname = intent.getStringExtra("ltdname");
        String username = intent.getStringExtra("username");
        RecyclerView list = findViewById(R.id.gangWeiInfo);
        list.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //设置适配器
        list.setAdapter(new SerachAdapter(new SqlUntils(this).qryZpinfoByltdname(ltdname), this,username));

    }

}