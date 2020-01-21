package com.docking.coordinatorlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.docking.coordinatorlayout.anim.AnimActivity;
import com.docking.coordinatorlayout.coordinatorlayout.demo.R;
import com.docking.coordinatorlayout.event.TopEvent;
import com.docking.coordinatorlayout.widget.ParentViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private List<String> mList = new ArrayList<>();
    private ParentViewPager mViewPager = null;

    MainAdapter mAdapter = null;
    private void init() {
        mList.add("北京");
        mList.add("上海");
        mList.add("天津");

        mAdapter = new MainAdapter(this.getSupportFragmentManager());
        mAdapter.replace(mList);
        mViewPager = this.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mAdapter);

        this.findViewById(R.id.main_comm_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击一次", Toast.LENGTH_SHORT).show();
                startAnimActivity();
            }
        });
    }

    private void startAnimActivity() {
        Intent intent = new Intent(this, AnimActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TopEvent event) {
        Log.w("dkk", "TPGActivity TopEvent event.isTop = " + event.isTop);
        mViewPager.setScroll(event.isTop);
    }

}
