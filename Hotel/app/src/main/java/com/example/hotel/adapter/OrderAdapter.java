package com.example.hotel.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.R;
import com.example.hotel.bean.Orders;
import com.example.hotel.enums.UserTypeEnum;
import com.example.hotel.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单列表适配器
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Activity activity;
    private List<Orders> list = new ArrayList<>();//数据
    private ItemListener mItemListener;

    public void setItemListener(ItemListener itemListener) {
        this.mItemListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //绑定页面
        activity = (Activity) parent.getContext();
        View view = LayoutInflater.from(activity).inflate(R.layout.item_rv_order_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Orders orders = list.get(position);//获取单个物品
        if (orders != null) {//不为空
            holder.tvNumber.setText(orders.getNumber());//编号
            holder.tvRoomNum.setText(String.format("x %s",orders.getRoomNum()));//数量
            holder.tv_price.setText(String.format("¥ %s",orders.getPrice()));//数量
            holder.tvOrderDate.setText(orders.getOrderDate());//下单日期
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItemListener != null) {
                        mItemListener.ItemClick(orders.getId());
                    }
                }
            });
            Integer userType = (Integer) SPUtils.get(activity,SPUtils.USER_TYPE,0);
            //长按
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (mItemListener != null) {
                        if (userType.intValue() == UserTypeEnum.Admin.getCode()){
                            mItemListener.ItemLongClick(orders.getId());
                        }
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();//数据大小
    }

    public void addItem(List<Orders> listAdd) {
        //如果是加载第一页，需要先清空数据列表
        this.list.clear();
        if (listAdd != null) {
            //添加数据
            this.list.addAll(listAdd);
        }
        //通知RecyclerView进行改变--整体
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNumber;
        private TextView tvRoomNum;
        private TextView tvOrderDate;
        private TextView tv_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_number);
            tvRoomNum = itemView.findViewById(R.id.tv_roomNum);
            tvOrderDate = itemView.findViewById(R.id.tv_orderDate);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }

    public interface ItemListener {
        void ItemClick(Integer id);
        void ItemLongClick(int id);
    }
}
