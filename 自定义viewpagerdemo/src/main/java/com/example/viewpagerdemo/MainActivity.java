package com.example.viewpagerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class MainActivity extends Activity {
    private LinearLayout mLinear;
    private List<ViewPagerInfo> mList;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        initHttp();

    }

    private void initHttp() {
        mQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST,
                Model.HttpUrl + Model.ADVERSTURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.e("request", s);
                        mList = JsonUtils.getJsonList(Model.ADVERSTURL, s);
                        mLinear.addView(new MyViewPager(MainActivity.this, mList).getView());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        mQueue.add(request);
        mQueue.start();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mLinear = (LinearLayout) findViewById(R.id.Linear_viewPager);

    }
}
