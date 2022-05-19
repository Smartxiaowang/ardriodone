package com.example.xq;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_account, et_password;
    Button bt_login ;
    TextView btn_to_zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init() {
        et_account = findViewById(R.id.ed_account);
        et_password = findViewById(R.id.ed_password);
        bt_login = findViewById(R.id.btn_login);
        btn_to_zhuce = findViewById(R.id.btn_to_zhuce);
        btn_to_zhuce.setOnClickListener(this);
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (TextUtils.isEmpty(et_account.getText().toString().trim())) {
                    Toast.makeText(this, "请输账号", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
                    Toast.makeText(this, "请输密码", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String nickName = et_account.getText().toString().trim();
                    int uid = cheack(nickName);
                    if ( uid != -1) {
                        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                        intent.putExtra("uid",String.valueOf(uid));
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.btn_to_zhuce:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private int cheack(String nickName) {
        MyHelper helper=new MyHelper(LoginActivity.this);
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query("user",null,"account=?",new String[]{nickName},null,null,null);
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                @SuppressLint("Range") Integer uid=cursor.getInt(cursor.getColumnIndex("u_id"));
                @SuppressLint("Range") String Account=cursor.getString(cursor.getColumnIndex("account"));
                @SuppressLint("Range") String Password=cursor.getString(cursor.getColumnIndex("password"));
                Log.i("Account",Account);
                if(et_password.getText().toString().trim().equals(Password)){
                    return uid;
                }
            }
        }
        cursor.close();
        return -1;
    }
}