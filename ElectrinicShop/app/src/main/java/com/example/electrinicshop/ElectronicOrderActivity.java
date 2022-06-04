package com.example.electrinicshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.HashMap;

public class ElectronicOrderActivity extends AppCompatActivity {

    private TextView  shopname, shopprice, endprice,num;
    private Button buyit;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        String sn = intent.getStringExtra("shopname");
        String sp = intent.getStringExtra("shopprice");
        String n = intent.getStringExtra("num");
        BigDecimal bigDecimal = new BigDecimal(sp.trim());
        BigDecimal endpr = bigDecimal.multiply(new BigDecimal(n));

        shopname = findViewById(R.id.shopname);
        shopname.setText(sn);
        shopprice = findViewById(R.id.shopprice);
        endprice = findViewById(R.id.endprice);
        endprice.setText(endpr.toString());
        shopprice.setText(sp);
        num = findViewById(R.id.numorder);
        num.setText(n);
        buyit = findViewById(R.id.buyitnow);
        buyit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> data = new HashMap<>();
                data.put("electronic_name", sn);
                data.put("electronic_price", sp);
                data.put("amount", endpr.toString());
                new MyOpenHelper(ElectronicOrderActivity.this).insertOrder(data);
                Intent intent1 = new Intent();
                intent1.setClass(ElectronicOrderActivity.this, ConfirmOkActivity.class);
                startActivity(intent1);
            }
        });
    }
}