package com.example.zp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.ViewHolder> {
    private List<TalkEntity> dataList;
    private Context context;
    public TalkAdapter(List<TalkEntity> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.talklist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TalkEntity talk = dataList.get(position);
        if (talk.getQyoruser().equals("1")) {
            //企业左边
            holder.qytalk.setText(talk.getMyTalk());
            holder.qytalk.setBackgroundResource(R.drawable.round_editstyle);
        }else {
            holder.mytalk.setText(talk.getMyTalk());
            holder.mytalk.setBackgroundResource(R.drawable.round_editstyle);
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView qytalk, mytalk;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            qytalk = itemView.findViewById(R.id.qytalk);
            mytalk = itemView.findViewById(R.id.mytalk);
        }
    }

}

