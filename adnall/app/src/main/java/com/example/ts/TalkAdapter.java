package com.example.ts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bean.TalkBean;
import com.example.bean.TsBean;

import java.util.List;

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.ViewHolder> {
    private List<TalkBean> dataList;
    private Context context;

    public TalkAdapter(Context context, List<TalkBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_talkabout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TalkBean talkBean = dataList.get(position);
        holder.body.setText(talkBean.getNr());
        holder.title_info.setText(talkBean.getUsername());
        Glide.with(context)
                .load(talkBean.getImageurl())
                .into(holder.pubish_img);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title_info, body, classifyname;
        private ImageView like, commit, send, pubish_img;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_info = itemView.findViewById(R.id.title_info);
            body = itemView.findViewById(R.id.body);
            pubish_img = itemView.findViewById(R.id.pubish_img);
            classifyname = itemView.findViewById(R.id.classifyname);
            like = itemView.findViewById(R.id.like);
            send = itemView.findViewById(R.id.send);
            commit = itemView.findViewById(R.id.commit);
            like.setOnClickListener(v -> {
                Toast.makeText(context, "点赞", Toast.LENGTH_SHORT).show();
            });
            send.setOnClickListener(v -> {
                Toast.makeText(context, "分享", Toast.LENGTH_SHORT).show();
            });
            commit.setOnClickListener(v -> {
                Toast.makeText(context, "评论", Toast.LENGTH_SHORT).show();
            });
            classifyname.setOnClickListener(v -> {
                Toast.makeText(context, "收藏", Toast.LENGTH_SHORT).show();
            });
        }
    }

}

