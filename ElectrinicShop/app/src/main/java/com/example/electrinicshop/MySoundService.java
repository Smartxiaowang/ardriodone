package com.example.electrinicshop;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MySoundService extends Service {
    //声明一个MediaPlayer引用
    //定义音乐播放器变量
    private MediaPlayer mPlayer;

    @Override
    public void onCreate() {
        mPlayer = MediaPlayer.create(getApplicationContext(),
                //替换成需要的mp3就好了
                R.raw.zhong);
        mPlayer.start();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startId) {
        // TODO Auto-generated method stub
        super.onStartCommand(intent, flag, startId);
        // 开始播放
        mPlayer.start();
        // 允许循环播放
        mPlayer.setLooping(false);

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //先停止 再释放
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
        mPlayer.release();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyMusicBinder();
    }

    class MyMusicBinder extends Binder {
        //返回Service对象
        MySoundService getService() {
            return MySoundService.this;
        }
    }


}


