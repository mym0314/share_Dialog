package com.example.review;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    private final static String LOG = "MainActivity";

   private  Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e(LOG, msg.obj.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNet();
        initView();
        initData();
    }

    private void initNet() {
        new Thread() {
            @Override
            public void run() {
                String json = NetUtils.getJsonFromNet(Constants.HTTP_URL + Constants.HOMEPAGER);
                Message msg = handler.obtainMessage();
                msg.obj = json;
                handler.sendMessage(msg);
            }
        }.start();

    }

    private void initData() {

    }

    private void initView() {
        setContentView(R.layout.activity_main);
    }

}
