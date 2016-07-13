package com.example.viewpagerdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mym_0314 on 2016/5/3.
 */
public class MyViewPager implements ViewPager.OnPageChangeListener {
    private Context mContext;
    private RelativeLayout mRelative;
    private ViewPager mViewPager;
    private LinearLayout mLinear;

    private List<ViewPagerInfo> mList;
    private List<ImageView> mVPList;
    private List<ImageView> mDotList;

    public MyViewPager(Context context, List<ViewPagerInfo> list) {
        this.mContext = context;
        this.mList = list;
        initView();
        initData();
        initAdapter();
        initListener();
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(this);
    }

    private void initAdapter() {
        MyAdapter adapter = new MyAdapter();
        mViewPager.setAdapter(adapter);
    }

    private void initData() {
        mVPList = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            ImageView iv = new ImageView(mContext);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(lp);
            mVPList.add(iv);
        }
        mDotList = new ArrayList<>();
        for (int j = 0; j < mList.size(); j++) {
            ImageView iv = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 10;
            iv.setLayoutParams(lp);
            iv.setImageResource(R.mipmap.home_banner_dot_down_black);
            if (j == 0)
                iv.setImageResource(R.mipmap.home_banner_dot_down);
            mLinear.addView(iv);
            mDotList.add(iv);
        }
    }

    private void initView() {
        mRelative = new RelativeLayout(mContext);
        mViewPager = new ViewPager(mContext);
        RelativeLayout.LayoutParams viewPagerLP = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        mViewPager.setLayoutParams(viewPagerLP);
        mRelative.addView(mViewPager);
        mLinear = new LinearLayout(mContext);
        RelativeLayout.LayoutParams linearLP = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        linearLP.bottomMargin = 20;
        linearLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        linearLP.addRule(RelativeLayout.CENTER_HORIZONTAL);

        mLinear.setLayoutParams(linearLP);
        mLinear.setOrientation(LinearLayout.HORIZONTAL);

        mRelative.addView(mLinear);
    }

    public View getView() {
        return mRelative;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        clearDot();
        mDotList.get(position).setImageResource(R.mipmap.home_banner_dot_down);
    }

    private void clearDot() {
        for (int i = 0; i < mDotList.size(); i++) {
            mDotList.get(i).setImageResource(R.mipmap.home_banner_dot_down_black);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MyAdapter extends PagerAdapter {

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
            ImageView img = mVPList.get(position);
            MyApplication app = (MyApplication) mContext.getApplicationContext();
            ImageLoader.getInstance().displayImage(Model.HTTP_IMG + mList.get(position).imgpath, img, app.loader.buildOp(R.mipmap.ic_launcher));
            container.addView(img);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mVPList.get(position));
        }
    }
}
