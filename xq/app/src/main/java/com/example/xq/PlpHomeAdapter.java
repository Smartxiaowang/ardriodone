package com.example.xq;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class PlpHomeAdapter extends RecyclerView.Adapter<PlpHomeAdapter.ViewHolder> {
    private List<HashMap<String, String>> data;
    private Context context;

    public PlpHomeAdapter(List<HashMap<String, String>> dateInfo, Context context) {
        this.data = dateInfo;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plp_home_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String, String> hdb = data.get(position);
        holder.jj.setText(hdb.get("jj"));
        holder.account.setText(hdb.get("nickname"));
        holder.dateup.setText(hdb.get("dateup"));
        holder.zw.setText("  "+hdb.get("nr"));
        holder.jj.setText(hdb.get("jj"));

        if (hdb.get("sex").equals("女")) {
            holder.sex.setImageResource(R.mipmap.v);
            holder.hometx.setImageResource(R.drawable.txv);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView account, jj, zw,dateup;
        private ImageView sex,hometx;

        @SuppressLint("NotifyDataSetChanged")
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            jj = itemView.findViewById(R.id.jj);
            account = itemView.findViewById(R.id.account);
            zw = itemView.findViewById(R.id.zw);
            dateup = itemView.findViewById(R.id.dateup);
            sex = itemView.findViewById(R.id.sex);
            hometx = itemView.findViewById(R.id.hometx);
        }
    }
}
