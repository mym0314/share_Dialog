package com.example.draggridview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    private List<HashMap<String, Object>> dataSourceList = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyGridView mDragGridView = (MyGridView) findViewById(R.id.MyGridView);
        for (int i = 0; i < 30; i++) {
            HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
            itemHashMap.put("item_image", R.mipmap.ic_launcher);
            itemHashMap.put("item_text", "拖拽 " + Integer.toString(i));
            dataSourceList.add(itemHashMap);
        }


        final SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, dataSourceList,
                R.layout.grid_item, new String[]{"item_image", "item_text"},
                new int[]{R.id.item_image, R.id.item_text});

        mDragGridView.setAdapter(mSimpleAdapter);

        mDragGridView.setOnChangListener(new MyGridView.onChangListener() {

            @Override
            public void onChang(int from, int to) {
                HashMap<String, Object> temp = dataSourceList.get(from);
                //直接交互item
                //              dataSourceList.set(from, dataSourceList.get(to));
                //              dataSourceList.set(to, temp);
                //              dataSourceList.set(to, temp);
                //这里的处理需要注意下
                if (from < to) {
                    for (int i = from; i < to; i++) {
                        Collections.swap(dataSourceList, i, i + 1);
                    }
                } else if (from > to) {
                    for (int i = from; i > to; i--) {
                        Collections.swap(dataSourceList, i, i - 1);
                    }
                }
                dataSourceList.set(to, temp);
                mSimpleAdapter.notifyDataSetChanged();
            }
        });

    }

}
