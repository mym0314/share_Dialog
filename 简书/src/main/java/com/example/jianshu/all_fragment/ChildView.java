package com.example.jianshu.all_fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.jianshu.AllAdapter.MyFragmentPagerAdapter;
import com.example.jianshu.R;
import com.example.jianshu.all_fragment.faxian_fragment.view_left.Remen_fragment;
import com.example.jianshu.all_fragment.faxian_fragment.view_left.commen_fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by mym_0314 on 2016/3/23.
 */
public class ChildView implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private Context ctx;
    private List<Fragment> mList;
    private List<TextView> mTextList;

    private int layoutId;
    private View view;
    private TextView remen_tv;
    private TextView xinshangbang_tv;
    private TextView qieirement_tv;
    private TextView sanshiriremen_tv;
    private TextView shiji_tv;
    private ViewPager viewPager;
    private FragmentManager fm;

    public ChildView(Context ctx, int layoutId, FragmentManager fm) {
        this.ctx = ctx;
        this.layoutId = layoutId;
        this.fm = fm;
    }


    public View getView() {
        view = View.inflate(ctx, layoutId, null);
        initView();
        initData();
        initAdapter();
        initListener();
        return view;
    }

    private void initListener() {
        remen_tv.setOnClickListener(this);
        xinshangbang_tv.setOnClickListener(this);
        qieirement_tv.setOnClickListener(this);
        sanshiriremen_tv.setOnClickListener(this);
        shiji_tv.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);

    }

    private void initAdapter() {
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(fm);
        adapter.setList(mList);
        viewPager.setAdapter(adapter);
    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add(new Remen_fragment());
        mList.add(new commen_fragment());
        mList.add(new commen_fragment());
        mList.add(new commen_fragment());
        mList.add(new commen_fragment());
    }

    private void initView() {
        remen_tv = (TextView) view.findViewById(R.id.left_remen);
        xinshangbang_tv = (TextView) view.findViewById(R.id.left_xinshangbang);
        qieirement_tv = (TextView) view.findViewById(R.id.left_qiriremen);
        sanshiriremen_tv = (TextView) view.findViewById(R.id.left_sanshiriremen);
        shiji_tv = (TextView) view.findViewById(R.id.left_shiji);
        initTextList();
        viewPager = (ViewPager) view.findViewById(R.id.left_viewpager);
    }

    private void initTextList() {
        mTextList = new ArrayList<>();
        mTextList.add(remen_tv);
        mTextList.add(xinshangbang_tv);
        mTextList.add(qieirement_tv);
        mTextList.add(sanshiriremen_tv);
        mTextList.add(shiji_tv);
    }

    private void clearTextColor() {
        for (TextView tv : mTextList) {
            tv.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        clearTextColor();
        for (int i = 0; i < mTextList.size(); i++) {
            TextView tv = mTextList.get(i);
            if (tv == v) {
                tv.setTextColor(Color.RED);
                viewPager.setCurrentItem(i);
            }

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        clearTextColor();
        mTextList.get(position).setTextColor(Color.RED);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
