package com.example.hotel.ui.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hotel.R;
import com.example.hotel.bean.OrderVo;
import com.example.hotel.enums.OrderStatusEnum;
import com.example.hotel.enums.RoomTypeEnum;
import com.example.hotel.enums.UserTypeEnum;
import com.example.hotel.util.MySqliteOpenHelper;
import com.example.hotel.util.SPUtils;


/**
 * 订单信息
 */
public class OrderActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private ImageView ivImage;//图片
    private TextView tvTitle;
    private TextView tvPrice;
    private TextView tvNumber;
    private TextView tvOrderDate;
    private TextView tvSize;
    private TextView tvBet;
    private TextView tvStartDate;
    private TextView tvEndDate;
    private TextView tvName;
    private TextView tvPhone;
    private TextView tvRoomNum;
    private TextView tv_idCard;
    private TextView tvRemark;
    private Button btnStatus;
    private OrderVo orderVo;//订单信息
    private Integer status;
    private Integer curStatus;//当前的状态
    private Boolean flag =false;
    private Integer userType;
    private Integer roomNum;
    private Integer roomId;
    private Integer surplus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        helper = new MySqliteOpenHelper(this);
        ivImage = findViewById(R.id.iv_photo);
        tvTitle = findViewById(R.id.tv_title);
        tvPrice = findViewById(R.id.tv_price);
        tvNumber = findViewById(R.id.tv_number);
        tvOrderDate = findViewById(R.id.tv_orderDate);
        tvSize = findViewById(R.id.tv_size);
        tvBet = findViewById(R.id.tv_bed);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvEndDate = findViewById(R.id.tv_end_date);
        tvName = findViewById(R.id.tv_name);
        tvPhone = findViewById(R.id.tv_phone);
        tv_idCard = findViewById(R.id.tv_idCard);
        tvRoomNum = findViewById(R.id.tv_roomNum);
        tvRemark = findViewById(R.id.tv_remark);
        btnStatus = findViewById(R.id.btn_status);
        userType = (Integer) SPUtils.get(OrderActivity.this, SPUtils.USER_TYPE, 0);
        int mId = getIntent().getIntExtra("id", 0);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select orders.*,room.title,room.image,room.size,room.bed,room.price,room.surplus,room.typeId from orders,room where orders.roomId = room.id and orders.id = " + mId;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getColumnCount() > 0) {
            while (cursor.moveToNext()) {
                Integer id = cursor.getInt(0);//
                //房间ID
                roomId = cursor.getInt(1);
                Integer userId = cursor.getInt(2);//用户ID
                String number = cursor.getString(3);//编号
                String startDate = cursor.getString(4);//开始日期
                String endDate = cursor.getString(5);//结算日期
                String orderDate = cursor.getString(6);//下单日期
                String name = cursor.getString(7);//姓名
                String phone = cursor.getString(8);//电话
                //房间数量
                roomNum = cursor.getInt(9);
                String remark = cursor.getString(10);//备注
                status = cursor.getInt(11); //状态
                String idCard = cursor.getString(12); //身份证
                Double orderPrice = cursor.getDouble(13); //订单价格
                String title = cursor.getString(14);//标题
                String image = cursor.getString(15);//图片
                String size = cursor.getString(16);//大小
                String bed = cursor.getString(17);//床型
                Double price = cursor.getDouble(18);//价格
                //剩余数量
                surplus = cursor.getInt(19);
                Integer typeId = cursor.getInt(20);//类型
                tvTitle.setText(String.format("%s(%s)",title, RoomTypeEnum.getName(typeId)));
                tvPrice.setText(String.format("¥%.2f", orderPrice));
                tvNumber.setText(String.format("编号：%s", number));
                tvOrderDate.setText(String.format("下单时间：%s", orderDate));
                tvSize.setText(String.format("大小：%s", size));
                tvBet.setText(String.format("床型：%s", bed));
                tvStartDate.setText(String.format("入住日期：%s", startDate));
                tvEndDate.setText(String.format("离店日期：%s", endDate));
                tvName.setText(String.format("姓名：%s", name));
                tvPhone.setText(String.format("电话：%s", phone));
                tv_idCard.setText(String.format("身份证：%s", idCard));
                tvRoomNum.setText(String.format("数量：%s", roomNum));
                tvRemark.setText(String.format("备注：%s", remark));
                Glide.with(OrderActivity.this)
                        .load(image)
                        .into(ivImage);
            }
        }
        db.close();
        if (OrderStatusEnum.Reserve.getCode() == status.intValue()) {//预定房间
            if (UserTypeEnum.User.getCode() == userType.intValue()) {//普通用户
                btnStatus.setText("退订房间");
                curStatus = OrderStatusEnum.Unsubscribe.getCode();
                flag = true;
            } else {//管理员
                btnStatus.setText("办理入住");
                curStatus = OrderStatusEnum.CheckingIn.getCode();
            }
        } else if (OrderStatusEnum.Unsubscribe.getCode() == status.intValue()) {//退订房间
            btnStatus.setVisibility(View.GONE);//隐藏
        } else if (OrderStatusEnum.CheckingIn.getCode() == status.intValue()) {//办理入住
            if (UserTypeEnum.Admin.getCode() == userType.intValue()) {//管理员
                btnStatus.setText("办理退房");
                curStatus = OrderStatusEnum.CheckingOut.getCode();
                flag = true;
            }else {
                btnStatus.setVisibility(View.GONE);//隐藏
            }
        } else if (OrderStatusEnum.CheckingOut.getCode() == status.intValue()) {//办理退房
            btnStatus.setVisibility(View.GONE);//隐藏
        }
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();
                String sql = "update orders set status = ? where id =?";
                db.execSQL(sql,new Object[]{curStatus,mId});
                Toast.makeText(OrderActivity.this,"操作成功",Toast.LENGTH_SHORT).show();
                if (flag){
                    String sql1 = "update room set surplus = ? where id =?";
                    db.execSQL(sql1,new Object[]{surplus + roomNum,roomId});
                }
                db.close();
                setResult(RESULT_OK);
                finish();
            }
        });
    }


    //返回
    public void back(View view) {
        finish();
    }
}
