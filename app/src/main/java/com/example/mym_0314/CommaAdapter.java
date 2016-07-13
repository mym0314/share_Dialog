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
public abstract class CommaAdapter<T> extends BaseAdapter{
    protected Context context;
    protected List<T> mList;
    protected LayoutInflater inflater;
    public  CommaAdapter(Context context,List<T> mList){
        this.context = context;
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(context, convertView, parent, R.layout.item, position);
        convert(holder,getItem(position));
        return holder.getmConvertView();
    }
    public abstract void convert(ViewHolder holder,T t);
}
