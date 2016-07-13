package com.example.jianshu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jianshu.all_fragment.show_fragment.Center_fragment;
import com.example.jianshu.all_fragment.show_fragment.Faxian_fragment;
import com.example.jianshu.all_fragment.show_fragment.Guanzhu_fragment;
import com.example.jianshu.all_fragment.show_fragment.Wode_fragment;
import com.example.jianshu.all_fragment.show_fragment.Xiaoxi_fragment;

import java.util.ArrayList;
import java.util.List;

public class   ShowActivity extends BaseActivity implements View.OnClickListener {

    private TextView faxian;
    private TextView guanzhu;
    private TextView xiaoxi;
    private TextView wode;
    private List<TextView> textList;
    private ImageView center;
    private FragmentManager fm;
    private Faxian_fragment faxian_fragment;
    private Guanzhu_fragment guanzhu_fragment;
    private Xiaoxi_fragment xiaoxi_fragment;
    private Wode_fragment wode_fragment;
    private Center_fragment center_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();

    }

    private void initData() {
        initFirstFragment();
        initTextList();
    }

    private void initTextList() {
        textList = new ArrayList<>();
        textList.add(faxian);
        textList.add(guanzhu);
        textList.add(xiaoxi);
        textList.add(wode);

    }

    private void initFirstFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        faxian_fragment = new Faxian_fragment();
        ft.add(R.id.show_frameLayout, faxian_fragment);
        ft.commit();
    }

    private void initListener() {
        faxian.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
        xiaoxi.setOnClickListener(this);
        wode.setOnClickListener(this);
        center.setOnClickListener(this);
    }

    private void initView() {
        setContentView(R.layout.activity_show);
        faxian = (TextView) findViewById(R.id.tv_faxian);
        guanzhu = (TextView) findViewById(R.id.tv_guanzhu);
        xiaoxi = (TextView) findViewById(R.id.tv_xiaoxi);
        wode = (TextView) findViewById(R.id.tv_wode);
        center = (ImageView) findViewById(R.id.iv_center);
    }


    @Override
    public void onClick(View v) {
        fm = getSupportFragmentManager();
        clearTextColor();
        FragmentTransaction ft = fm.beginTransaction();
        clearAllFragment(ft);
        switch (v.getId()) {
            case R.id.tv_faxian:
                if (faxian_fragment == null) {
                    faxian_fragment = new Faxian_fragment();
                    ft.add(R.id.show_frameLayout, faxian_fragment);
                } else {
                    ft.show(faxian_fragment);
                }
                faxian.setTextColor(Color.RED);
                break;
            case R.id.tv_guanzhu:
                if (guanzhu_fragment == null) {
                    guanzhu_fragment = new Guanzhu_fragment();
                    ft.add(R.id.show_frameLayout, guanzhu_fragment);
                } else {
                    ft.show(guanzhu_fragment);
                }
                guanzhu.setTextColor(Color.RED);
                break;
            case R.id.tv_xiaoxi:
                if (xiaoxi_fragment == null) {
                    xiaoxi_fragment = new Xiaoxi_fragment();
                    ft.add(R.id.show_frameLayout, xiaoxi_fragment);
                } else {
                    ft.show(xiaoxi_fragment);
                }
                xiaoxi.setTextColor(Color.RED);
                break;
            case R.id.tv_wode:
                if (wode_fragment == null) {
                    wode_fragment = new Wode_fragment();
                    ft.add(R.id.show_frameLayout, wode_fragment);
                } else {
                    ft.show(wode_fragment);
                }
                wode.setTextColor(Color.RED);
                break;
            case R.id.iv_center:
                if (center_fragment == null) {
                    center_fragment = new Center_fragment();
                    ft.add(R.id.show_frameLayout, center_fragment);
                } else {
                    ft.show(center_fragment);
                }
                break;
        }
        ft.commit();
    }

    private void clearTextColor() {
        for (TextView v : textList) {
            v.setTextColor(Color.GRAY);
        }
    }

    private void clearAllFragment(FragmentTransaction  ft) {
        if (xiaoxi_fragment != null)
            ft.hide(xiaoxi_fragment);
        if (guanzhu_fragment != null)
            ft.hide(guanzhu_fragment);
        if (faxian_fragment != null)
            ft.hide(faxian_fragment);
        if (wode_fragment != null)
            ft.hide(wode_fragment);
        if (center_fragment != null)
            ft.hide(center_fragment);

    }


}
