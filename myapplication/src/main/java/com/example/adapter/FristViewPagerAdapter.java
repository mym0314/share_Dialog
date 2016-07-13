package com.example.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myapplication.FirstActivity;
import com.example.myapplication.MainActivity;

/**
 * Created by mym_0314 on 2016/5/14.
 */
public class FristViewPagerAdapter extends PagerAdapter {
    private Context ctx;
    private int[] mResource;

    public FristViewPagerAdapter(Context ctx, int[] mResource) {
        this.ctx = ctx;
        this.mResource = mResource;
    }

    @Override
    public int getCount() {
        return mResource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FrameLayout big = new FrameLayout(ctx);
        big.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        container.addView(big);

        ImageView imageView = new ImageView(ctx);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(mResource[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        big.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstActivity main = (FirstActivity) ctx;
                main.setViewPagerFull();
            }
        });
        return big;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
