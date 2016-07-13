package com.example.base;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.MyApplication;

/**
 * Created by mym_0314 on 2016/5/11.
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected View f(int Recource) {
        return findViewById(Recource);
    }

    protected MyApplication getApp() {
        return (MyApplication) getApplication();
    }

    protected void Toast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
