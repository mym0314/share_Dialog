package com.example.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by mym_0314 on 2016/5/11.
 */
public abstract class BaseFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = initView(inflater);
        initData();
        initListener();
        return view;
    }

    protected abstract View initView(LayoutInflater inflater);

    protected abstract void initData();

    protected abstract void initListener();

    protected void Toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected View f(int recourse) {
        return view.findViewById(recourse);
    }


}
