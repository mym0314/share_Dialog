package com.example.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.text.BoringLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by mym_0314 on 2016/5/22.
 */
public class SlindingActivity extends RelativeLayout {
    private RelativeLayout mTop, mBottom;
    private float bottomWidth, mScreenWidth;


    private int startX, startY;

    private Boolean openState = false;
    private int sourceLeft;
    private int sourceRight;

    public SlindingActivity(Context context) {
        this(context, null);
    }

    public SlindingActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        bottomWidth = metrics.widthPixels * 4 / 5;
        initView();
    }

    private void initView() {
        mTop = new RelativeLayout(getContext());
        mTop.setBackgroundColor(Color.RED);
        mBottom = new RelativeLayout(getContext());
        mBottom.setBackgroundColor(Color.BLUE);
        addView(mBottom);
        addView(mTop);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            RelativeLayout.LayoutParams lp = (LayoutParams) mTop.getLayoutParams();
            sourceLeft = lp.leftMargin;
            sourceRight = lp.rightMargin;
            startX = (int) ev.getX();
            startY = (int) ev.getY();

        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            RelativeLayout.LayoutParams lp = (LayoutParams) mTop.getLayoutParams();
            int moveX = (int) ev.getX();
            int moveY = (int) ev.getY();
            float disX = moveX - startX;
            float disY = moveY - startY;
            //状态判断
            if (openState) {

                if (HorOrVer(disX, disY)) {
                    if (disX > 0) {
                        return super.dispatchTouchEvent(ev);
                    } else {
                        if (disX < -bottomWidth) {
                            disX = -bottomWidth;
                        }
                        lp.leftMargin = (int) (bottomWidth + disX);
                        lp.rightMargin = -lp.leftMargin;
                    }
                    mTop.setLayoutParams(lp);
                    invalidate();
                    return true;
                } else {
                    return super.dispatchTouchEvent(ev);
                }
            } else {
                if (startX > mScreenWidth / 5) return super.dispatchTouchEvent(ev);
                if (HorOrVer(disX, disY)) {
                    if (disX < 0) {
                        return super.dispatchTouchEvent(ev);
                    } else {
                        if (disX > bottomWidth) {
                            disX = bottomWidth;
                        }

                        lp.leftMargin = (int) disX;
                        lp.rightMargin = -lp.leftMargin;
                    }
                    mTop.setLayoutParams(lp);
                    invalidate();
                    return true;
                } else {
                    return super.dispatchTouchEvent(ev);
                }
            }
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (!openState && startX > mScreenWidth / 5) return super.dispatchTouchEvent(ev);
            RelativeLayout.LayoutParams lp = (LayoutParams) mTop.getLayoutParams();
            float disX = ev.getX() - startX;
            if (disX > 200 && !openState) {
                lp.leftMargin = (int) bottomWidth;
                lp.rightMargin = -lp.leftMargin;
                openState = true;
            } else if (disX < -200 && openState) {
                lp.leftMargin = 0;
                lp.rightMargin = 0;
                openState = false;
            } else {
                lp.leftMargin = sourceLeft;
                lp.rightMargin = sourceRight;
            }
            mTop.setLayoutParams(lp);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setBottom(View view) {
        mBottom.setLayoutParams(new RelativeLayout.LayoutParams(
                (int) bottomWidth, LayoutParams.MATCH_PARENT));
        mBottom.addView(view);
    }

    public void setTop(View view) {
        mTop.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        mTop.addView(view);
    }

    private boolean HorOrVer(float disX, float disY) {
        if (Math.abs(disX) > (Math.abs(disY) / 3))
            return true;
        return false;
    }

}
