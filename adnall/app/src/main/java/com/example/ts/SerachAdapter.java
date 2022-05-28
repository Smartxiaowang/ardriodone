package com.example.ts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.TsBean;

import java.util.List;

public class SerachAdapter extends RecyclerView.Adapter<SerachAdapter.ViewHolder> {
    private List<TsBean> dataList;
    private Context context;

    public SerachAdapter(Context context, List<TsBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_seach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TsBean tsBean = dataList.get(position);
        holder.author.setText(tsBean.getAuthor());
        holder.title.setText(tsBean.getTitle());
        holder.classifyname.setText(tsBean.getClassifyname());
        holder.body.setText(tsBean.getBody());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, author, classifyname, body;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_info);
            author = itemView.findViewById(R.id.author);
            classifyname = itemView.findViewById(R.id.classifyname);
            body = itemView.findViewById(R.id.body);
        }
    }

}

