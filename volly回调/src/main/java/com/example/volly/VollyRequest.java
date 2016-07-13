package com.example.volly;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by mym_0314 on 2016/5/2.
 */
public class VollyRequest {

    private static StringRequest mRequest;

    public static void getRequest( String url, String tag, VollyInterface vif) {
        MyApplication.getmQueue().cancelAll(tag);
        mRequest = new StringRequest(Request.Method.GET, url, vif.getmListener(), vif.getmErrorListener());
        mRequest.setTag(tag);
        MyApplication.getmQueue().add(mRequest);
        MyApplication.getmQueue().start();
    }

    public static void postRequese( String url, String tag, VollyInterface vif) {
        MyApplication.getmQueue().cancelAll(tag);
        mRequest = new StringRequest(Request.Method.POST, url, vif.getmListener(), vif.getmErrorListener());
        mRequest.setTag(tag);
        MyApplication.getmQueue().add(mRequest);
        MyApplication.getmQueue().start();
    }


}
