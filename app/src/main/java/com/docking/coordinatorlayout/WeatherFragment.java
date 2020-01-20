package com.docking.coordinatorlayout;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.docking.coordinatorlayout.behavior.AppBarLayoutBehavior;
import com.docking.coordinatorlayout.coordinatorlayout.demo.R;
import com.docking.coordinatorlayout.event.ScrollEvent;
import com.docking.coordinatorlayout.listener.AppBarStateChangeListener;
import com.docking.coordinatorlayout.widget.ChildViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class WeatherFragment extends Fragment implements View.OnClickListener {

    private List<String> mList = new ArrayList<>();
    private ChildViewPager mViewPager = null;
    private WeatherAdapter mAdapter = null;
    private int position = 0;
    private TextView mBackTv = null;
    private TextView mNextTv = null;
    private Button mBgBtn = null;
    private ImageView mBgIv = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        position = bundle.getInt("KEY_POSITION");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.weather_fragment, null);
        return view;
    }

    private void init() {
        ImageView imageView = getView().findViewById(R.id.image1);
        switch (position) {
            case 0:
                imageView.setImageResource(R.mipmap.aipmiddle);
                break;
            case 1:
                imageView.setImageResource(R.mipmap.aipliang);
                break;
            case 2:
                imageView.setImageResource(R.mipmap.aipqd);
                break;
            default:
                imageView.setImageResource(R.mipmap.aipyou);
                break;
        }

        mList.add("1");
        mList.add("2");
        mList.add("3");

        mAdapter = new WeatherAdapter(this.getChildFragmentManager());
        mAdapter.replace(mList);
        mViewPager = getView().findViewById(R.id.weather_viewpager);
        mViewPager.setAdapter(mAdapter);

        mAppBarLayout = getView().findViewById(R.id.appbar);

        mBackTv = getView().findViewById(R.id.weather_back);
        mNextTv = getView().findViewById(R.id.weather_next);
        mBgBtn = getView().findViewById(R.id.weather_bg_btn);
        mBgIv = getView().findViewById(R.id.weather_bg_iv);
    }

    private boolean canDrag = true;
    AppBarLayout mAppBarLayout = null;
    private void initListener() {
        mBackTv.setOnClickListener(this);
        mNextTv.setOnClickListener(this);
        mBgBtn.setOnClickListener(this);
        mBgIv.setOnClickListener(this);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (State.COLLAPSED == state) {
                    Log.w("dkk", "----->>> 折叠");
                    stickTop(true);
                } if (State.EXPANDED == state) {
                    Log.w("dkk", "----->>> 展开");
                    stickTop(false);
                } else {
                    Log.w("dkk", "----->>> 闲置");
                }
            }

            @Override
            public void onVerticalOffset(AppBarLayout appBarLayout, int verticalOffset) {
            }
        });

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayoutBehavior behavior = (AppBarLayoutBehavior) params.getBehavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                return true;
            }
        });

    }

    private void stickTop(final boolean isTop) {
        // 操作信息流置顶
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayoutBehavior behavior = (AppBarLayoutBehavior) params.getBehavior();
        behavior.setEnableNestedScroll(!isTop);
        Log.e("dkk", "----->>> " + "isTop = " + isTop);
        Log.e("dkk", "----->>> " + (isTop?"置顶":"未置顶"));
//        Activity activity = getActivity();
//        if (activity instanceof MainActivity) {
//            ((MainActivity)activity).setScroll(isTop);
//        }
        mViewPager.setScroll(isTop);
        mAdapter.notifyTop(isTop);
    }

    /**
     * 设置是否展开
     */
    private void gotoTopAndBottom(final boolean isExpanded) {
        EventBus.getDefault().post(new ScrollEvent(isExpanded));
        mAppBarLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAppBarLayout.setExpanded(isExpanded);
            }
        }, 50);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mBackTv.getId()) {
            gotoTopAndBottom(true);
        } else if (id == mNextTv.getId()) {
            gotoTopAndBottom(false);
        } else if (id == mBgBtn.getId()) {
            Toast.makeText(getActivity(), "点击按钮", Toast.LENGTH_SHORT).show();
        } else if (id == mBgIv.getId()) {
            Toast.makeText(getActivity(), "点击图片", Toast.LENGTH_SHORT).show();
        }
    }

//     mAppBarParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
//        mAppBarChildAt.setLayoutParams(mAppBarParams);
//————————————————
//    版权声明：本文为CSDN博主「Silas_」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/qq_31852701/article/details/80859644

}
