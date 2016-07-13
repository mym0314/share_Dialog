package com.example.jianshu.all_fragment.faxian_fragment.view_left;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.jianshu.AllAdapter.MyPagerAdapter;
import com.example.jianshu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mym_0314 on 2016/3/22.
 */
public class Remen_fragment extends Fragment implements ViewPager.OnPageChangeListener, SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private ViewPager viewpager;
    private ListView listView;
    private LinearLayout dots;
    private LinearLayout dot;
    private List<ImageView> mImgList;
    private List<String> mList;
    private MyPagerAdapter<ImageView> adapter;
    private float width;
    private View dotwhite;
    private float start;
    private float end;
    private ScrollView sv;
    private SwipeRefreshLayout swipe;


    private static Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_remen, container, false);
        initView();
        initData();
        initAdapter();
        initListener();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private void initListener() {
        viewpager.addOnPageChangeListener(this);
    }

    private void initAdapter() {
        adapter = new MyPagerAdapter<>(mImgList);
        viewpager.setAdapter(adapter);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mList);
        listView.setAdapter(listAdapter);
    }

    private void initData() {
        //初始化ViewPager的数据
        initViewPagerData();
        //初始化ListView的数据
        initListViewData();
        //添加ViewPager的小圆点
        initDots();
    }

    private void initDots() {
        for (int i = 0; i < mImgList.size(); i++) {
            dots.addView(getDot(R.drawable.dot));
        }
        dotwhite = getDot(R.drawable.dotwhite);
        dot.addView(dotwhite);
        //初始化白色移动小圆点的位置
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获得第一个、第二个、最后一个,小圆点位置
                View dot1 = dots.getChildAt(0);
                View dot2 = dots.getChildAt(1);
                View last_dot = dots.getChildAt(mImgList.size() - 1);
                start = dot1.getLeft();
                float left2 = dot2.getLeft();
                end = last_dot.getLeft();
                width = left2 - start;
                dotwhite.setX(start);
                //只调用一次ViewTreeObserver方法，移除这个方法；
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void initListViewData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            mList.add("我是蠢B--->>" + i);
    }

    private void initViewPagerData() {
        mImgList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mImgList.add(getImageView(R.mipmap.a));
        }
    }

    private ImageView getImageView(int image) {
        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(image);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return iv;
    }

    private void initView() {
        viewpager = (ViewPager) view.findViewById(R.id.remen_viewpager);
        viewpager.setFocusable(true);
        viewpager.setFocusableInTouchMode(true);
        viewpager.requestFocus();

        listView = (ListView) view.findViewById(R.id.listView);
        dots = (LinearLayout) view.findViewById(R.id.dots);
        dot = (LinearLayout) view.findViewById(R.id.dot);
        sv = (ScrollView) view.findViewById(R.id.sv);


        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipe.setColorSchemeColors(Color.RED);
        swipe.setOnRefreshListener(this);
    }

    private View getDot(int id) {
        View view = new View(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
        params.rightMargin = 20;
        view.setLayoutParams(params);
        view.setBackgroundResource(id);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        dotwhite.setX(Math.max(start, Math.min(start + (position + positionOffset) * width, end)));
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onRefresh() {
        //// TODO: 2016/3/23
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
            }
        }, 3000);
    }
}
