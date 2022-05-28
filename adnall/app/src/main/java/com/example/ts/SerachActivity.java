package com.example.ts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//meiyong
public class SerachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seach);
        EditText jl = findViewById(R.id.gs_title);
        ImageButton search = findViewById(R.id.serch);
        search.setOnClickListener(v -> {
            String ltdname = jl.getText().toString();
            if (ltdname.equals("")) {
                Toast.makeText(this, "诗词内容不为空", Toast.LENGTH_SHORT).show();

            }else {
                //搜索结果页面
                Intent intent = new Intent();
                intent.putExtra("scnr",ltdname);
                intent.setClass(this,IndexActivity.class);
                startActivity(intent);
            }
        });
    }

}
