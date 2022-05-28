package com.example.hotel.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.TimePickerView;
import com.example.hotel.R;
import com.example.hotel.bean.Room;
import com.example.hotel.util.MySqliteOpenHelper;
import com.example.hotel.util.SPUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 预定房间
 */
public class ReserveActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private TextView tvTitle;//标题
    private TextView tvStartDate;//开始日期
    private TextView tvEndDate;//结束日期
    private EditText etName;//姓名
    private EditText etPhone;//手机号
    private EditText etIdCard;//身份证号
    private EditText etRoomNum;//房间数
    private EditText etRemark;//备注
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Room room;
    private Integer userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        helper = new MySqliteOpenHelper(this);
        tvTitle = findViewById(R.id.tv_title);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvEndDate = findViewById(R.id.tv_end_date);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etIdCard = findViewById(R.id.et_idcard);
        etRoomNum = findViewById(R.id.et_roomNum);
        etRemark = findViewById(R.id.et_remark);
        room = (Room) getIntent().getSerializableExtra("room");
        userId = (Integer) SPUtils.get(ReserveActivity.this,SPUtils.USER_ID,0);
        tvTitle.setText(room.getTitle());
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
                etName.setText(dbName);
                etPhone.setText(dbPhone);
            }
        }
        db.close();
    }

    /**
     * 支付
     */
    public void pay(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取请求参数
        String startDate = tvStartDate.getText().toString();
        String endData = tvEndDate.getText().toString();
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String idCard = etIdCard.getText().toString();
        String roomNum = etRoomNum.getText().toString();
        String remark = etRemark.getText().toString();

        if ("请选择".equals(startDate)) {//入住日期不能为空
            Toast.makeText(ReserveActivity.this, "请选择入住日期", Toast.LENGTH_LONG).show();
            return;
        }
        if ("请选择".equals(endData)) {//离店日期不能为空
            Toast.makeText(ReserveActivity.this, "请选择离店日期", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(name)) {//名字不能为空
            Toast.makeText(ReserveActivity.this, "名字不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(phone)) {//手机号不能为空
            Toast.makeText(ReserveActivity.this, "手机号不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(idCard)) {//身份证号不能为空
            Toast.makeText(ReserveActivity.this, "身份证号不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(roomNum)) {//数量不能为空
            Toast.makeText(ReserveActivity.this, "数量不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (Integer.valueOf(roomNum) > room.getSurplus()) {//输入的房间数大于改房间剩余数量
            Toast.makeText(ReserveActivity.this, "输入的房间数大于改房间剩余数量", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(ReserveActivity.this,PayActivity.class);
        intent.putExtra("room",room);
        intent.putExtra("startDate",startDate);
        intent.putExtra("endData",endData);
        intent.putExtra("name",name);
        intent.putExtra("phone",phone);
        intent.putExtra("idCard",idCard);
        intent.putExtra("roomNum",roomNum);
        intent.putExtra("remark",remark);
        startActivity(intent);
        finish();
    }


    //开始日期
    public void start(View view){
        TimePickerView pvTime = new TimePickerView.Builder(ReserveActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date d, View v) {
                tvStartDate.setText(sf.format(d));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("选择时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setSubmitColor(getResources().getColor(R.color.colorPrimary))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.colorBlack))//取消按钮文字颜色*/
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
        pvTime.show();
    }

    //结束日期
    public void end(View view){
        TimePickerView pvTime = new TimePickerView.Builder(ReserveActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date d, View v) {
                tvEndDate.setText(sf.format(d));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("选择时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setSubmitColor(getResources().getColor(R.color.colorPrimary))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.colorBlack))//取消按钮文字颜色*/
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
        pvTime.show();
    }

    //返回
    public void back(View view) {
        finish();
    }
}
