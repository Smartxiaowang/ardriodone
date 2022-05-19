package com.example.zp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zp.sqlUntils.SqlUntils;

public class ForgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);
        EditText user = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button bt_reg = findViewById(R.id.bt_reg);
        bt_reg.setOnClickListener(v -> {
            String userName = user.getText().toString().trim();
            String passwordName = password.getText().toString().trim();
            if (!userName.equals("") && !passwordName.equals("")) {
                new SqlUntils(this).upPassword(userName, passwordName);
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "账号密码不为空", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
