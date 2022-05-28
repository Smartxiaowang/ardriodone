package com.example.ts;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.bean.GlideEngine;
import com.example.bean.TalkBean;
import com.example.bean.TsBean;
import com.example.db.DBUtils;
import com.example.db.ThreadPollHelp;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.List;

public class AddTalkActivity extends AppCompatActivity {
    private TextView choose_photo;
    private EditText et_fabiao;
    private Button btn_fabiao;
    private ImageView imageView;
    private String imagePath;
    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 12:
                    String res = (String) msg.obj;
                    Toast.makeText(AddTalkActivity.this, res, Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtalk);
        choose_photo = findViewById(R.id.btn_photo);
        et_fabiao = findViewById(R.id.et_fabiao);
        btn_fabiao = findViewById(R.id.btn_fabiao);
        imageView = (ImageView) findViewById(R.id.image_show);
        choose_photo.setOnClickListener(v -> {
            selectClick();
        });
        btn_fabiao.setOnClickListener(v -> {
            String nr = et_fabiao.getText().toString();
            //imagePath;
            ThreadPollHelp.getPoll().execute(() -> {
                String res = new DBUtils().insertTalk("用户名", nr, imagePath);
                Message message = handler.obtainMessage();
                message.what = 12;
                message.obj = res;
                handler.sendMessage(message);
            });


        });

    }

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
                                    path = PictureFileUtils.getPath(AddTalkActivity.this, Uri.parse(media.getPath()));
                                } else {
                                    path = media.getPath();
                                }
                            }
                            imagePath = path;
                        }
                        Glide.with(AddTalkActivity.this)
                                .load(imagePath)
                                .into(imageView);
                    }

                    @Override
                    public void onCancel() {
                        // onCancel Callback
                    }
                });
    }
}