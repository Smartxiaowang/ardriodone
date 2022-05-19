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

import com.example.zp.sqlUntils.SqlUntils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {
    private List<ZpInfoEntity> dataList;
    private Context context;
    private String userName;

    public IndexAdapter(List<ZpInfoEntity> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;

    }

    public IndexAdapter(List<ZpInfoEntity> dataList, Context context, String userName) {
        this.dataList = dataList;
        this.context = context;
        this.userName = userName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gangweilist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ZpInfoEntity zp = dataList.get(position);
        holder.ltdname.setText(zp.getLtdname());
        holder.salary.setText(zp.getSalary());
        holder.job.setText("岗位：" + zp.getJob());
        holder.place.setText("     " + "工作地点：" + zp.getPlace());
        holder.onespeak.setText("简介：" + zp.getOnespeak());
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ltdname, salary, job, place, onespeak;
        private ImageButton td;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ltdname = itemView.findViewById(R.id.ltdname);
            salary = itemView.findViewById(R.id.salary);
            job = itemView.findViewById(R.id.job);
            place = itemView.findViewById(R.id.place);
            onespeak = itemView.findViewById(R.id.onespeak);
            td = itemView.findViewById(R.id.t2d);
            td.setOnClickListener(v -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                TdInfo tdInfo = new TdInfo(job.getText().toString().trim(), userName, place.getText().toString().trim(),
                        ltdname.getText().toString().trim(), salary.getText().toString().trim(), simpleDateFormat.format(new Date()));
                long tdId = new SqlUntils(context).insertTdInfo(tdInfo);
                TalkEntity talkEntity = new TalkEntity();
                talkEntity.setTdid(String.valueOf(tdId));
                talkEntity.setMyTalk("投递" + job.getText().toString().trim() + "已经收到您的简历,请静候面试通知");
                talkEntity.setQyoruser("1");
                talkEntity.setLtdname(ltdname.getText().toString().trim());
                new SqlUntils(context).insertTalkInfo(talkEntity);
                Toast.makeText(context, "投递成功", Toast.LENGTH_SHORT).show();
            });
        }
    }

}

