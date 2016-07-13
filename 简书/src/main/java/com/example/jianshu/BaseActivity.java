package com.example.jianshu;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by mym_0314 on 2016/3/22.
 */
public class BaseActivity  extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setWindowAnimations(R.style.BaseActivity);
    }
}
