package com.example.volly;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by mym_0314 on 2016/5/2.
 */
public class MyApplication extends Application {
    private static RequestQueue mQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getmQueue() {
        return mQueue;
    }
}
