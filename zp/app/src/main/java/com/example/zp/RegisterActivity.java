package com.example.zp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zp.R;
import com.example.zp.sqlUntils.SqlUntils;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        TextView user = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);
        TextView skill = findViewById(R.id.skill);
        TextView onespeak = findViewById(R.id.onespeak);
        TextView byyx = findViewById(R.id.byyx);
        TextView xl = findViewById(R.id.xl);
        TextView age = findViewById(R.id.age);
        Button bt_reg = findViewById(R.id.bt_reg);
        bt_reg.setOnClickListener(v -> {
            String userName = user.getText().toString().trim();
            String passwordName = password.getText().toString().trim();
            String skills = skill.getText().toString().trim();
            String onespeaks = onespeak.getText().toString().trim();
            String byyxs = byyx.getText().toString().trim();
            String xls = xl.getText().toString().trim();
            String ages = age.getText().toString().trim();
            if (!userName.equals("") && !passwordName.equals("")) {
                ZpUser zpUser = new ZpUser(userName, passwordName, onespeaks, skills,byyxs,xls,"在校-月内到岗",ages);
                new SqlUntils(this).inserZpuser(zpUser);
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("userName", userName);
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "账号密码不为空", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
