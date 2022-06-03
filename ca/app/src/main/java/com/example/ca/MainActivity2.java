package com.example.ca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnzero, mBtnone, mBtntwo, mBtnthree, mBtnfour, mBtnfive, mBtnsix, mBtnSeven, mBtnEight, mBntNine;
    private Button mBtnMulti, mBtnspot, mBtndiv, mBtnequal, mBtnclear, mBtnsubtraction, mBtnaddition;
    private Button bt_back;
    private Button ca;
    private Button bin,otc,dec,hex;
    private TextView mTvResult;
    private TextView jinzhihit;
    private TextView b,o,d,h;
    private static StringBuilder s = new StringBuilder();
    private static StringBuilder res = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();


    }

    private void initView() {
        mBtnone = findViewById(R.id.btn_one);
        mBtntwo = findViewById(R.id.btn_two);
        mBtnthree = findViewById(R.id.btn_three);
        mBtnfour = findViewById(R.id.btn_four);
        ca = findViewById(R.id.ca);
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
        bin = findViewById(R.id.bin);
        otc = findViewById(R.id.otc);
        dec = findViewById(R.id.dec);
        hex = findViewById(R.id.hex);

        b = findViewById(R.id.b);
        o = findViewById(R.id.o);
        d = findViewById(R.id.d);
        h = findViewById(R.id.h);

        mBtnone.setOnClickListener(this);

        bin.setOnClickListener(this);
        otc.setOnClickListener(this);
        dec.setOnClickListener(this);
        hex.setOnClickListener(this);

        bt_back.setOnClickListener(this);
        mBtntwo.setOnClickListener(this);
        ca.setOnClickListener(this);
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
            case R.id.bin:
                String [] res2 = changejinzhi(s,2);
                b.setText("BIN:"+res2[1]);
                o.setText("OTC:"+res2[2]);
                h.setText("HEX:"+res2[3]);
                d.setText("DEC:"+res2[0]);
                break;
            case R.id.otc:
                String [] res3 = changejinzhi(s,8);
                b.setText("BIN:"+res3[1]);
                o.setText("OTC:"+res3[2]);
                h.setText("HEX:"+res3[3]);
                d.setText("DEC:"+res3[0]);
                break;
            case R.id.hex:
                String [] res4 = changejinzhi(s,16);
                b.setText("BIN:"+res4[1]);
                o.setText("OTC:"+res4[2]);
                h.setText("HEX:"+res4[3]);
                d.setText("DEC:"+res4[0]);
                break;
            case R.id.dec:
                String [] res5 = changejinzhi(s,10);
                b.setText("BIN:"+res5[1]);
                o.setText("OTC:"+res5[2]);
                h.setText("HEX:"+res5[3]);
                d.setText("DEC:"+res5[0]);
                break;

            case R.id.btn_one:
                mTvResult.setText(res);
                s.append("1");
                mTvResult.setText(s.toString());
                break;
            case R.id.ca:
                Intent intent = new Intent();
                intent.setClass(this,MainActivity.class);
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
                s.append("A");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_zero:
                mTvResult.setText(res);
                s.append("0");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_div:
                mTvResult.setText(res);
                s.append("E");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_spot:
                s.append("D");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_subtraction:
                s.append("B");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_addition:
                s.append("C");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_equal:
                s.append("F");
                mTvResult.setText(s.toString());
                break;
            case R.id.btn_clear:
                s.setLength(0);
                res.setLength(0);
                b.setText("");
                o.setText("");
                h.setText("");
                d.setText("");
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

    private String[] changejinzhi(StringBuilder s,int jz) {
        try {
            int i = Integer.parseInt(s.toString(),jz);
            String bin = Integer.toBinaryString(i);
            String oct = Integer.toOctalString(i);
            String hex = Integer.toHexString(i);

            return new String[] {
                    String.valueOf(i),bin,oct,hex
            };
        }catch (Exception e) {
            return new String[] {
                    "错误", "错误", "错误", "错误"
            };
        }
    }

}