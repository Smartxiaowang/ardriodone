package com.example.hotel.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.R;
import com.example.hotel.adapter.OrderAdapter;
import com.example.hotel.bean.Orders;
import com.example.hotel.enums.UserTypeEnum;
import com.example.hotel.ui.activity.OrderActivity;
import com.example.hotel.util.MySqliteOpenHelper;
import com.example.hotel.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单状态页面
 */
public class OrderStateFragment extends Fragment {
    MySqliteOpenHelper helper = null;
    private Activity myActivity;//上下文
    private int stateId;
    private List<Orders> list;
    private OrderAdapter orderAdapter;
    private RecyclerView rvList;
    private LinearLayout llEmpty;
    private TextView tvOrderPrice;
    private SmartRefreshLayout srlList;//下拉加载，上拉刷新控件
    private Integer userId;
    private Integer userType;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (Activity) context;//设置上下文
        helper = new MySqliteOpenHelper(myActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        Bundle bundle = getArguments();
        stateId = bundle.getInt("stateId");
        userId = (Integer) SPUtils.get(myActivity, SPUtils.USER_ID, 0);
        userType = (Integer) SPUtils.get(myActivity, SPUtils.USER_TYPE, 0);
        view = inflater.inflate(R.layout.fragment_order_state, container, false);
        rvList = view.findViewById(R.id.rv_list);
        llEmpty = view.findViewById(R.id.ll_empty);
        srlList = view.findViewById(R.id.srl_list);
        tvOrderPrice = view.findViewById(R.id.tvOrderPrice);
        LinearLayoutManager layoutManager = new LinearLayoutManager(myActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        orderAdapter = new OrderAdapter();
        rvList.setAdapter(orderAdapter);
        orderAdapter.setItemListener(new OrderAdapter.ItemListener() {
            @Override
            public void ItemClick(Integer id) {
                Intent intent = new Intent(myActivity, OrderActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }

            @Override
            public void ItemLongClick(int id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(myActivity);
                dialog.setMessage("确认要删除该订单吗");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = helper.getWritableDatabase();
                        db.execSQL("delete from orders where roomId=?", new Object[]{ id});
                        db.close();
                        loadData();//加载数据
                    }
                });
                dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        srlList.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadData();
            }
        });
        srlList.autoRefresh();//自动加载
        return view;
    }

    /**
     * 加载数据
     */
    private void loadData() {
        list = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select * from orders where status = " + stateId;
        if (userType.intValue() == UserTypeEnum.User.getCode()) {//普通用户查自己的数据
            sql += " and userId =" + userId;
        }
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getColumnCount() > 0) {
            while (cursor.moveToNext()) {
                Integer id = cursor.getInt(0);//
                Integer roomId = cursor.getInt(1);//房间ID
                Integer userId = cursor.getInt(2);//用户ID
                String number = cursor.getString(3);//编号
                String startDate = cursor.getString(4);//开始日期
                String endDate = cursor.getString(5);//结算日期
                String orderDate = cursor.getString(6);//下单日期
                String name = cursor.getString(7);//姓名
                String phone = cursor.getString(8);//电话
                String roomNum = cursor.getString(9);//房间数量
                String remark = cursor.getString(10);//备注
                Integer status = cursor.getInt(11);//状态
                String idCard = cursor.getString(12);//身份证
                Double price = cursor.getDouble(13);//价格
                Orders orders = new Orders(id, roomId, userId, number, startDate, endDate, orderDate, name, phone, roomNum, remark, status, idCard, price);
                list.add(orders);
            }
        }
        srlList.finishRefresh();//刷新完成
        db.close();
        Double orderPrice = 0.0;
        if (list.size() > 0) {
            orderAdapter.addItem(list);
            for (Orders orders : list) {
                orderPrice += orders.getPrice();
            }
            llEmpty.setVisibility(View.GONE);
            rvList.setVisibility(View.VISIBLE);
        } else {
            llEmpty.setVisibility(View.VISIBLE);
            rvList.setVisibility(View.GONE);
        }
        tvOrderPrice.setText(String.format("合计：%.2f", orderPrice));
    }
}
