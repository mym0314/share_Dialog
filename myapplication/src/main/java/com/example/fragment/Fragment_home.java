package com.example.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adapter.HomeVPAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mym_0314 on 2016/5/21.
 */
public class Fragment_home extends Fragment {
    private View view;
    private float mScreenWidth;
    private float mScreenHeight;
    private List<TextView> tvList;
    private List<Fragment> fraList;
    private HorizontalScrollView mScrollView;
    private ViewPager mViewPager;
    private LinearLayout mLinear;
    private LinearLayout home_linear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        home_linear = (LinearLayout) view.findViewById(R.id.fragment_home);
        getScreenSize();
        initView();
        initData();

        return view;
    }

    private void initData() {
        fraList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Fragment_see see = new Fragment_see();
            Bundle bundle = new Bundle();
            bundle.putString("arg", "Home_child" + i);
            see.setArguments(bundle);
            fraList.add(see);
        }

        for (int i = 0; i < 7; i++) {
            TextView tv = new TextView(getActivity());
            mLinear.addView(tv);
            tv.setLayoutParams(new LinearLayout.LayoutParams(
                    (int) mScreenWidth / 3, LinearLayout.LayoutParams.MATCH_PARENT));
            tv.setTextSize(14);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.GREEN);
            tv.setText("Home_tab" + i);
        }
        FragmentManager manager = getChildFragmentManager();
        HomeVPAdapter adapter = new HomeVPAdapter(manager);
        adapter.setData(fraList);

        mViewPager.setAdapter(adapter);

    }


    private void getScreenSize() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
    }

    private void initView() {
        mScrollView = new HorizontalScrollView(getActivity());
        mScrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mScrollView.setBackgroundColor(Color.BLACK);
        mScrollView.setHorizontalScrollBarEnabled(false);
        mLinear = new LinearLayout(getActivity());
        mLinear.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mLinear.setOrientation(LinearLayout.HORIZONTAL);
        mScrollView.addView(mLinear);
        mViewPager = new ViewPager(getActivity());
        mViewPager.setId(R.id.fraHomeVp);
        mViewPager.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        home_linear.addView(mScrollView);
        home_linear.addView(mViewPager);
    }
}
