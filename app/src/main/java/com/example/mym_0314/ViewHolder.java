package com.example.mym_0314;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mym_0314 on 2016/3/24.
 */
public class ViewHolder {
    private SparseArray<View> sparseArray;

    private Context context;

    private  int mPosition;

    private View mConvertView;


    private ViewHolder(Context context, ViewGroup parent, int position, int layoutId) {
        this.context = context;
        sparseArray = new SparseArray<>();
        this.mPosition = position;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }


    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, position, layoutId);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    public <T extends View> T getView(int viewId) {
        View view = sparseArray.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            sparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public View getmConvertView() {
        return mConvertView;
    }

    public ViewHolder setText(int viewId,String content){
        TextView view = getView(viewId);
        view.setText(content);
        return this;
    }
}
