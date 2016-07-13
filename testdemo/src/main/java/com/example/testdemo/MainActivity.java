package com.example.testdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView mListView;
    private List mList = new ArrayList<>();
    private MyAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String json = (String) msg.obj;

                try {

                    mList = JsonUtils.getJsonFromStr(json);
                    Log.e("JSON", mList.toString());
                    adapter = new MyAdapter(MainActivity.this, mList);
                    mListView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.ListView);
        JsonThread thread = new JsonThread(HttpConstants.HttpUrl + HttpConstants.ADVERSTURL, handler);
        thread.start();
    }
}
