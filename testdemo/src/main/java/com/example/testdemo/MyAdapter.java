package com.example.testdemo;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mym_0314 on 2016/5/18.
 */
public class MyAdapter extends BaseAdapter {
    private List<Bean> mList;
    private Context ctx;

    public MyAdapter(Context ctx, List<Bean> mList) {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Bean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * private String _id;
     * private String imgpath;
     * private String advertId;
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.item_list, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.BigImage);
            holder.tv = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance(ctx).imgLoad(holder.img,HttpConstants.ImgUrl+ mList.get(position).getImgpath()+"/");
        holder.tv.setText(mList.get(position).getAdvertId());
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView tv;
    }
}
