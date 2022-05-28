package com.example.hotel.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotel.R;
import com.example.hotel.bean.Room;
import com.example.hotel.enums.UserTypeEnum;
import com.example.hotel.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页列表适配器
 */
public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private Activity activity;
    private List<Room> list = new ArrayList<>();//数据
    private ItemListener mItemListener;

    public void setItemListener(ItemListener itemListener) {
        this.mItemListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //绑定页面
        activity = (Activity) parent.getContext();
        View view = LayoutInflater.from(activity).inflate(R.layout.item_rv_room_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Room room = list.get(position);//获取单个物品
        if (room != null) {//不为空
            holder.tvName.setText(room.getTitle());//名称
            holder.tvPrice.setText(String.format("¥%.2f", room.getPrice()));//价格
            Glide.with(activity)
                    .load(room.getImage())
                    .into(holder.ivPhoto);
            //点击
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemListener != null) {
                        mItemListener.ItemClick(room);
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
                            mItemListener.ItemLongClick(room.getId());
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

    public void addItem(List<Room> listAdd) {
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
        private ImageView ivPhoto;
        private TextView tvName;
        private TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }

    public interface ItemListener {
        void ItemClick(Room room);
        void ItemLongClick(int id);
    }
}
