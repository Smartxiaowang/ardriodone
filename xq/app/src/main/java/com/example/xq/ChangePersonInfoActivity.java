package com.example.xq;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePersonInfoActivity extends AppCompatActivity {
    private Button save_info;
    private EditText Et_change_name,Et_change_phone,Et_change_birth,jj,xz;//activity_change_person.xml三个编辑框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_person_info);
        initView();
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");

        save_info.setOnClickListener(v->{
            if (!Et_change_name.getText().equals("")) {
                String nickname = Et_change_name.getText().toString();
                String number = Et_change_phone.getText().toString();
                String jjs = jj.getText().toString();
                String xzs = xz.getText().toString();
                String bir = Et_change_birth.getText().toString();
                new MyHelper(this).upduserinfo(uid,nickname,number,jjs,xzs,bir);
                Toast.makeText(this,"修改成功", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"昵称不为空", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initView() {
        save_info=findViewById(R.id.saveInfo);

        Et_change_name=findViewById(R.id.et_change_name);
        Et_change_phone=findViewById(R.id.et_change_phone);
        Et_change_birth=findViewById(R.id.et_change_birth);
        xz=findViewById(R.id.xz);
        jj=findViewById(R.id.jj);

    }
}
