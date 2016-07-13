package com.example.jianshu.all_fragment.show_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jianshu.AllAdapter.MyPagerAdapter;
import com.example.jianshu.R;
import com.example.jianshu.all_fragment.ChildView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mym_0314 on 2016/3/22.
 */
public class Faxian_fragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private View view;
    private TextView article;
    private TextView zhuanti;
    private ViewPager viewPager;
    private LinearLayout lines;
    private List<View> list_view;
    private MyPagerAdapter<View> adapter;
    private View line;
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_faxian, container, false);
        initView();
        initData();
        initAdapter();
        initListener();
        return view;
    }

    private void initListener() {
        article.setOnClickListener(this);
        zhuanti.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    private void initAdapter() {
        adapter = new MyPagerAdapter<>(list_view);
        viewPager.setAdapter(adapter);

    }

    private void initData() {
        list_view = new ArrayList<>();
        initChildData();
    }

    private void initChildData() {
        TextView tv = new TextView(getActivity());
        tv.setText("Right");
        tv.setGravity(Gravity.CENTER);
       // View view = View.inflate(getActivity(), R.layout.fragment_faxian_left, null);
        ChildView  cv = new ChildView(getActivity(),R.layout.fragment_faxian_left,getFragmentManager());
        View view = cv.getView();
        list_view.add(view);
        list_view.add(tv);
    }

    private void initView() {
        article = (TextView) view.findViewById(R.id.faxian_article);
        zhuanti = (TextView) view.findViewById(R.id.faxian_zhuanti);
        viewPager = (ViewPager) view.findViewById(R.id.faxian_pager);
        lines = (LinearLayout) view.findViewById(R.id.lines);
        v = view.findViewById(R.id.line);
        setLine();
    }

    private void setLine() {
        line = new View(getActivity());
        line.setBackgroundColor(Color.RED);
        article.measure(0, 0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(article.getMeasuredWidth()+45,LinearLayout.LayoutParams.MATCH_PARENT);
        line.setLayoutParams(params);
        lines.addView(line);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                float x = article.getLeft();
                line.setX(x);
            }
        });


    }

    @Override
    public void onClick(View v) {
        clearTextColor();
        switch (v.getId()) {
            case R.id.faxian_article:
                article.setTextColor(Color.RED);
                viewPager.setCurrentItem(0);
                break;
            case R.id.faxian_zhuanti:
                zhuanti.setTextColor(Color.RED);
                viewPager.setCurrentItem(1);
                break;
        }
    }

    public void clearTextColor() {
        article.setTextColor(Color.BLACK);
        zhuanti.setTextColor(Color.BLACK);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        float width = line.getWidth();
        line.setX(article.getLeft()+(position+positionOffset)*width);
    }

    @Override
    public void onPageSelected(int position) {
        clearTextColor();
        if (position == 0) {
            article.setTextColor(Color.RED);
        } else if (position == 1) {
            zhuanti.setTextColor(Color.RED);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
