package com.example.zp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zp.sqlUntils.SqlUntils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        EditText user = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        TextView forget = findViewById(R.id.forget);
        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);
        new SqlUntils(this).onCreate();
        login.setOnClickListener(v -> {
            String userName = user.getText().toString().trim();
            String passwordName = password.getText().toString().trim();
            if (!userName.equals("") && !passwordName.equals("")) {
                ZpUser zpUser = new SqlUntils(this).login(userName, passwordName);
                if (zpUser.getId() != null) {
                    Intent intent = new Intent();
                    intent.putExtra("userName", zpUser.getUsername());
                    intent.putExtra("skill", zpUser.getSkill());
                    intent.putExtra("onespeak", zpUser.getOnespeak());
                    intent.setClass(this, MainActivity.class);
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "账号密码错误", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "账号密码不为空", Toast.LENGTH_SHORT).show();
            }


        });
        register.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(this, RegisterActivity.class);
            startActivity(intent);
        });
        forget.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(this, ForgetActivity.class);
            startActivity(intent);
        });
    }
}
