//package com.docking.coordinatorlayout.widget;
//
//import android.content.Context;
//import android.support.v7.widget.LinearLayoutManager;
//import android.util.AttributeSet;
//
//public class ChildLinearLayoutManager extends LinearLayoutManager {
//    private boolean canScroll = true;
//
//    public ChildLinearLayoutManager(Context context) {
//        super(context);
//    }
//
//    public ChildLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
//        super(context, orientation, reverseLayout);
//    }
//
//    public ChildLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
//
//    public void setCanScroll(boolean canScroll) {
//        this.canScroll = canScroll;
//    }
//
//    @Override
//    public boolean canScrollVertically() {
//        return canScroll && super.canScrollVertically();
//    }
//}
