package com.docking.coordinatorlayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class ChildSmartRefreshLayout extends SmartRefreshLayout {
    // true: 允许滑动， false:禁用滑动
    private boolean isScroll = false;

    public ChildSmartRefreshLayout(Context context) {
        super(context);
    }

    public ChildSmartRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildSmartRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float startX;
    private float startY;
    private float endX;
    private float endY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.w("dkk", "ChildSmartRefreshLayout onInterceptTouchEvent isScroll = " + isScroll);
        return super.onInterceptTouchEvent(ev);
//        // 置顶后，自己消费事件，反之不处理
//        if (isScroll) {
//            return super.onInterceptTouchEvent(ev);
//        } else {
////            int action = ev.getAction();
////            switch (action) {
////                case MotionEvent.ACTION_DOWN:
////                    startX = ev.getX();
////                    startY = ev.getY();
////                    break;
////                case MotionEvent.ACTION_MOVE:
////                    endX = ev.getX();
////                    endY = ev.getY();
////                    Log.w("dkk", "### startY x endY = " + startY + "x" + endY);
////                    if (startY - endY > 5) {
////                        Log.w("dkk", "### 向上滑动");
////                    } else if (endY - startY > 5) {
////                        Log.w("dkk", "### 向下滑动");
////                    }
////                    startX = endX;
////                    startY = endY;
////                    break;
////                case MotionEvent.ACTION_UP:
//////                    endX = ev.getX();
//////                    endY = ev.getY();
//////
//////                    if (startY - endY > 0) {
//////                        Log.w("dkk", "向上滑动");
//////                    } else if (endY - startX > 0) {
//////                        Log.w("dkk", "向下滑动");
//////                    }
////                    break;
////            }
//            return false;
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.w("dkk", "ChildSmartRefreshLayout onInterceptTouchEvent isScroll = " + isScroll);
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
