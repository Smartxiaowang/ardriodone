package com.example.xq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlpPushActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");
        setContentView(R.layout.activiy_plp_push);
        ImageView add = findViewById(R.id.add);
        ImageView back = findViewById(R.id.back);
        EditText nr = findViewById(R.id.nr);
        add.setOnClickListener(v->{
            if (!nr.getText().toString().equals("")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                String dateup = simpleDateFormat.format(new Date());
                new MyHelper(this).insertPlp(nr.getText().toString(),dateup,uid);
                Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "内容不为空", Toast.LENGTH_SHORT).show();

        });
        back.setOnClickListener(v->{
            finish();
        });
    }
}