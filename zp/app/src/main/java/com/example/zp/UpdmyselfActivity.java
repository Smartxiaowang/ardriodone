package com.example.zp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zp.sqlUntils.SqlUntils;

public class UpdmyselfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updmyself);
        TextView user = findViewById(R.id.username);
        Button save = findViewById(R.id.updmyself);
        EditText skill = findViewById(R.id.skill);
        EditText onespeak = findViewById(R.id.onespeak);
        EditText byyx = findViewById(R.id.byyx);
        EditText xl = findViewById(R.id.xl);
        EditText age = findViewById(R.id.age);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        ZpUser zpUser = new SqlUntils(this).getUserInfo(userName);
        user.setText(zpUser.getUsername());
        skill.setText(zpUser.getSkill());
        onespeak.setText(zpUser.getOnespeak());
        byyx.setText(zpUser.getByyx());
        xl.setText(zpUser.getXl());
        age.setText(zpUser.getAge());
        save.setOnClickListener(v -> {
            String skills = skill.getText().toString().trim();
            String onespeaks = onespeak.getText().toString().trim();
            String byyxx = byyx.getText().toString().trim();
            String xlx = xl.getText().toString().trim();
            String agex = age.getText().toString().trim();
            new SqlUntils(this).updUserInfo(skills, onespeaks, byyxx, xlx, agex, userName);
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            Intent intt = new Intent();
            intt.setClass(this, MyselfActivity.class);
            startActivity(intt);
        });

    }
}
