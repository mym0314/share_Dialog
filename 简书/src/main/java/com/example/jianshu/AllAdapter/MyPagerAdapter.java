package com.example.jianshu.AllAdapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by mym_0314 on 2016/3/22.
 */
public class MyPagerAdapter<T extends View> extends PagerAdapter {
    private List<T> mList;

    public MyPagerAdapter(List<T> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        T t = mList.get(position);
        container.addView(t);
        return t;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }
}
