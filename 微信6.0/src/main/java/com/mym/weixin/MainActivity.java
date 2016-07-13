package com.mym.weixin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import android.view.View;
import android.view.Window;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private List<Fragment> mList;
    private String[] mContent = new String[]{
            "TabFragment_1", "TabFragment_2", "TabFragment_3", "TabFragment_4"};

    private FragmentPagerAdapter adapter;

    private ChangerColorIconWithText one, two, three, four;
    private List<ChangerColorIconWithText> mTabs;
    private float mAlpha = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayShowHomeEnabled(false);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(this);
    }

    private void initData() {
        mList = new ArrayList<>();
        mTabs = new ArrayList<>();
        for (int i = 0; i < mContent.length; i++) {
            TabFragment tab = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TabFragment.TEXT_TAG, mContent[i]);
            tab.setArguments(bundle);
            mList.add(tab);
        }
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        };
        mViewPager.setAdapter(adapter);

        mTabs.add(one);
        mTabs.add(two);
        mTabs.add(three);
        mTabs.add(four);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.ViewPager);
        one = (ChangerColorIconWithText) findViewById(R.id.Icon_indicator_one);
        two = (ChangerColorIconWithText) findViewById(R.id.Icon_indicator_two);
        three = (ChangerColorIconWithText) findViewById(R.id.Icon_indicator_three);
        four = (ChangerColorIconWithText) findViewById(R.id.Icon_indicator_four);

    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        tabClick(v);
    }

    private void tabClick(View v) {
        resetColor();
        switch (v.getId()) {
            case R.id.Icon_indicator_one:
                mTabs.get(0).setIconAlpha(mAlpha);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.Icon_indicator_two:
                mTabs.get(1).setIconAlpha(mAlpha);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.Icon_indicator_three:
                mTabs.get(2).setIconAlpha(mAlpha);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.Icon_indicator_four:
                mTabs.get(3).setIconAlpha(mAlpha);
                mViewPager.setCurrentItem(3);
                break;
        }
    }

    private void resetColor() {
        for (int i = 0; i < mTabs.size(); i++) {
            mTabs.get(i).setIconAlpha(0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
            ChangerColorIconWithText left = mTabs.get(position);
            ChangerColorIconWithText right = mTabs.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
