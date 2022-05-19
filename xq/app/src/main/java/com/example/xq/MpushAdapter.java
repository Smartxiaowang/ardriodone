package com.example.xq;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MpushAdapter extends RecyclerView.Adapter<MpushAdapter.ViewHolder> {
    private List<HomeDataBean> data;
    private Context context;
    private String id;
    private OnItemClickListener mOnItemClickListener;

    public MpushAdapter(List<HomeDataBean> userInfo, Context context, String id) {
        this.data = userInfo;
        this.context = context;
        this.id = id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dongtai_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeDataBean hdb = data.get(position);
        holder.jj.setText(hdb.getJj());
        holder.account.setText(hdb.getNickname());
        holder.zw.setText(hdb.getDremcontent());
        holder.d_id.setText(hdb.getdId());
        holder.ll_dongtai_delete.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView account, jj, zw, d_id;
        private LinearLayout ll_dongtai_delete;

        @SuppressLint("NotifyDataSetChanged")
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            account = itemView.findViewById(R.id.account);
            jj = itemView.findViewById(R.id.jj);
            zw = itemView.findViewById(R.id.zw);
            d_id = itemView.findViewById(R.id.d_id);
            ll_dongtai_delete = itemView.findViewById(R.id.ll_dongtai_delete);

        }
    }
}
