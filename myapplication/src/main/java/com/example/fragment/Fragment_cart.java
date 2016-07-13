package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.utils.animation.AnimUtils;

import java.lang.ref.SoftReference;

/**
 * Created by mym_0314 on 2016/5/22.
 */
public class Fragment_cart extends Fragment implements View.OnClickListener {

    private View view;
    private AnimUtils animUtils;

    private ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        animUtils = new AnimUtils(getActivity());
        initView();
        initData();
        initListener();
        return view;
    }

    private void initData() {
    }

    private void initListener() {
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
        iv7.setOnClickListener(this);
        iv8.setOnClickListener(this);
    }

    private void initView() {
        iv1 = (ImageView) view.findViewById(R.id.iv1);
        iv2 = (ImageView) view.findViewById(R.id.iv2);
        iv3 = (ImageView) view.findViewById(R.id.iv3);
        iv4 = (ImageView) view.findViewById(R.id.iv4);
        iv5 = (ImageView) view.findViewById(R.id.iv5);
        iv6 = (ImageView) view.findViewById(R.id.iv6);
        iv7 = (ImageView) view.findViewById(R.id.iv7);
        iv8 = (ImageView) view.findViewById(R.id.iv8);
    }

    @Override
    public void onClick(View v) {
        AnimationSet animation;
        SoftReference<Animation> softReference = new SoftReference<Animation>(new AnimationSet(true));
        animation = (AnimationSet) softReference.get();
        switch (v.getId()) {
            case R.id.iv1:
                AlphaAnimation alpha = (AlphaAnimation) animUtils.getAlphaAnimation();
                animation.addAnimation(alpha);
                iv1.startAnimation(animation);
                break;
            case R.id.iv2:
                ScaleAnimation scale = (ScaleAnimation) animUtils.getScaleAnimation();
                animation.addAnimation(scale);
                iv2.startAnimation(animation);
                break;
            case R.id.iv3:
                RotateAnimation rotate = (RotateAnimation) animUtils.getRetoteAnimation();
                animation.addAnimation(rotate);
                iv3.startAnimation(animation);
                break;
            case R.id.iv4:
                AlphaAnimation alpha4 = (AlphaAnimation) animUtils.getAlphaAnimation();
                ScaleAnimation scale4 = (ScaleAnimation) animUtils.getScaleAnimation();
                animation.addAnimation(alpha4);
                animation.addAnimation(scale4);
                iv4.startAnimation(animation);
                break;
            case R.id.iv5:
                AlphaAnimation alpha5 = (AlphaAnimation) animUtils.getAlphaAnimation();
                ScaleAnimation scale5 = (ScaleAnimation) animUtils.getScaleAnimation();
                RotateAnimation rotate5 = (RotateAnimation) animUtils.getRetoteAnimation();
                animation.addAnimation(rotate5);
                animation.addAnimation(alpha5);
                animation.addAnimation(scale5);
                iv5.startAnimation(animation);
                break;
            case R.id.iv6:
                break;
            case R.id.iv7:
                break;
            case R.id.iv8:
                break;
        }
    }
}
