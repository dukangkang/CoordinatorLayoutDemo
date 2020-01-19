package com.docking.coordinatorlayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.docking.coordinatorlayout.event.TopEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class WeatherAdapter extends FragmentStatePagerAdapter {
    private List<String> mList;
    public WeatherAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        CustomFragment fragment = new CustomFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void replace(List<String> list) {
        if (mList != null) {
            mList.clear();
        }
        this.mList = list;
        notifyDataSetChanged();
    }

    public void notifyTop(boolean isTop) {
        EventBus.getDefault().post(new TopEvent(isTop));
    }
}
