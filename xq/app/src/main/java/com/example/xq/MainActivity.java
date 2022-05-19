package com.example.xq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yindao);
        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);
        imageView.setImageResource(R.drawable.yd);
        textView.setText("心情日记");
        Intent intent = new Intent();
        intent.setClass(this,LoginActivity.class);
            Handler handler=new Handler();
            handler.postDelayed(() -> {
                startActivity(intent);
                finish();
            },4000);
    }
}