package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.base.BaseFragmentActivity;
import com.example.bean.User;
import com.example.broadcast.StartBroadCast;
import com.example.constant.Constant;
import com.example.db.DBUtils;
import com.example.fragment.Fragment_cart;
import com.example.fragment.Fragment_category;
import com.example.fragment.Fragment_home;
import com.example.fragment.Fragment_mine;
import com.example.fragment.Fragment_see;
import com.example.view.SlindingActivity;

import java.util.Date;


/**
 * Created by mym_0314 on 2016/5/11.
 */
public class CenterActivity extends BaseFragmentActivity implements View.OnClickListener {
    private StartBroadCast mBroadCast;
    private RadioButton mBtnHome, mBtnCart, mBtnCategory, mBtnSee, mBtnMine;
    private Button mBack;
    private ImageView mMore;
    private SlindingActivity mSlinding;
    private FrameLayout mFrameLayout;

    private Fragment_home mFragmentHome;
    private Fragment_cart mFragmentCart;
    private Fragment_category mFragmentCategory;
    private Fragment_see mFragmentSee;
    private Fragment_mine mFragmentMine;

    private View mTop;
    private View mBootom;
    private static final String HOME_FRAGMENT_CHOOSE = "fragment_choose";
    private RadioGroup mTab;
    private FragmentManager manager;

   private DBUtils dbUtils;
    @Override
    public void initView() {
        Log.e("Error", "initFragment");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_center);
        initBroadCast();
        dbUtils = new DBUtils(this);
        mTop = View.inflate(this, R.layout.home_top, null);
        mBootom = View.inflate(this, R.layout.home_bottom, null);
        mSlinding = (SlindingActivity) findViewById(R.id.content);
        mSlinding.setTop(mTop);
        mSlinding.setBottom(mBootom);
        mBack = (Button) mTop.findViewById(R.id.back);
        mBtnHome = (RadioButton) mTop.findViewById(R.id.tab_home);
        mBtnCart = (RadioButton) mTop.findViewById(R.id.tab_cart);
        mBtnCategory = (RadioButton) mTop.findViewById(R.id.tab_category);
        mBtnSee = (RadioButton) mTop.findViewById(R.id.tab_see);
        mBtnMine = (RadioButton) mTop.findViewById(R.id.tab_mine);
        mMore = (ImageView) mTop.findViewById(R.id.more);
        mTab = (RadioGroup) mTop.findViewById(R.id.tab);
        mFrameLayout = (FrameLayout) mTop.findViewById(R.id.home_content);
        mFragmentHome = new Fragment_home();
//        testDB();// 测试
//        testDBQuery();
        initFirstFragment();
    }
//    private void testDB(){
//        User user = new User();
//        user.setName("张三");
//        user.setAge(12);
//        user.setDate(new Date());
//        user.setSex("男");
//
//        dbUtils.saveData("user",user);
//    }
//    private void testDBQuery(){
//        DBUtils dbUtils = new DBUtils(this);
//        dbUtils.queryDate("user");
//    }

    private void initFirstFragment() {
        manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.home_content, mFragmentHome);
        ft.commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mBtnHome.setOnClickListener(this);
        mBtnCart.setOnClickListener(this);
        mBtnCategory.setOnClickListener(this);
        mBtnSee.setOnClickListener(this);
        mBtnMine.setOnClickListener(this);
    }

    private void initBroadCast() {
        mBroadCast = new StartBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.START);
        intentFilter.addAction(Constant.NOTIFICATION);
        registerReceiver(mBroadCast, intentFilter);
    }

    @Override
    protected void onStop() {
        if (mBroadCast != null)
            unregisterReceiver(mBroadCast);
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Toast("点击了");
                Intent intent = new Intent();
                intent.setAction(Constant.NOTIFICATION);
                sendBroadcast(intent);
                break;
            default:
                selectTab(v.getId());
                break;
        }
    }


    private void selectTab(int checkedId) {
        FragmentTransaction ft = manager.beginTransaction();
        clearFragment();
        switch (checkedId) {
            case R.id.tab_home:
                Log.e("Onclick", "mFragmentHome");
                mBtnHome.setChecked(true);
                if (mFragmentHome == null) {
                    mFragmentHome = new Fragment_home();
                    ft.add(R.id.home_content, mFragmentHome);
                } else {
                    ft.show(mFragmentHome);
                }
                break;
            case R.id.tab_cart:
                mBtnCart.setChecked(true);
                if (mFragmentCart == null) {
                    mFragmentCart = new Fragment_cart();
                    ft.add(R.id.home_content, mFragmentCart);
                } else {
                    ft.show(mFragmentCart);
                }
                break;
            case R.id.tab_category:
                mBtnCategory.setChecked(true);
                if (mFragmentCategory == null) {
                    mFragmentCategory = new Fragment_category();
                    ft.add(R.id.home_content, mFragmentCategory);
                } else {
                    ft.show(mFragmentCategory);
                }
                break;
            case R.id.tab_see:
                mBtnSee.setChecked(true);
                if (mFragmentSee == null) {
                    mFragmentSee = new Fragment_see();
                    ft.add(R.id.home_content, mFragmentSee);
                } else {
                    ft.show(mFragmentSee);
                }
                break;
            case R.id.tab_mine:
                mBtnMine.setChecked(true);
                if (mFragmentMine == null) {
                    mFragmentMine = new Fragment_mine();
                    ft.add(R.id.home_content, mFragmentMine);
                } else {
                    ft.show(mFragmentMine);
                }
                break;
        }
        ft.commit();

    }

    private void clearFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mFragmentHome != null)
            ft.hide(mFragmentHome);
        if (mFragmentCart != null)
            ft.hide(mFragmentCart);
        if (mFragmentCategory != null)
            ft.hide(mFragmentCategory);
        if (mFragmentSee != null)
            ft.hide(mFragmentSee);
        if (mFragmentMine != null)
            ft.hide(mFragmentMine);
        ft.commit();
    }
}
