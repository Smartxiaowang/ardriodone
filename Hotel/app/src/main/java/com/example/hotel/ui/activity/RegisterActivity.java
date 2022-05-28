package com.example.hotel.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hotel.R;
import com.example.hotel.bean.User;
import com.example.hotel.util.GlideEngine;
import com.example.hotel.util.MySqliteOpenHelper;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.List;

/**
 * 注册页面
 */
public class RegisterActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private EditText etAccount;//账号
    private EditText etNickName;//昵称
    private EditText etPhone;//手机号
    private EditText etAddress;//地址
    private EditText etPassword;//密码
    private EditText etPasswordSure;//确认密码
    private RadioGroup rgSex;//性别
    private RadioGroup rgType;//用户类型
    private TextView tvLogin;//登录
    private Button btnRegister;//注册按钮
    private RequestOptions headerRO = new RequestOptions().circleCrop();//圆角变换

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        helper = new MySqliteOpenHelper(this);
        etAccount = findViewById(R.id.et_account);//获取账号
        etNickName = findViewById(R.id.et_nickName);//获取昵称
        etPhone = findViewById(R.id.et_phone);//获取手机号
        etAddress = findViewById(R.id.et_address);//获取地址
        etPassword = findViewById(R.id.et_password);//获取密码
        etPasswordSure = findViewById(R.id.et_password_sure);//获取确认密码
        rgType = findViewById(R.id.rg_type);
        rgSex = findViewById(R.id.rg_sex);
        tvLogin = (TextView) findViewById(R.id.tv_login);//登录
        btnRegister = (Button) findViewById(R.id.btn_register);//获取注册按钮
        //返回登录
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到登录页面
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //设置注册点击按钮
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取请求参数
                String account = etAccount.getText().toString();
                String nickName = etNickName.getText().toString();
                String phone = etPhone.getText().toString();
                String address = etAddress.getText().toString();
                String password = etPassword.getText().toString();
                String passwordSure = etPasswordSure.getText().toString();
                if ("".equals(account)) {//用户名不能为空
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(nickName)) {//昵称不能为空
                    Toast.makeText(RegisterActivity.this, "昵称不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(phone)) {//手机号不能为空
                    Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if (phone.length() != 11) {//手机号格式错误
                    Toast.makeText(RegisterActivity.this, "手机号格式错误", Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(address)) {//地址不能为空
                    Toast.makeText(RegisterActivity.this, "地址不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(password)) {//密码为空
                    Toast.makeText(RegisterActivity.this, "密码为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(passwordSure)) {//密码不一致
                    Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_LONG).show();
                    return;
                }
                String sex = rgSex.getCheckedRadioButtonId() == R.id.rb_man ? "男" : "女";//性别
                int userType = rgType.getCheckedRadioButtonId() == R.id.rb_user ? 0 : 1;//用户类型
                User mUser = null;
                //通过账号查询是否存在
                String sql = "select * from user where account = ? ";
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
                if (mUser == null) {//用户不存在 注册
                    String insertSql = "insert into user(account, password,name,sex, phone,address,userType) values(?,?,?,?,?,?,?)";
                    db.execSQL(insertSql, new Object[]{account, password, nickName, sex, phone, address, userType});
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {//用户存在
                    Toast.makeText(RegisterActivity.this, "该账号已存在", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
    }



    public void back(View view) {
        finish();
    }
}
