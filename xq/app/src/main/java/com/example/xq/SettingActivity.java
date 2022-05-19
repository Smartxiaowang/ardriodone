package com.example.xq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        View back = findViewById(R.id.back);
        View clear = findViewById(R.id.clear);
        View loginout = findViewById(R.id.loginout);
        back.setOnClickListener(v->{
            finish();
        });
        clear.setOnClickListener(v->{
            Toast.makeText(this, "缓存清理成功", Toast.LENGTH_SHORT).show();
        });
        loginout.setOnClickListener(v->{
            Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClass(this,LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
