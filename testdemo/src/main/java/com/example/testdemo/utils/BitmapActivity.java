package com.example.testdemo.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.testdemo.R;

/**
 * Created by mym_0314 on 2016/5/19.
 */
public class BitmapActivity extends Activity {
    private ImageView iv1, iv2, iv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        assert ((BitmapDrawable)getResources().getDrawable(R.mipmap.g)) != null;
        iv1.setImageBitmap(((BitmapDrawable)getResources().getDrawable(R.mipmap.g)).getBitmap());
        Bitmap b2= BitmapUtils.getRoundBitmap(this, R.mipmap.g);
        Bitmap b3 = BitmapUtils.getScaleBitmap(this, R.mipmap.g);
        iv2.setImageBitmap(b3);
        iv3.setImageBitmap(b2);
    }

    private void initView() {
        setContentView(R.layout.activity_bitmap);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
    }
}
