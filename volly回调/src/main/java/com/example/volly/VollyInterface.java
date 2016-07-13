package com.example.volly;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by mym_0314 on 2016/5/2.
 */
public abstract class VollyInterface {
    public Context mContext;
    private Response.Listener<String> mListener;
    private Response.ErrorListener mErrorListener;

    public VollyInterface(Context context) {
        this.mContext = context;

    }

    public abstract void onMySuccess(String result);

    public abstract void onMyError(VolleyError volleyError);


    public Response.Listener<String> getmListener() {
        mListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                onMySuccess(s);
            }
        };
        return mListener;
    }

    public Response.ErrorListener getmErrorListener() {
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onMyError(volleyError);
            }
        };
        return mErrorListener;
    }


}
