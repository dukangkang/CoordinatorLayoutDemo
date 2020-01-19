package com.docking.coordinatorlayout.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.docking.coordinatorlayout.behavior.AppBarLayoutBehavior;

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

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.w("dkk", "----->>>>onNestedPreFling 1");
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
        Log.w("dkk", "----->>>>onNestedPreScroll");
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.w("dkk", "----->>>>onNestedFling 2");
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    public void setScroll(boolean isTop) {
        // isTop true:置顶，false:未置顶
        isScroll = isTop;
    }
}
