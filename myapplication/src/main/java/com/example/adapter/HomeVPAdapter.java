package com.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mym_0314 on 2016/5/23.
 */
public class HomeVPAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList = new ArrayList<>();

    public void setData(List<Fragment> mList) {
        this.mList = mList;
    }

    public HomeVPAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

}
