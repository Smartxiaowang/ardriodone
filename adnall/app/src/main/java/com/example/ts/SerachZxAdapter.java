package com.example.ts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.TsBean;

import java.util.List;

public class SerachZxAdapter extends RecyclerView.Adapter<SerachZxAdapter.ViewHolder> {
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    public SerachZxAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.serch.setOnClickListener(v->{
            mOnItemClickListener.onItemClick(holder.title.getText().toString());
        });
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(String title);
    }
    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private EditText title;

        private ImageView serch;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.gs_title);
            serch = itemView.findViewById(R.id.serch);


        }
    }

}

