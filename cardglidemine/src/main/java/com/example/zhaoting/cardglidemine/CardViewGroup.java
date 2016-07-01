package com.example.zhaoting.cardglidemine;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoting on 16/6/30.
 */
public class CardViewGroup extends ViewGroup {
//    private LinearLayout bottomLayout;//存放的是底部的三个按钮
    private List<CardViewItem> mViewList = new ArrayList<>();//存放的是页面上现实的四个View，只能看出来三个是因为，第四个和第三个重合了，只有再第一个滑动时可以看出是四个共存的

    private int bottomMarginTop;
    private int yOffsetStep;
    private static final float SCALE_STEP = 0.08f; // view叠加缩放的步长
//    private int initCenterViewX = 0, initCenterViewY = 0; // 最初时，中间View的x位置,y位置
//    private int childWith = 0; // 每一个子View对应的宽度



    public CardViewGroup(Context context) {
        this(context, null);
    }

    public CardViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.card_group);
        bottomMarginTop = (int) array.getDimension(R.styleable.card_group_bottomMarginTop, 0);
        yOffsetStep = (int) array.getDimension(R.styleable.card_group_yOffsetStep, 0);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 布局卡片view
        int size = mViewList.size();
        for (int i = 0; i < size; i++) {
            View viewItem = mViewList.get(i);
            int childHeight = viewItem.getMeasuredHeight();
            viewItem.layout(l, t, r, t + childHeight);
            int offset = yOffsetStep * i;
            float scale = 1 - SCALE_STEP * i;
            if (i > 2) {
                // 备用的view
                offset = yOffsetStep * 2;
                scale = 1 - SCALE_STEP * 2;
            }

            viewItem.offsetTopAndBottom(offset);
            viewItem.setScaleX(scale);
            viewItem.setScaleY(scale);
        }

//        // 初始化一些中间参数
//        initCenterViewX = mViewList.get(0).getLeft();
//        initCenterViewY = mViewList.get(0).getTop();
//        childWith = mViewList.get(0).getMeasuredWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //最后需要设置的宽和高
        int width = 0;
        int height = 0;

        //child的layoutParams
        MarginLayoutParams childParams = null;

        //获取到最前端的cardItem，默认第一个元素放的是最前端的cardItem
        View childView = getChildAt(0);
        childParams = (MarginLayoutParams) childView.getLayoutParams();
        //宽度为childView的宽度，加上cardViewGroup的padding再加上childView的左右margin
        width = childView.getMeasuredWidth() + getPaddingLeft() + getPaddingRight() + childParams.leftMargin + childParams.rightMargin;
        height = childView.getMeasuredHeight() + getPaddingBottom() + getPaddingTop() + childParams.topMargin + childParams.bottomMargin + 2 * yOffsetStep;
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth
                : width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
                : height);

    }


//    private class DragHelpCallBack extends ViewDragHelper.Callback {
//
//        @Override
//        public boolean tryCaptureView(View child, int pointerId) {
//            //
////            if (child == bottomLayout) {
////                return false;
////            }
////            int childIndex=
//            return false;
//        }
//    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mViewList.clear();
        int num = getChildCount();
        for (int i = num-1; i >=0; i--) {
            CardViewItem childView = (CardViewItem) getChildAt(i);
            mViewList.add(childView);
//            if (childView.getId()==R.id.id_bottom_layout){
//                bottomLayout= (LinearLayout) childView;
//            }
        }
    }
}
