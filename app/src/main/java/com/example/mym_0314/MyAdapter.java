package com.example.mym_0314;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mym_0314.android_day16.R;

import java.util.List;

/**
 * Created by mym_0314 on 2016/3/24.
 */
public class MyAdapter extends CommaAdapter<Bean> {


    public MyAdapter(Context context, List<Bean> mList) {
        super(context, mList);
    }


    @Override
    public void convert(ViewHolder holder, Bean bean) {
        holder.setText(R.id.title, bean.getTitle()).setText(R.id.content, bean.getContent())
                .setText(R.id.time, bean.getTime()).setText(R.id.hot, bean.getHot());

    }


}
