package com.example.hotel.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hotel.R;
import com.example.hotel.bean.Room;
import com.example.hotel.enums.OrderStatusEnum;
import com.example.hotel.util.MySqliteOpenHelper;
import com.example.hotel.util.SPUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 支付
 */
public class PayActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private Activity activity;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Room room;
    private String startDate;
    private String endData;
    private String name;
    private String phone;
    private String idCard;
    private String roomNum;
    private String remark;
    private Integer userId;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_pay);
        helper = new MySqliteOpenHelper(this);
        image = findViewById(R.id.image);
        room = (Room) getIntent().getSerializableExtra("room");
        startDate = getIntent().getStringExtra("startDate");
        endData = getIntent().getStringExtra("endData");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        idCard = getIntent().getStringExtra("idCard");
        roomNum = getIntent().getStringExtra("roomNum");
        remark = getIntent().getStringExtra("remark");
        userId = (Integer) SPUtils.get(PayActivity.this, SPUtils.USER_ID, 0);
        String imagePath = (String) SPUtils.get(PayActivity.this, SPUtils.PAY_PICTURE, "");
        Glide.with(PayActivity.this)
                .load(imagePath)
                .error(R.drawable.ic_pay)
                .into(image);
    }

    //支付
    public void reserve(View v) {
        Double orderPrice = Integer.valueOf(roomNum) * room.getPrice();
        SQLiteDatabase db = helper.getWritableDatabase();
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage("确认要支付" + orderPrice + "元吗");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String number = "S" + System.currentTimeMillis();//编号
                String orderDate = sf1.format(new Date());//订单日期
                String sql = "insert into orders(roomId, userId,number,startDate, endDate,orderDate,name,phone,roomNum,remark,status,idCard,price) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                db.execSQL(sql, new Object[]{room.getId(), userId, number, startDate, endData, orderDate, name, phone, roomNum, remark, OrderStatusEnum.Reserve.getCode(), idCard,orderPrice});
                String sql1 = "update room set surplus = ? where id =?";
                db.execSQL(sql1, new Object[]{room.getSurplus() - Integer.valueOf(roomNum), room.getId()});
                Toast.makeText(PayActivity.this, "预定成功", Toast.LENGTH_SHORT).show();
                db.close();
                setResult(RESULT_OK);
                finish();
            }
        });
        dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void back(View view) {
        finish();
    }
}
