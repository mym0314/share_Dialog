package com.example.testdemo;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

/**
 * Created by mym_0314 on 2016/5/18.
 */
public class JsonThread extends  Thread {
    private String url ;
    private Handler handler;
    public JsonThread(String url,Handler handler){
        this.url  =url;
        this.handler = handler;
    }
    @Override
    public void run() {
        try {
               String json= NetUtils.getJsonFromHttp(url);
           Message  msg= handler.obtainMessage();
            msg.what =1;
            msg.obj= json;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
