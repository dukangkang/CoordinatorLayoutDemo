package com.docking.coordinatorlayout.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class ChildViewPager extends ViewPager {
    // true: 允许滑动， false:禁用滑动
    private boolean isScroll = false;

    public ChildViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.v("dkk", "ChildViewPager onInterceptTouchEvent isScroll = " + isScroll);
        // 置顶后，自己消费事件，反之不处理
        if (isScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.v("dkk", "ChildViewPager onTouchEvent isScroll = " + isScroll);
        // 置顶后，自己消费事件，反之不处理
        if (isScroll) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }


    public void setScroll(boolean isTop) {
        // isTop true:置顶，false:未置顶
        isScroll = isTop;
    }
}