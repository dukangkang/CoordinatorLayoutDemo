package com.docking.coordinatorlayout.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class ChildRecyclerView extends RecyclerView {
    // true: 允许滑动， false:禁用滑动
    private boolean isScroll = false;

    public ChildRecyclerView(@NonNull Context context) {
        super(context);
    }

    public ChildRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.w("dkk", "ChildRecyclerView onInterceptTouchEvent isScroll = " + isScroll);
        // 置顶后，自己消费事件，反之不处理
//        if (isScroll) {
//            return super.onInterceptTouchEvent(ev);
//        } else {
//            return false;
//        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.w("dkk", "ChildRecyclerView onTouchEvent isScroll = " + isScroll);
        // 置顶后，自己消费事件，反之不处理
//        if (isScroll) {
//            return super.onTouchEvent(ev);
//        } else {
//            return false;
//        }
        return super.onTouchEvent(ev);
    }

    public void setScroll(boolean isTop) {
        // isTop true:置顶，false:未置顶
        isScroll = isTop;
    }
}
