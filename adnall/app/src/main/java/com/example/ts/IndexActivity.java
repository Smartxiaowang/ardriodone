package com.example.ts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.TalkBean;
import com.example.bean.TsBean;
import com.example.db.DBUtils;
import com.example.db.ThreadPollHelp;

import java.lang.reflect.Field;
import java.util.List;

public class IndexActivity extends AppCompatActivity {
    private ImageView end_one, end_two, end_three, end_four;
    private ImageView top_leftimg, top_rightimg;
    private TextView top_text;
    private ConstraintLayout serchcons;
    private RecyclerView list;/*
    private ImageButton like, res;*/
    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 2:
                    List<TsBean> obj = (List<TsBean>) msg.obj;
                    ViewGroup.LayoutParams layoutParams = serchcons.getLayoutParams();
                    //搜索框展示 list.setAdapter(new EveryDayAdapter());
                    layoutParams.height = 120;
                    serchcons.setLayoutParams(layoutParams);
                    list.addItemDecoration(new DividerItemDecoration(IndexActivity.this, LinearLayoutManager.VERTICAL));
                    list.setAdapter(new SerachAdapter(IndexActivity.this, obj));
                    break;
                case 3:
                    TsBean data = (TsBean) msg.obj;
                    //搜索框展示 list.setAdapter(new EveryDayAdapter());
                    //list.addItemDecoration(new DividerItemDecoration(IndexActivity.this, LinearLayoutManager.VERTICAL));
                    EveryDayAdapter everyDayAdapter = new EveryDayAdapter(IndexActivity.this, data);
                    list.setAdapter(everyDayAdapter);
                    everyDayAdapter.setOnItemClickListener(new EveryDayAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick() {
                            scSerach("每日一词");
                        }
                    });
                    break;
                case 4:
                    List<TalkBean> talkBeanList = (List<TalkBean>) msg.obj;
                    list.setAdapter(new TalkAdapter(IndexActivity.this, talkBeanList));
                    break;
                case 5:
                    List<TsBean> tsLikeList = (List<TsBean>) msg.obj;
                    list.setAdapter(new SerachAdapter(IndexActivity.this, tsLikeList));
                    break;
            }

        }
    };

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initTop();
        initZw();
        initEnd();
        list.setLayoutManager(new LinearLayoutManager(this));
        //
        IndexAdapter indexAdapter = new IndexAdapter(this);
        list.setAdapter(indexAdapter);
        //首页数据监听列表跳转
        //文本监听点击了什么
        indexAdapter.setOnItemClickListener(v -> {
            top_text.setText("诗词搜索");
            top_rightimg.setBackgroundResource(R.mipmap.search);
            scSerach(v);
        });
        end_one.setOnClickListener(v -> {
            //
            top_text.setText("诗词分类");
            top_rightimg.setBackgroundResource(0);
            list.setAdapter(indexAdapter);
        });
        //底部诗词搜索按钮
        end_two.setOnClickListener(v -> {
            top_text.setText("诗词搜索");
            top_rightimg.setBackgroundResource(R.mipmap.search);
            scSerach("");
        });
        end_three.setOnClickListener(v -> {
            top_text.setText("每日一词");
            top_rightimg.setBackgroundResource(0);
            scSerach("每日一词");
        });
        end_four.setOnClickListener(v -> {
            top_text.setText("讨论区");
            top_rightimg.setBackgroundResource(R.mipmap.addtalk);
            scSerach("讨论区");
        });
        top_rightimg.setOnClickListener(v -> {
            ImageView imageView = findViewById(R.id.top_rightimg);
            int imgid = 0;
            Field[] fields = top_rightimg.getClass().getDeclaredFields();
            for (Field f : fields) {

                if (f.getName().equals("mBackgroundTintHelper")) {

                    f.setAccessible(true);

                    try {
                        Object obj = f.get(imageView);

                        Field[] fields2 = obj.getClass().getDeclaredFields();

                        for (Field f2 : fields2) {

                            if (f2.getName().equals("mBackgroundResId")) {

                                f2.setAccessible(true);

                                imgid = f2.getInt(obj);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
            if (imgid == R.mipmap.search) {
                //搜索
                SerachZxAdapter serachZxAdapter = new SerachZxAdapter(IndexActivity.this);
                list.setAdapter(serachZxAdapter);
                serachZxAdapter.setOnItemClickListener(gushi_title -> {
                    if (gushi_title.equals("")) {
                        Toast.makeText(IndexActivity.this, "请输入文字", Toast.LENGTH_SHORT).show();
                    } else {
                        scSerach("l" + gushi_title);
                    }
                });
            }
            if (imgid == R.mipmap.addtalk) {
                startActivity(new Intent(this, AddTalkActivity.class));
            }
        });
        /*like.setOnClickListener(v -> {
            Toast.makeText(this, "点赞成功", Toast.LENGTH_SHORT).show();
        });
        res.setOnClickListener(v->{
            scSerach("每日一词");
        });*/
    }

    //封装展示数据方法
    private void scSerach(String v) {
        ThreadPollHelp.getPoll().execute(() -> {
            if (v.equals("每日一词")) {
                TsBean tsInfo = new DBUtils().getTsInfoOne();
                Message message = handler.obtainMessage();
                message.what = 3;
                message.obj = tsInfo;
                handler.sendMessage(message);
            } else if (v.equals("讨论区")) {
                List<TalkBean> talkInfo = new DBUtils().getTalkInfo();
                Message message = handler.obtainMessage();
                message.what = 4;
                message.obj = talkInfo;
                handler.sendMessage(message);
            } else if (v.contains("l")) {
                //古诗搜索
                String tString = v.replace("l", "");
                tString.trim();
                List<TsBean> tsLikeInfo = new DBUtils().getTsTitleLikeInfo(tString);
                Message message = handler.obtainMessage();
                message.what = 5;
                message.obj = tsLikeInfo;
                handler.sendMessage(message);
            } else {
                List<TsBean> tsInfo = new DBUtils().getTsInfo(v);
                Message message = handler.obtainMessage();
                message.what = 2;
                message.obj = tsInfo;
                handler.sendMessage(message);
            }
        });
    }

    private void initZw() {
        list = findViewById(R.id.list);/*
        like = findViewById(R.id.like);
        res = findViewById(R.id.res);*/
    }

    private void initEnd() {
        end_one = findViewById(R.id.iv_one);
        end_two = findViewById(R.id.iv_two);
        end_three = findViewById(R.id.iv_three);
        end_four = findViewById(R.id.iv_four);
    }

    private void initTop() {
        top_leftimg = findViewById(R.id.top_leftimg);
        top_rightimg = findViewById(R.id.top_rightimg);
        top_text = findViewById(R.id.top_text);
        serchcons = findViewById(R.id.serchcons);
    }
}
