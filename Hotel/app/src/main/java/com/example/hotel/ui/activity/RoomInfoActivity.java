package com.example.hotel.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hotel.R;
import com.example.hotel.bean.Room;
import com.example.hotel.enums.RoomTypeEnum;
import com.example.hotel.util.MySqliteOpenHelper;

/**
 * 房间信息
 */
public class RoomInfoActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private TextView tvTitle;//标题
    private ImageView ivImage;//图片
    private TextView tvSize;//房间大小
    private TextView tvBed;//床
    private TextView tvQuantity;//总数量
    private TextView tvSurplus;//剩余数量
    private TextView tvPrice;//价格
    private TextView tvRemark;//备注
    private Room room;//信息

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);
        tvTitle = findViewById(R.id.tv_title);
        tvSize = findViewById(R.id.tv_size);
        tvBed = findViewById(R.id.tv_bed);
        tvQuantity = findViewById(R.id.tv_quantity);
        tvSurplus = findViewById(R.id.tv_surplus);
        tvPrice = findViewById(R.id.tv_price);
        tvRemark = findViewById(R.id.tv_remark);
        ivImage = findViewById(R.id.iv_photo);
        room = (Room) getIntent().getSerializableExtra("room");
        if (room != null){
            tvTitle.setText(String.format("%s(%s)",room.getTitle(), RoomTypeEnum.getName(room.getTypeId())));
            tvSize.setText(String.format("房间大小:%s",room.getSize()));
            tvBed.setText(String.format("床型:%s",room.getBed()));
            tvQuantity.setText(String.format("总数量:%s",room.getQuantity()));
            tvSurplus.setText(String.format("剩余数量:%s",room.getSurplus()));
            tvPrice.setText(String.format("¥:%.2f",room.getPrice()));
            tvRemark.setText(String.format("备注:%s",room.getRemark()));
            Glide.with(RoomInfoActivity.this)
                    .load(room.getImage())
                    .into(ivImage);
        }
    }

    //预定
    public void reserve(View view){
        Intent intent = new Intent(RoomInfoActivity.this,ReserveActivity.class);
        intent.putExtra("room",room);
        startActivityForResult(intent,100);
        finish();
    }

    //返回
    public void back(View view) {
        finish();
    }

}
