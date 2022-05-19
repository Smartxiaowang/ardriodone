package com.example.xq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        EditText et_fabiao = findViewById(R.id.et_fabiao);
        EditText bq = findViewById(R.id.bq);
        Button btn_fabiao = findViewById(R.id.btn_fabiao);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date());
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");
        btn_fabiao.setOnClickListener(v -> {
            if (!et_fabiao.getText().toString().equals(""))
                new MyHelper(this).insertDre(et_fabiao.getText().toString(), bq.getText().toString(), format, uid);
            else
                Toast.makeText(this,"文章不能为空", Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"发布成功", Toast.LENGTH_SHORT).show();
        });
    }
}
