package com.example.hotel.ui.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel.R;
import com.example.hotel.bean.User;
import com.example.hotel.util.MySqliteOpenHelper;


/**
 * 重置密码
 */
public class ForgetActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private Activity activity;
    private EditText et_account;
    private EditText et_phone;
    private EditText etNewPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity =this;
        setContentView(R.layout.activity_forget);
        helper = new MySqliteOpenHelper(activity);
        et_account = findViewById(R.id.et_account);
        et_phone = findViewById(R.id.et_phone);
        etNewPassword = findViewById(R.id.et_new_password);
    }

    //保存信息
    public void save(View v){
        SQLiteDatabase db = helper.getWritableDatabase();
        String account = et_account.getText().toString();
        String phone = et_phone.getText().toString();
        String newPassword = etNewPassword.getText().toString();
        if ("".equals(account)){//账号为空
            Toast.makeText(activity,"账号为空", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(phone)){//手机号为空
            Toast.makeText(activity,"手机号为空", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(newPassword)){//密码为空
            Toast.makeText(activity,"新密码为空", Toast.LENGTH_LONG).show();
            return;
        }
        User mUser = null;
        //通过账号查询是否存在
        String sql = "select * from user where account = ? ";
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
        if (mUser != null) {//用户不存在 注册
            if (phone.equals(mUser.getPhone())) {
                db.execSQL("update user set password = ? where id = ?", new Object[]{mUser.getId()});
                Toast.makeText(ForgetActivity.this,"更新成功",Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(ForgetActivity.this, "该账号与绑定的手机号不一致", Toast.LENGTH_SHORT).show();
            }
        } else {//用户不存在
            Toast.makeText(ForgetActivity.this, "该账号不存在", Toast.LENGTH_SHORT).show();
        }

    }

    public void back(View view){
        finish();
    }
}
