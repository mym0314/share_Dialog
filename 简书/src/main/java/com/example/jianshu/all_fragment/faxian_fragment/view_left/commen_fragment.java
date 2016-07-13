package com.example.jianshu.all_fragment.faxian_fragment.view_left;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mym_0314 on 2016/3/22.
 */
public class commen_fragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setText("真是蠢");
        tv.setTextSize(30);
        tv.setGravity(Gravity.CENTER);

        return tv;
    }
}
