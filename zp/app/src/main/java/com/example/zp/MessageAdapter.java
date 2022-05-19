package com.example.zp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zp.sqlUntils.SqlUntils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<TdInfo> dataList;
    private Context context;

    public MessageAdapter(List<TdInfo> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagelist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TdInfo td = dataList.get(position);
        holder.ltdname.setText(td.getLtdname());
        holder.tdid.setText(td.getId());
        Icon icon = null;
        if (td.getLtdname().equals("百度")) {
            icon = Icon.createWithResource(context, R.mipmap.bd);
        }
        if (td.getLtdname().equals("美团")) {
            icon = Icon.createWithResource(context, R.mipmap.mt);
        }
        if (td.getLtdname().equals("字节跳动")) {
            icon = Icon.createWithResource(context, R.mipmap.dy);
        }
        if (td.getLtdname().equals("阿里")) {
            icon = Icon.createWithResource(context, R.mipmap.al);
        }
        if (td.getLtdname().equals("京东")) {
            icon = Icon.createWithResource(context, R.mipmap.jd);
        }
        holder.pic.setImageIcon(icon);
        holder.place.setText(td.getPlace());
        holder.onespeak.setText("投递" + td.getJob() + "。已经收到您的简历，请静候面试通知。");
        holder.dete.setText(td.getDate());
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ltdname, place, onespeak, dete,tdid;
        private ImageView pic;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ltdname = itemView.findViewById(R.id.ltdname2);
            tdid = itemView.findViewById(R.id.tdid);
            place = itemView.findViewById(R.id.place);
            onespeak = itemView.findViewById(R.id.message_info);
            dete = itemView.findViewById(R.id.dete);
            pic = itemView.findViewById(R.id.pic);
            onespeak.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.setClass(context, TalkActivity.class);
                intent.putExtra("ltdname", ltdname.getText().toString());
                intent.putExtra("tdid", tdid.getText().toString());
                context.startActivity(intent);
            });
        }
    }

}

