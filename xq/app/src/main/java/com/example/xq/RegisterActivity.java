package com.example.xq;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText med_register_account, med_register_password, med_sure_password, userjj, nickname;
    Button mbtn_to_login, mbtn_zhuce;

    private RadioButton rb_man, rb_woman;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
                    if (find(account) != -1) {
                        Toast.makeText(this, "账户已存在", Toast.LENGTH_SHORT).show();
                    } else {
                        if (rb_man.isChecked()) {
                            gender = "男";
                        }
                        if (rb_woman.isChecked()) {
                            gender = "女";
                        }
                        insert(account, password, userjjs, nicknameS,gender);
                        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.btn_to_login:
                finish();
                break;
        }
    }

    //添加账户信息
    public void insert(String account, String password, String jj, String nickname,String gender) {
        MyHelper helper = new MyHelper(RegisterActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("account", account);
        values.put("password", password);

        long id = db.insert("user", null, values);
        ContentValues values2 = new ContentValues();
        values2.put("u_id", id);
        values2.put("jj", jj);
        values2.put("nickname", nickname);
        values2.put("sex", gender);
        long userinfoid = db.insert("userinfo", null, values2);
    }

    //根据账号查找
    public int find(String account) {
        MyHelper helper = new MyHelper(RegisterActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("user", null, "account=?", new String[]{account}, null, null, null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") Integer uid = cursor.getInt(cursor.getColumnIndex("u_id"));
                return uid;
            }

        }
        cursor.close();
        return -1;
    }
}