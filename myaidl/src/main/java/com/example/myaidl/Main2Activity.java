package com.example.myaidl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends Activity {
    private TextView tv1;
    private TextView tv2;
    private String name;
    private int age;
    private String account;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            age = bundle.getInt("age");
            account = bundle.getString("account");
            pwd = bundle.getString("pwd");
        }

        tv1.setText( "account = " + account + "pwd = " + pwd);
        tv2.setText( "name = " + name + "age = " + age );
    }

    private void initView() {
        setContentView(R.layout.activity_main2);
        tv1 = (TextView) findViewById(R.id.Tv1);
        tv2 = (TextView) findViewById(R.id.Tv2);
    }
}
