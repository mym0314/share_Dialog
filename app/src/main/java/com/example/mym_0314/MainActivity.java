package com.example.mym_0314;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.mym_0314.android_day16.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    private List<Bean> mList;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        mList = new ArrayList<>();
        adapter = new MyAdapter(this, mList);

        for (int i = 0; i < 20; i++) {
            mList.add(new Bean("真的好蠢-->" + i, "繁华易逝，容颜易老-->" + i, "2016-3-14-->" + i, "火热" + i));
        }
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(adapter);
    }
}
