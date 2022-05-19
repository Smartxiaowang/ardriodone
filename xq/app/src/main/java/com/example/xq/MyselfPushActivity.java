package com.example.xq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyselfPushActivity extends AppCompatActivity {

    private RecyclerView list;
    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongtai);
        Intent intent = getIntent();
         c = this;
        String uid = intent.getStringExtra("uid");
        List<HomeDataBean> homeDataInfo = new MyHelper(this).getHomeDataInfo(uid);
        MpushAdapter mpushAdapter = new MpushAdapter(homeDataInfo, this, uid);
        list = findViewById(R.id.list_view_dongtai);
        list.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //设置适配器
        list.setAdapter(mpushAdapter);
        mpushAdapter.setOnItemClickListener(position -> {
            HomeDataBean homeDataBean = homeDataInfo.get(position);

            new MyHelper(c).delMessage(homeDataBean.getdId());
            homeDataInfo.remove(position);
            list.setAdapter(new MpushAdapter(homeDataInfo,c,uid));
            Toast.makeText(c, "删除成功", Toast.LENGTH_SHORT).show();
        });

    }


}
