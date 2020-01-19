package com.docking.coordinatorlayout.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.docking.coordinatorlayout.coordinatorlayout.demo.R;

public class CommTitle extends LinearLayout {

    public CommTitle(Context context) {
        super(context);
        init(context);
    }

    public CommTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.comm_title_view, this);
    }
}
