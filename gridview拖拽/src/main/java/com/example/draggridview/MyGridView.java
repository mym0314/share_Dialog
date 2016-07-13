package com.example.draggridview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by mym_0314 on 2016/5/4.
 */
public class MyGridView extends GridView {
    private long dragResponseMS = 1000;

    private int startX;
    private int startY;
    private int moveX;
    private int moveY;

    private int mStatusHeight;

    private WindowManager winManager;
    private WindowManager.LayoutParams winParms;

    private int pointViewX;
    private int pointViewY;

    private int pointLayoutX;
    private int pointLayoutY;

    private int limitTop;
    private int limitBottom;
    private View mStartDragView;
    //mStartDragView 产生的虚像
    private Bitmap mBitmap;
    private ImageView mImageView;

    private int mDragPosition;

    private boolean isDrag = false;
    private Handler handler = new Handler();

    private Runnable mLongClickRun = new Runnable() {
        @Override
        public void run() {
            isDrag = true;
            mStartDragView.setVisibility(INVISIBLE);
            creatDragImage(mBitmap, startX, startY);
        }
    };
    private int mScrollY = 20;
    private onChangListener mChangListener;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isDrag && mImageView != null) {
            if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                moveX = (int) ev.getX();
                moveY = (int) ev.getY();
                Log.e("GridView",moveX +"****"+moveY);
                dragTo(moveX, moveY);
            } else if (ev.getAction() == MotionEvent.ACTION_UP) {
                dragStop();
                isDrag = false;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    private void dragStop() {
        View view = getChildAt(mDragPosition - getFirstVisiblePosition());
        if (view != null) {
            view.setVisibility(VISIBLE);
        }
        removeImage();
    }

    public void setOnChangListener(onChangListener mChangListener) {
        this.mChangListener = mChangListener;
    }

    private void removeImage() {
        if (mImageView != null) {
            winManager.removeView(mImageView);
            mImageView = null;
        }
    }

    private void dragTo(int moveX, int moveY) {
        winParms.x = moveX - pointViewX + pointLayoutX;
        winParms.y = moveY - pointViewY + pointLayoutY - mStatusHeight;
        winManager.updateViewLayout(mImageView, winParms);

        onSwipItem(moveX, moveY);
        handler.post(mScrollListener);
    }

    private Runnable mScrollListener = new Runnable() {
        @Override
        public void run() {
            int scrollY;
            if (moveY < limitTop) {
                scrollY = mScrollY;
                handler.postDelayed(mScrollListener, 25);
            } else if (moveY > limitBottom) {
                scrollY = -mScrollY;
                handler.postDelayed(mScrollListener, 25);
            } else {
                scrollY = 0;
                handler.postDelayed(mScrollListener, 25);
            }

            onSwipItem(moveX, moveY);

            smoothScrollBy(scrollY, 10);

        }
    };

    private void onSwipItem(int moveX, int moveY) {
        Log.e("GridView",moveX +"****"+moveY);
        int temPosition = pointToPosition(moveX,moveY);
        Log.e("GridView",mDragPosition +"****"+temPosition);
        if (mDragPosition != temPosition && temPosition != AdapterView.INVALID_POSITION) {
            if (mChangListener != null) {
                mChangListener.onChang(mDragPosition, temPosition);
            }
            getChildAt(temPosition - getFirstVisiblePosition()).setVisibility(INVISIBLE);
            getChildAt(mDragPosition - getFirstVisiblePosition()).setVisibility(VISIBLE);
            mDragPosition = temPosition;
        }
    }

    private void creatDragImage(Bitmap bitmap, int x, int y) {
        winParms = new WindowManager.LayoutParams();
        winParms.format = PixelFormat.TRANSLUCENT;
        winParms.gravity = Gravity.TOP | Gravity.LEFT;
        winParms.alpha = 0.55f;
        winParms.x = x - pointViewX + pointLayoutX;
        winParms.y = y - pointViewY + pointLayoutY - mStatusHeight;
        winParms.width = WindowManager.LayoutParams.WRAP_CONTENT;
        winParms.height = WindowManager.LayoutParams.WRAP_CONTENT;
        winParms.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mImageView = new ImageView(getContext());
        mImageView.setImageBitmap(bitmap);
        winManager.addView(mImageView, winParms);
    }

    public MyGridView(Context context) {
        this(context, null);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        winManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mStatusHeight = getStatusHeight(context);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            startX = (int) ev.getX();
            startY = (int) ev.getY();

            mDragPosition = pointToPosition(startX, startY);
            mDragPosition = pointToPosition(startX, startY);
            if (mDragPosition == AdapterView.INVALID_POSITION) {
                return super.dispatchTouchEvent(ev);
            }
            handler.postDelayed(mLongClickRun, dragResponseMS);

            mStartDragView = getChildAt(mDragPosition - getFirstVisiblePosition());

            pointViewX = startX - mStartDragView.getLeft();
            pointViewY = startY - mStartDragView.getTop();

            pointLayoutX = (int) (ev.getRawX() - startX);
            pointLayoutY = (int) (ev.getRawY() - startY);

            limitTop = getHeight() / 4;
            limitBottom = getHeight() * 3 / 4;

            mStartDragView.setDrawingCacheEnabled(true);
            mBitmap = Bitmap.createBitmap(mStartDragView.getDrawingCache());
            mStartDragView.destroyDrawingCache();

        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            int moveX = (int) ev.getX();
            int moveY = (int) ev.getY();
            if (!isTouchInItem(moveX, moveY)) {
                handler.removeCallbacks(mLongClickRun);
            }

        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            handler.removeCallbacks(mLongClickRun);
            handler.removeCallbacks(mScrollListener);
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isTouchInItem(int x, int y) {
        if (x < mStartDragView.getLeft() || x > mStartDragView.getLeft() + mStartDragView.getWidth()) {
            return false;
        }
        if (y < mStartDragView.getTop() || y > mStartDragView.getTop() + mStartDragView.getHeight()) {
            return false;
        }
        return true;
    }

    private int getStatusHeight(Context ctx) {
        int height = 0;
        Rect statusRect = new Rect();
        ((Activity) ctx).getWindow().getDecorView().getWindowVisibleDisplayFrame(statusRect);
        height = statusRect.top;
        if (height == 0) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                height = (int) ctx.getResources().getDimension(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return height;
    }

    interface onChangListener {
         void onChang(int from, int to);
    }
}
