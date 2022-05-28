package com.example.hotel.ui.activity;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hotel.R;
import com.example.hotel.bean.Room;
import com.example.hotel.util.GlideEngine;
import com.example.hotel.util.MySqliteOpenHelper;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.List;


/**
 * 新增或者修改房间
 */
public class AddActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private EditText etTitle;//标题
    private ImageView ivImage;//图片
    private Spinner sp_type;
    private EditText etSize;//房间大小
    private EditText etBed;//床
    private EditText etQuantity;//总数量
    private EditText etSurplus;//剩余数量
    private EditText etPrice;//价格
    private EditText etRemark;//备注
    private String imagePath = "";
    private Room room;//房间信息
    private RequestOptions headerRO = new RequestOptions().circleCrop();//圆角变换

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        helper = new MySqliteOpenHelper(this);
        etTitle = findViewById(R.id.et_title);
        sp_type = findViewById(R.id.sp_type);
        ivImage = findViewById(R.id.iv_photo);
        etSize = findViewById(R.id.et_size);
        etBed = findViewById(R.id.et_bed);
        etQuantity = findViewById(R.id.et_quantity);
        etSurplus = findViewById(R.id.et_surplus);
        etPrice = findViewById(R.id.et_price);
        etRemark = findViewById(R.id.et_remark);
        room = (Room) getIntent().getSerializableExtra("room");
        if (room != null) {
            sp_type.setSelection(room.getTypeId());
            etTitle.setText(room.getTitle());
            etSize.setText(room.getSize());
            etBed.setText(room.getBed());
            etQuantity.setText(String.valueOf(room.getQuantity()));
            etSurplus.setText(String.valueOf(room.getSurplus()));
            etPrice.setText(String.valueOf(room.getPrice()));
            etRemark.setText(String.valueOf(room.getRemark()));
            imagePath = room.getImage();
            Glide.with(AddActivity.this)
                    .load(room.getImage())
                    .into(ivImage);
        }
        //从相册中选择头像
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectClick();
            }
        });
    }

    /**
     * 新增或者修改房间信息
     */
    public void save(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取请求参数
        int typeId = sp_type.getSelectedItemPosition();
        String title = etTitle.getText().toString();
        String size = etSize.getText().toString();
        String bed = etBed.getText().toString();
        String quantity = etQuantity.getText().toString();
        String surplus = etSurplus.getText().toString();
        String price = etPrice.getText().toString();
        String remark = etRemark.getText().toString();
        if ("".equals(imagePath)) {//请上传图片
            Toast.makeText(AddActivity.this, "请上传图片", Toast.LENGTH_LONG).show();
            return;
        }
        if (typeId == 0) {
            Toast.makeText(AddActivity.this, "请选择类型", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(title)) {//房间号不能为空
            Toast.makeText(AddActivity.this, "房间号不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(size)) {//房间大小不能为空
            Toast.makeText(AddActivity.this, "房间大小不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(bed)) {//床型不能为空
            Toast.makeText(AddActivity.this, "床型不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(quantity)) {//总数量不能为空
            Toast.makeText(AddActivity.this, "总数量不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(surplus)) {//剩余数量不能为空
            Toast.makeText(AddActivity.this, "剩余数量不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if ("".equals(price)) {//价格不能为空
            Toast.makeText(AddActivity.this, "价格不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (room == null) {//新增
            String insertSql = "insert into room(typeId,title, image,size,bed, quantity,surplus,price,remark) values(?,?,?,?,?,?,?,?,?)";
            db.execSQL(insertSql, new Object[]{typeId, title, imagePath, size, bed, quantity, surplus, price, remark});
            Toast.makeText(AddActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        } else {//修改
            String insertSql = "update room set typeId= ?,title=?, image=?,size=?,bed=?, quantity=?,surplus=?,price=?,remark=? where id =?";
            db.execSQL(insertSql, new Object[]{typeId, title, imagePath, size, bed, quantity, surplus, price, remark, room.getId()});
            Toast.makeText(AddActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
        }
        setResult(RESULT_OK);
        finish();
    }

    /**
     * 选择图片
     */
    private void selectClick() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofAll())
                .imageEngine(GlideEngine.createGlideEngine())
                .maxSelectNum(1)
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        for (int i = 0; i < result.size(); i++) {
                            // onResult Callback
                            LocalMedia media = result.get(i);
                            String path;
                            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                            boolean compressPath = media.isCompressed() || (media.isCut() && media.isCompressed());
                            // 裁剪过
                            boolean isCutPath = media.isCut() && !media.isCompressed();
                            if (isCutPath) {
                                path = media.getCutPath();
                            } else if (compressPath) {
                                path = media.getCompressPath();
                            } else if (!TextUtils.isEmpty(media.getAndroidQToPath())) {
                                // AndroidQ特有path
                                path = media.getAndroidQToPath();
                            } else if (!TextUtils.isEmpty(media.getRealPath())) {
                                // 原图
                                path = media.getRealPath();
                            } else {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                    path = PictureFileUtils.getPath(AddActivity.this, Uri.parse(media.getPath()));
                                } else {
                                    path = media.getPath();
                                }
                            }
                            imagePath = path;
                        }
                        Glide.with(AddActivity.this)
                                .load(imagePath)
                                .into(ivImage);
                    }

                    @Override
                    public void onCancel() {
                        // onCancel Callback
                    }
                });
    }

    //返回
    public void back(View view) {
        finish();
    }
}
