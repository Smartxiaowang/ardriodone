package com.example.ca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnzero, mBtnone, mBtntwo, mBtnthree, mBtnfour, mBtnfive, mBtnsix, mBtnSeven, mBtnEight, mBntNine;
    private Button mBtnMulti, mBtnspot, mBtndiv, mBtnequal, mBtnclear, mBtnsubtraction, mBtnaddition;
    private Button bt_back;
    private Button cas;
    private TextView mTvResult;
    private static StringBuilder s = new StringBuilder();
    private static StringBuilder res = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mBtnone = findViewById(R.id.btn_one);
        mBtntwo = findViewById(R.id.btn_two);
        mBtnthree = findViewById(R.id.btn_three);
        mBtnfour = findViewById(R.id.btn_four);
        cas = findViewById(R.id.ca1);
        mBtnfive = findViewById(R.id.btn_five);
        mBtnsix = findViewById(R.id.btn_six);
        mBtnSeven = findViewById(R.id.btn_seven);
        mBtnEight = findViewById(R.id.btn_eight);
        mBntNine = findViewById(R.id.btn_nine);
        mBtnMulti = findViewById(R.id.btn_multi);
        mBtnspot = findViewById(R.id.btn_spot);
        mBtndiv = findViewById(R.id.btn_div);
        mBtnequal = findViewById(R.id.btn_equal);
        mBtnclear = findViewById(R.id.btn_clear);
        bt_back = findViewById(R.id.bt_back);
        mBtnzero = findViewById(R.id.btn_zero);
        mBtnsubtraction = findViewById(R.id.btn_subtraction);
        mBtnaddition = findViewById(R.id.btn_addition);
        mTvResult = findViewById(R.id.tv_result);

        mBtnone.setOnClickListener(this);
        bt_back.setOnClickListener(this);
        mBtntwo.setOnClickListener(this);
        cas.setOnClickListener(this);
        mBtnthree.setOnClickListener(this);
        mBtnfour.setOnClickListener(this);
        mBtnfive.setOnClickListener(this);
        mBtnsix.setOnClickListener(this);
        mBtnSeven.setOnClickListener(this);
        mBtnEight.setOnClickListener(this);
        mBntNine.setOnClickListener(this);
        mBtnMulti.setOnClickListener(this);
        mBtnspot.setOnClickListener(this);
        mBtndiv.setOnClickListener(this);
        mBtnequal.setOnClickListener(this);
        mBtnclear.setOnClickListener(this);
        mBtnzero.setOnClickListener(this);
        mBtnsubtraction.setOnClickListener(this);
        mBtnaddition.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                mTvResult.setText(res);
                s.append("1");
                mTvResult.setText(s.toString());
                break;
            case R.id.ca1:
                Intent intent = new Intent();
                intent.setClass(this,MainActivity2.class);
                startActivity(intent);
                break;
            case R.id.btn_two:
                mTvResult.setText(res);
                s.append("2");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_three:
                mTvResult.setText(res);
                s.append("3");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_four:
                mTvResult.setText(res);
                s.append("4");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_five:
                mTvResult.setText(res);
                s.append("5");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_six:
                mTvResult.setText(res);
                s.append("6");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_seven:
                mTvResult.setText(res);
                s.append("7");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_eight:
                mTvResult.setText(res);
                s.append("8");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_nine:
                mTvResult.setText(res);
                s.append("9");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_multi:
                mTvResult.setText(res);
                s.append("x");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_zero:
                mTvResult.setText(res);
                s.append("0");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_div:
                mTvResult.setText(res);
                s.append("÷");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_spot:
                mTvResult.setText(res);
                s.append(".");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_subtraction:
                mTvResult.setText(res);
                s.append("-");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_addition:
                mTvResult.setText(res);
                s.append("+");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_equal:
                res.append(s);
                String res2 = quickCa(s);
                res.append("=");
                res.append(res2);
                res.append("\n");
                mTvResult.setText(res);
                s.setLength(0);
                break;
            case R.id.btn_clear:
                s.setLength(0);
                res.setLength(0);
                mTvResult.setText("");
                break;
            case R.id.bt_back:
                mTvResult.setText(res);
                int length = s.length();
                if (length != 0) {
                    s.deleteCharAt(length - 1);
                    mTvResult.setText(s.toString());
                }
                break;

        }

    }

    private String quickCa(StringBuilder b2) {
        StringBuilder s = b2;
        try {
            StringBuilder after = new StringBuilder();
            StringBuilder befro = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '+') {
                    befro.append(s.delete(0, i+1));
                    BigDecimal a = new BigDecimal(after.toString());
                    BigDecimal b = new BigDecimal(befro.toString());
                    return a.add(b).toString();
                }
                if (s.charAt(i) == '÷') {
                    befro.append(s.delete(0, i+1));
                    BigDecimal a = new BigDecimal(after.toString());
                    BigDecimal b = new BigDecimal(befro.toString());
                    return a.divide(b).toString();
                }
                if (s.charAt(i) == 'x') {
                    befro.append(s.delete(0, i+1));
                    BigDecimal a = new BigDecimal(after.toString());
                    BigDecimal b = new BigDecimal(befro.toString());
                    return a.multiply(b).toString();
                }
                if (s.charAt(i) == '-') {
                    befro.append(s.delete(0, i+1));
                    BigDecimal a = new BigDecimal(after.toString());
                    BigDecimal b = new BigDecimal(befro.toString());
                    return a.subtract(b).toString();
                }
                after.append(s.charAt(i));
            }
            return after.toString();
        } catch (Exception e) {
            return "错误";
        }
    }
}