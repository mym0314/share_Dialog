package com.example.utils.animation;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.myapplication.R;

/**
 * Created by mym_0314 on 2016/5/31.
 */
public class AnimUtils {
    private Animation animation;
    private Context ctx;
    public AnimUtils(Context ctx){
        this.ctx = ctx;
    }
    public Animation getScaleAnimation() {
        animation = AnimationUtils.loadAnimation(ctx, R.anim.scale_anim);
        return animation;
    }

    public Animation getAlphaAnimation() {
        animation = AnimationUtils.loadAnimation(ctx, R.anim.alpha_anim);
        return animation;
    }

    public Animation getRetoteAnimation() {
        animation = AnimationUtils.loadAnimation(ctx, R.anim.rotate_anim);
        return animation;
    }
}
