package com.example.jianshu.AllAdapter;

import android.media.MediaCodecList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mym_0314 on 2016/3/23.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList  = new ArrayList<>();

    public void setList( List<Fragment> list){

        this.mList = list;
    }
    public MyFragmentPagerAdapter(FragmentManager fm) {

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
