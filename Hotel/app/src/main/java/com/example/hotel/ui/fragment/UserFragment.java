package com.example.hotel.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hotel.R;
import com.example.hotel.bean.User;
import com.example.hotel.enums.UserTypeEnum;
import com.example.hotel.ui.activity.LoginActivity;
import com.example.hotel.ui.activity.PasswordActivity;
import com.example.hotel.ui.activity.PayManageActivity;
import com.example.hotel.ui.activity.PersonActivity;
import com.example.hotel.util.GlideEngine;
import com.example.hotel.util.MySqliteOpenHelper;
import com.example.hotel.util.SPUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.List;


/**
 * 我的
 */
public class UserFragment extends Fragment {
    MySqliteOpenHelper helper = null;
    private Activity mActivity;
    private LinearLayout llPerson;
    private LinearLayout llSecurity;
    private LinearLayout llPay;
    private Button btnLogout;
    private User mUser = null;
    private RequestOptions headerRO = new RequestOptions().circleCrop();//圆角变换
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        helper = new MySqliteOpenHelper(mActivity);
        llPerson = view.findViewById(R.id.person);
        llSecurity = view.findViewById(R.id.security);
        llPay = view.findViewById(R.id.pay);
        btnLogout = view.findViewById(R.id.logout);
        initData();
        initView();
        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Integer userId = (Integer) SPUtils.get(mActivity,SPUtils.USER_ID,0);
        Integer userType = (Integer) SPUtils.get(mActivity,SPUtils.USER_TYPE,0);
        llPay.setVisibility(userType.intValue() == UserTypeEnum.Admin.getCode()?View.VISIBLE:View.GONE);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select * from user where id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(userId)});
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
    }

    private void initView() {
        //个人信息
        llPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转页面
                Intent intent = new Intent(mActivity, PersonActivity.class);
                startActivity(intent);
            }
        });
        //账号安全
        llSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转页面
                Intent intent = new Intent(mActivity, PasswordActivity.class);
                startActivity(intent);
            }
        });
        //支付管理
        llPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转页面
                Intent intent = new Intent(mActivity, PayManageActivity.class);
                startActivity(intent);
            }
        });

        //退出登录
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.remove(mActivity,SPUtils.USER_ID);
                SPUtils.remove(mActivity,SPUtils.IF_ADMIN);
                startActivity(new Intent(mActivity, LoginActivity.class));
                mActivity.finish();
            }
        });
    }
}
