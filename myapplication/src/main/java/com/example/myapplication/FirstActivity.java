package com.example.myapplication;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.adapter.FristViewPagerAdapter;
import com.example.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class FirstActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private Button mBack;
    private TextView mTitle;
    private ImageView mMore;
    private HorizontalScrollView mScroll;
    private FrameLayout mFrame;
    private LinearLayout first_layout;
    private ViewPager mViewPager;

    private int mScreenWidth;
    private int mScreenHeight;
    private int count;

    private List<ImageView> bk = new ArrayList<>();
    private int[] mResource = new int[]{R.mipmap.a, R.mipmap.b,
            R.mipmap.c, R.mipmap.d, R.mipmap.f, R.mipmap.g};
    private Bitmap[] smallIcon = new Bitmap[mResource.length];
    private Boolean flag = false;
    private LinearLayout mGallary;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getScreenSize();
        setContentView(R.layout.activity_first);
        first_layout = (LinearLayout) findViewById(R.id.first_activity);
        mBack = (Button) findViewById(R.id.back);
        mTitle = (TextView) findViewById(R.id.title);
        mMore = (ImageView) findViewById(R.id.more);
        //mScroll = (HorizontalScrollView) findViewById(R.id.scroll);
        // mFrame = (FrameLayout) findViewById(R.id.Frame);

    }

    private void setViewPager() {
        mViewPager = new ViewPager(this);
        mViewPager.setLayoutParams(new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        first_layout.addView(mViewPager);
        FristViewPagerAdapter adapter = new FristViewPagerAdapter(this, mResource);
        mViewPager.setAdapter(adapter);
    }

    private void setGallary() {
        mScroll = new HorizontalScrollView(this);
        mScroll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, mScreenWidth / 3));
        mScroll.setHorizontalScrollBarEnabled(false);
        first_layout.addView(mScroll);
        mGallary = new LinearLayout(this);
        mGallary.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mGallary.setBackgroundColor(Color.WHITE);
        mGallary.setPadding(5, 5, 5, 5);
        mScroll.addView(mGallary);
        for (int i = 0; i < mResource.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(new LinearLayout.LayoutParams(
                    mScreenWidth / 3, LinearLayout.LayoutParams.MATCH_PARENT));
            iv.setBackgroundResource(R.drawable.biankuang2);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setTag(i);
            iv.setImageBitmap(smallIcon[i]);
            iv.setPadding(5, 5, 5, 5);
            if (i == 0) {
                iv.setBackgroundResource(R.drawable.biankuang);
                bk.add(iv);
            }
            mGallary.addView(iv);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count = (int) v.getTag();
                    mViewPager.setCurrentItem(count);
                }
            });
        }
    }


    @Override
    protected void initData() {
        for (int i = 0; i < mResource.length; i++) {
            smallIcon[i] = getSmallBitmap(mResource[i]);
        }
        setGallary();
        setViewPager();
    }

    private Bitmap getSmallBitmap(int id) {
        Bitmap bitmap = BitmapFactory.decodeStream(getResources().openRawResource(id));
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float dirWidth = ((float) mScreenWidth / 3) / width;
        float dirHeight = ((float) mScreenWidth / 3) / width;

        Matrix matrix = new Matrix();
        matrix.postScale(dirWidth, dirHeight);
        Log.e("FirstActivity", "width:" + width + "height:" + height);
        Log.e("FirstActivity", "dirWidth:" + dirWidth + "dirHeight:" + dirHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mMore.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                break;
            case R.id.more:
                break;
        }
    }

    public void getScreenSize() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
    }

    public void setViewPagerFull() {
        if (flag) {
            mScroll.setVisibility(View.GONE);
            flag = false;
        } else {
            mScroll.setVisibility(View.VISIBLE);
            flag = true;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    private Handler handler = new Handler();
    private Runnable mRunable = new Runnable() {
        @Override
        public void run() {
            if (count > 0) {
                mScroll.scrollTo((count - 1) * mScreenWidth / 3, 0);
            } else {
                mScroll.scrollTo(0, 0);
            }

        }
    };

    @Override
    public void onPageSelected(int position) {
        count = position;
        handler.postDelayed(mRunable, 0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
