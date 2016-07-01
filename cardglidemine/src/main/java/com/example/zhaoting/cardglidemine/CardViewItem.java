package com.example.zhaoting.cardglidemine;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhaoting on 16/6/30.
 */
public class CardViewItem extends CardView {
    private ImageView mTopImg;
    private TextView mCenterText;
    private ImageView mbottomImg;
    private ImageView mTimeImg;

    public CardViewItem(Context context) {
        this(context, null);
    }

    public CardViewItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.card_view_item, this);
        mTopImg = (ImageView) findViewById(R.id.id_top_img);
        mCenterText = (TextView) findViewById(R.id.id_center);
        mbottomImg = (ImageView) findViewById(R.id.id_author_name);
        mTimeImg = (ImageView) findViewById(R.id.id_time);
    }


}
