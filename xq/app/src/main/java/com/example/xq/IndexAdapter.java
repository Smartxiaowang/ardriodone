package com.example.xq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {
    private List<HomeDataBean> data;
    private Context context;
    private String id;

    public IndexAdapter(List<HomeDataBean> userInfo, Context context) {
        this.data = userInfo;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeDataBean hdb = data.get(position);
        holder.jj.setText(hdb.getJj());
        holder.account.setText(hdb.getNickname());
        holder.zw.setText("     "+hdb.getDremcontent());
        holder.d_id.setText(hdb.getdId());
        holder.starts.setText(hdb.getStarts());
        holder.dateup.setText(hdb.getDateup());
        holder.bq.setText("  "+hdb.getBq());
        if (hdb.getSex().equals("女")) {
            holder.sex.setImageResource(R.mipmap.v);
            holder.hometx.setImageResource(R.drawable.txv);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView account, jj, zw, starts,d_id,dateup,bq;
        private ImageView tostarts,sex,hometx;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            account = itemView.findViewById(R.id.account);

            jj = itemView.findViewById(R.id.jj);
            zw = itemView.findViewById(R.id.zw);
            tostarts = itemView.findViewById(R.id.tostarts);
            d_id = itemView.findViewById(R.id.d_id);
            bq = itemView.findViewById(R.id.bq);
            dateup = itemView.findViewById(R.id.dateup);
            starts = itemView.findViewById(R.id.starts);
            hometx = itemView.findViewById(R.id.hometx);
            sex = itemView.findViewById(R.id.sex);
            tostarts.setOnClickListener(v -> {
                new MyHelper(context).toStarts(d_id.getText().toString());
                String s = starts.getText().toString();
                starts.setText(String.valueOf(Integer.parseInt(s)+1));
                Toast.makeText(context,"点赞成功", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
