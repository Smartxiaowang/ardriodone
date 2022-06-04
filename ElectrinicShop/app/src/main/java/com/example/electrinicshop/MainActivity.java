package com.example.electrinicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView title, shopname, shopprice;
    private EditText num;
    private Button buyit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        shopname = findViewById(R.id.shopname);
        shopprice = findViewById(R.id.shopprice);
        num = findViewById(R.id.num);
        buyit = findViewById(R.id.buyit);
        buyit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = num.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("shopname",shopname.getText().toString().trim());
                intent.putExtra("shopprice",shopprice.getText().toString().trim());
                intent.putExtra("num",text);
                intent.setClass(MainActivity.this, ElectronicOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}