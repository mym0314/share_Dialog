package com.example.viewpagerdemo;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;


/**
 * Created by mym_0314 on 2016/5/3.
 */
public class LoadOptions {
    private DisplayImageOptions op;

    public DisplayImageOptions buildOp(int img) {
        op = new DisplayImageOptions.Builder().showImageOnLoading(img)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                //图片中一个像素所占通道大小16*108*108
                //32*108*108
                .bitmapConfig(Bitmap.Config.ARGB_4444).build();
        return op;
    }
}
