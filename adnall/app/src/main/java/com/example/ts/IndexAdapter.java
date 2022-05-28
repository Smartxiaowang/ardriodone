package com.example.ts;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {

    private Context context;
    private OnItemClickListener mOnItemClickListener;


    public IndexAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.indexlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.index1.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(holder.index1.getText().toString());
        });
        holder.index2.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(holder.index2.getText().toString());
        });
        holder.index3.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(holder.index3.getText().toString());
        });
        holder.index4.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(holder.index4.getText().toString());
        });
        holder.index5.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(holder.index5.getText().toString());
        });
        holder.index6.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(holder.index6.getText().toString());
        });
        holder.index7.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(holder.index7.getText().toString());
        });
        holder.index8.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(holder.index8.getText().toString());
        });
        holder.index9.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(holder.index9.getText().toString());
        });
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(String lb);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView index1, index2, index3, index4, index5,
                index6, index7, index8, index9, index10;
        private ImageView pic;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            index1 = itemView.findViewById(R.id.index1);
            index2 = itemView.findViewById(R.id.index2);
            index3 = itemView.findViewById(R.id.index3);
            index4 = itemView.findViewById(R.id.index4);
            index5 = itemView.findViewById(R.id.index5);
            index6 = itemView.findViewById(R.id.index6);
            index7 = itemView.findViewById(R.id.index7);
            index8 = itemView.findViewById(R.id.index8);
            index9 = itemView.findViewById(R.id.index9);
            index10 = itemView.findViewById(R.id.index10);
        }


    }

}

