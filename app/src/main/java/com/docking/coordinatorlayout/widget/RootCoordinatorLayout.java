package com.docking.coordinatorlayout.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class RootCoordinatorLayout extends CoordinatorLayout {
    public static final String TAG = "RootCoordinatorLayout";
    public RootCoordinatorLayout(@NonNull Context context) {
        super(context);
    }

    public RootCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RootCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.w("dkk", TAG + " onInterceptTouchEvent");
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
        Log.w("dkk", TAG + " onTouchEvent");
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
        Log.w("dkk", TAG + " onNestedPreFling target = " + target);
//        if (target instanceof RecyclerView) {
//            return true;
//        }
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
        Log.w("dkk", TAG + " onNestedPreScroll");
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.w("dkk", TAG + " onNestedFling target = " + target + " \n velocityX = " + velocityX + " velocityY = " + velocityY + " consumed = " + consumed);


        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

}
