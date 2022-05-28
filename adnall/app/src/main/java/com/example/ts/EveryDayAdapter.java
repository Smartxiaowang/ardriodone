package com.example.ts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.TsBean;

import java.util.List;

public class EveryDayAdapter extends RecyclerView.Adapter<EveryDayAdapter.ViewHolder> {
    private TsBean data;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    public EveryDayAdapter(Context context, TsBean datas) {
        this.context = context;
        this.data = datas;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_everyday_list, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*TsBean tsBean = dataList.get(position);*/
        TsBean tsBean = data;
        holder.author.setText(tsBean.getAuthor());
        holder.title.setText(tsBean.getTitle());
        holder.classifyname.setText(tsBean.getClassifyname());
        holder.body.setText(tsBean.getBody());
        holder.like.setOnClickListener(v->{
                Toast.makeText(context, "点赞成功", Toast.LENGTH_SHORT).show();
        });
        holder.res.setOnClickListener(v->{
            mOnItemClickListener.onItemClick();
            Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
        });
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick();
    }

    @Override
    public int getItemCount() {

        return 1;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, author, classifyname, body;
        private ImageButton like, res;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_info);
            author = itemView.findViewById(R.id.author);
            classifyname = itemView.findViewById(R.id.classifyname);
            body = itemView.findViewById(R.id.body);
            like = itemView.findViewById(R.id.like);
            res = itemView.findViewById(R.id.res);

        }
    }

}

