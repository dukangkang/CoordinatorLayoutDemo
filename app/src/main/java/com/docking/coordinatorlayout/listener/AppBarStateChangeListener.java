package com.docking.coordinatorlayout.listener;

import android.support.design.widget.AppBarLayout;

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
    private State mCurrentState = State.IDLE;

    public enum State {
        /**
         * 展开
         */
        EXPANDED,
        /**
         * 折叠
         */
        COLLAPSED,
        /**
         * 闲置
         */
        IDLE
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//        if (verticalOffset == 0) {
//            if (mCurrentState != State.EXPANDED) {
//                onStateChanged(appBarLayout, State.EXPANDED);
//            }
//            mCurrentState = State.EXPANDED;
//        }
        if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
//            if (mCurrentState != State.IDLE) {
//                onStateChanged(appBarLayout, State.IDLE);
//            }
//            mCurrentState = State.IDLE;
        }

        onVerticalOffset(appBarLayout, verticalOffset);
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);

    public abstract void onVerticalOffset(AppBarLayout appBarLayout, int verticalOffset);

}
