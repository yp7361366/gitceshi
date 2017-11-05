package com.example.myvediodemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by yipan on 2017/11/5.
 */

public class MyPopListView extends ListView {

    public MyPopListView(Context context) {
        super(context);
    }

    public MyPopListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyPopListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMaxWidthOfChildren() + getPaddingLeft() + getPaddingRight();//计算listview的宽度
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), heightMeasureSpec);//设置listview的宽高

    }

    /**
     * 计算item的最大宽度
     *
     * @return
     */
    private int getMaxWidthOfChildren() {
        int maxWidth = 0;
        View view = null;
        int count = getAdapter().getCount();
        for (int i = 0; i < count; i++) {
            view = getAdapter().getView(i, view, this);
            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            if (view.getMeasuredWidth() > maxWidth)
                maxWidth = view.getMeasuredWidth();
        }
        return maxWidth;
    }
}
