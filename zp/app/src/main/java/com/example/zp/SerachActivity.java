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

public class SerachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seach);
        EditText jl = findViewById(R.id.gw);
        ImageButton search = findViewById(R.id.serch);
        Intent getIntent = getIntent();
        search.setOnClickListener(v -> {
            String ltdname = jl.getText().toString();
            if (ltdname.equals("")) {
                Toast.makeText(this, "岗位名称不为空", Toast.LENGTH_SHORT).show();
            }else {
                //搜索结果页面
                Intent intent = new Intent();
                intent.putExtra("ltdname",ltdname);
                intent.putExtra("username",getIntent.getStringExtra("username"));
                intent.setClass(this,SerachResActivity.class);
                startActivity(intent);
            }
        });
    }

}