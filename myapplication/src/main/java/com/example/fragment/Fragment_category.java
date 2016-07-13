package com.example.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * Created by mym_0314 on 2016/5/22.
 */
public class Fragment_category extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String str = "Category";
        if (getArguments() != null) {
            Bundle arg = getArguments();
            str = arg.getString("arg");
        }
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        TextView tv = new TextView(getActivity());
        RelativeLayout mRelative = (RelativeLayout) view.findViewById(R.id.fragment_category);
        mRelative.setBackgroundColor(Color.GRAY);
        mRelative.addView(tv);
        tv.setText(str);
        tv.setTextColor(Color.BLACK);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tv.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        tv.setLayoutParams(lp);
        tv.setTextSize(22f);
        return view;
    }
}
