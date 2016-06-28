package com.example.zhaoting.getapkandshare;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhaoting on 16/6/28.
 */
class ApkHolder extends RecyclerView.ViewHolder {
    ImageView icon;
    TextView label;
    TextView size;
    TextView time;
    OnItemClickListener mOnItemClickListener;

    public ApkHolder(View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.id_icon);
        label = (TextView) itemView.findViewById(R.id.id_label);
        size = (TextView) itemView.findViewById(R.id.id_size);
        time = (TextView) itemView.findViewById(R.id.id_time);

        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getLayoutPosition();
                    mOnItemClickListener.onItemClick(v,pos);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
