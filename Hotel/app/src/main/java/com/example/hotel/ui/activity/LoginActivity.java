package com.example.hotel.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel.R;
import com.example.hotel.bean.User;
import com.example.hotel.util.MySqliteOpenHelper;
import com.example.hotel.util.SPUtils;


/**
 * 登录页面
 */
public class LoginActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private EditText etAccount;//账号
    private EditText etPassword;//密码
    private TextView tv_forget;//忘记密码
    private TextView tvRegister;//注册
    private Button btnLogin;//登录按钮
    private RadioGroup rgType;//用户类型

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new MySqliteOpenHelper(this);
        etAccount = findViewById(R.id.et_account);//获取手机号
        etPassword= findViewById(R.id.et_password);//获取密码
        tv_forget= findViewById(R.id.tv_forget);//获取忘记密码
        tvRegister= findViewById(R.id.tv_register);//获取注册
        rgType = findViewById(R.id.rg_type);
        btnLogin= findViewById(R.id.btn_login);//获取登录
        //手机号注册
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册页面
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //忘记密码
        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到注册页面
                Intent intent=new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });
        //设置点击按钮
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取请求参数
                String account= etAccount.getText().toString();
                String password=etPassword.getText().toString();
                if ("".equals(account)){//用户名不能为空
                    Toast.makeText(LoginActivity.this,"用户名不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(password)){//密码为空
                    Toast.makeText(LoginActivity.this,"密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                User mUser = null;
                //通过账号查询用户是否存在
                String sql = "select * from user where account = ?";
                SQLiteDatabase db = helper.getWritableDatabase();
                Cursor cursor = db.rawQuery(sql, new String[]{account});
                if (cursor != null && cursor.getColumnCount() > 0) {
                    while (cursor.moveToNext()) {
                        Integer dbId = cursor.getInt(0);
                        String dbAccount = cursor.getString(1);
                        String dbPassword = cursor.getString(2);
                        String dbName = cursor.getString(3);
                        String dbSex = cursor.getString(4);
                        String dbPhone = cursor.getString(5);
                        String dbAddress = cursor.getString(6);
                        Integer dbUserType = cursor.getInt(7);
                        mUser = new User(dbId, dbAccount, dbPassword, dbName, dbSex, dbPhone, dbAddress, dbUserType);
                    }
                }
                db.close();
                if (mUser != null) {//用户存在
                    if (!password.equals(mUser.getPassword())) {//判断密码是否正确
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }else{//密码验证成功
                        //跳转到主页面
                        int userType = rgType.getCheckedRadioButtonId() == R.id.rb_user ?0:1;//用户类型
                        if (userType == mUser.getUserType().intValue()){
                            SPUtils.put(LoginActivity.this,SPUtils.USER_ID,mUser.getId());
                            SPUtils.put(LoginActivity.this,SPUtils.USER_TYPE,mUser.getUserType());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, "用户类型不一致", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{//账号不存在
                    Toast.makeText(LoginActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
