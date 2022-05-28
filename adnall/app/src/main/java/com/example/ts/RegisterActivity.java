package com.example.ts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db.DBUtils;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText med_register_account, med_register_password, med_sure_password, userjj, nickname;
    Button mbtn_to_login, mbtn_zhuce;

    private RadioButton rb_man, rb_woman;
    String gender;
    private Context c;
    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    if (msg.obj.equals("注册成功")) {
                        Toast.makeText(c, "注册成功", Toast.LENGTH_SHORT).show();

                    }else
                        Toast.makeText(c, "注册失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        c = this;
        initView();
    }

    private void initView() {
        med_register_account = findViewById(R.id.ed_register_account);
        med_register_password = findViewById(R.id.ed_register_password);
        med_sure_password = findViewById(R.id.ed_register_sure_password);
        mbtn_zhuce = findViewById(R.id.btn_zhuce);
        nickname = findViewById(R.id.nickname);
        userjj = findViewById(R.id.userjj);

        rb_man = findViewById(R.id.rb_man);

        rb_woman = findViewById(R.id.rb_woman);


        mbtn_to_login = findViewById(R.id.btn_to_login);
        mbtn_to_login.setOnClickListener(this);
        mbtn_zhuce.setOnClickListener(this);

        rb_man.setOnClickListener(v -> {
            rb_man.setBackgroundResource(R.mipmap.n);
            rb_woman.setBackgroundResource(R.mipmap.fv);

        });
        rb_woman.setOnClickListener(v -> {
            rb_woman.setBackgroundResource(R.mipmap.v);
            rb_man.setBackgroundResource(R.mipmap.fn);
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zhuce:
                String account = med_register_account.getText().toString().trim();
                String password = med_register_password.getText().toString().trim();
                String surepassword = med_sure_password.getText().toString().trim();
                String userjjs = userjj.getText().toString().trim();
                String nicknameS = nickname.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(this, "请输账号", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "请输密码", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(surepassword)) {
                    Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                } else if ((!rb_man.isChecked()) && (!rb_woman.isChecked())) {
                    Toast.makeText(this, "选择性别", Toast.LENGTH_SHORT).show();
                } else {
                    if (rb_man.isChecked()) {
                        gender = "男";
                    }
                    if (rb_woman.isChecked()) {
                        gender = "女";
                    }
                    insert(account, password, userjjs, nicknameS, gender);
                    //Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_to_login:
                finish();
                break;
        }
    }

    private void insert(String account, String password, String userjjs, String nicknameS, String gender) {
        new Thread(() -> {
            DBUtils dbUtils = new DBUtils();
            String c = dbUtils.insert(account, password, userjjs, nicknameS, gender);
            Message message = handler.obtainMessage();
            message.what = 1;
            message.obj = c;
            handler.sendMessage(message);
        }).start();
    }
}
