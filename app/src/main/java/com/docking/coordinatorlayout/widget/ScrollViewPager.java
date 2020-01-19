package com.docking.coordinatorlayout.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ScrollViewPager extends ViewPager {
    // true: 允许滑动， false:禁用滑动
    private boolean isScroll = false;

    public ScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("dkk", "ScrollViewPager onInterceptTouchEvent isScroll = " + isScroll);
        // 置顶后，交个子View处理
        if (isScroll) {
            return false;
        }

        try {
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("dkk", "ScrollViewPager onTouchEvent isScroll = " + isScroll);
        // 置顶后，交个子View处理
        if (isScroll) {
            return false;
        }
        try {
            return super.onTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        // View没有置顶的情况下，禁用子View左右滑动事件
        if (!isScroll) {
            return false;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }

    public void setScroll(boolean isTop) {
        // isTop true:置顶，false:未置顶
        isScroll = isTop;
    }
}