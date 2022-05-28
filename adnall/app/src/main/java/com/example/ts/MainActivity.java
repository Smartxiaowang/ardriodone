package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.db.DBUtils;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private TextView userid;
    private  Handler handler = new Handler(){
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 1:
                    userid.setText((String) msg.obj);
                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userid = findViewById(R.id.user);
        new Thread(()->{
            DBUtils dbUtils = new DBUtils();
            HashMap test = dbUtils.test();
            Message message = handler.obtainMessage();
            message.what = 1;
            message.obj = test.get("id");
            handler.sendMessage(message);
        }).start();
    }
}