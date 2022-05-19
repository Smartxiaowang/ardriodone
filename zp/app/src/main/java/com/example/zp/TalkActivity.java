package com.example.zp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zp.sqlUntils.SqlUntils;

import java.util.ArrayList;

public class TalkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talk);
        Intent getIntent = getIntent();
        TextView ltdname = findViewById(R.id.ltdname);
        ltdname.setText(getIntent.getStringExtra("ltdname"));
        String tdid = getIntent.getStringExtra("tdid");
        EditText edittalk = findViewById(R.id.edittalk);
        RecyclerView talk = findViewById(R.id.talk);
        talk.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        //talk.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //设置适配器
        talk.setAdapter(new TalkAdapter(new SqlUntils(this).qryTalk(tdid), this));
        ImageView sendtalk = findViewById(R.id.sendtalk);

        sendtalk.setOnClickListener(v -> {
            if (edittalk.getText().equals("")) {
                Toast.makeText(this, "消息不为空", Toast.LENGTH_SHORT).show();

            } else {
                TalkEntity talkEntity2 = new TalkEntity();
                talkEntity2.setMyTalk(edittalk.getText().toString().trim());
                talkEntity2.setLtdname(getIntent.getStringExtra("ltdname"));
                talkEntity2.setQyoruser("2");
                talkEntity2.setTdid(tdid);
                new SqlUntils(this).insertTalkInfo(talkEntity2);
                talk.setAdapter(new TalkAdapter(new SqlUntils(this).qryTalk(tdid), this));
                edittalk.setText("");
            }
        });

    }
}
