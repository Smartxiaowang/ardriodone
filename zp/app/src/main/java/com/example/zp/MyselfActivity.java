package com.example.zp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zp.sqlUntils.SqlUntils;

public class MyselfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself);
        TextView user = findViewById(R.id.username);
        Button updmyself = findViewById(R.id.updmyself);
        TextView skill = findViewById(R.id.skill);
        TextView onespeak = findViewById(R.id.onespeak);
        TextView byyx = findViewById(R.id.byyx);
        TextView xl = findViewById(R.id.xl);
        TextView age = findViewById(R.id.age);
        TextView workstu = findViewById(R.id.workstu);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        ZpUser zpUser = new SqlUntils(this).getUserInfo(userName);
        user.setText(zpUser.getUsername());
        skill.setText(zpUser.getSkill());
        onespeak.setText(zpUser.getOnespeak());
        byyx.setText(zpUser.getByyx());
        xl.setText(zpUser.getXl());
        age.setText(zpUser.getAge());

        updmyself.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setClass(this, UpdmyselfActivity.class);
            i.putExtra("userName", userName);
            startActivity(i);
        });
    }
}
