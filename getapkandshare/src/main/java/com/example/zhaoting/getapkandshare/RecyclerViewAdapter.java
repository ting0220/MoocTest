package com.example.zhaoting.getapkandshare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoting on 16/6/28.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<ApkHolder> {
    private List<Bean> mList = new ArrayList<>();
    private ApkHolder.OnItemClickListener mOnItemClickListener;

    @Override
    public ApkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ApkHolder holder = new ApkHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ApkHolder holder, int position) {
        holder.icon.setImageDrawable(mList.get(position).getIcon());
        holder.label.setText(mList.get(position).getName());
        holder.size.setText(String.valueOf(mList.get(position).getSize()) + "KB");
        holder.time.setText(String.valueOf(mList.get(position).getTime()));
        if (mOnItemClickListener != null) {
            holder.setOnItemClickListener(mOnItemClickListener);
        }

    }

    public void setOnItemClickListener(ApkHolder.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<Bean> list) {
        mList = list;
        notifyDataSetChanged();
    }


}
