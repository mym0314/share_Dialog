package com.mym.weixin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by mym_0314 on 2016/5/1.
 */
public class ChangerColorIconWithText extends View {

    private Bitmap mIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    private String mText = "微信";
    private int mColor = 0xff45C01A;
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;

    private float mAlpha;

    private Rect mIconRect;
    private Rect mTextRect;
    private Paint mTextPaint;


    public ChangerColorIconWithText(Context context) {
        this(context, null);
    }

    public ChangerColorIconWithText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChangerColorIconWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChangerColorIconWithText);
        int count = a.getIndexCount();
        for (int i = 0; i < count; i++) {
            int index = a.getIndex(i);
            switch (index) {
                case R.styleable.ChangerColorIconWithText_icon:
                    BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(index);
                    if (drawable != null)
                        mIcon = drawable.getBitmap();
                    break;
                case R.styleable.ChangerColorIconWithText_color:
                    mColor = a.getColor(index, 0xff45C01A);
                    break;
                case R.styleable.ChangerColorIconWithText_text:
                    mText = a.getString(index);
                    break;
                case R.styleable.ChangerColorIconWithText_text_size:
                    //mTextSize = (int) a.getDimension(index, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
        mTextRect = new Rect();
        mTextPaint = new Paint();

        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0xFF555555);
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextRect);
        Log.e("TextView", mTextRect.width() + "onMeasure");

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //这个View的显示长度，取长宽中的较小值
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - mTextRect.height());
        //绘制图形的起始X，Y坐标
        int x = getMeasuredWidth() / 2 - iconWidth / 2;
        int y = (getMeasuredHeight() - mTextRect.height()) / 2 - iconWidth / 2;
        mIconRect = new Rect(x, y, x + iconWidth, y + iconWidth);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mIcon, null, mIconRect, null);
        int alpha = (int) Math.ceil(mAlpha * 255);
        //绘制Bitmap
        setUpBitmap(alpha);
        //绘制源文本,绘制颜色可变文本
        setUpSourceText(canvas, alpha);
        setUpColorText(canvas, alpha);

        canvas.drawBitmap(mBitmap, 0, 0, null);

    }

    private void setUpColorText(Canvas canvas, int alpha) {
        Log.e("TextView", mTextRect.width() + "setUpColorText");
        mTextPaint.setColor(mColor);
        mTextPaint.setAlpha(alpha);
        int x = getMeasuredWidth() / 2 - mTextRect.width() / 2;
        int y = mIconRect.bottom + mTextRect.height();
        canvas.drawText(mText, x, y, mTextPaint);
    }

    private void setUpSourceText(Canvas canvas, int alpha) {
        Log.e("TextView", mTextRect.width() + "setUpSourceText");
        mTextPaint.setColor(0xff333333);
        mTextPaint.setAlpha(255 - alpha);
        int x = getMeasuredWidth() / 2 - mTextRect.width() / 2;
        int y = mIconRect.bottom + mTextRect.height();
        canvas.drawText(mText, x, y, mTextPaint);
    }


    private void setUpBitmap(int alpha) {
        //创建一个Bitmap区域 作为底色，宽高为是这个View的快高
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_4444);
        //内存中绘制Bitmap
        mCanvas = new Canvas(mBitmap);
        //创建画笔
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setAlpha(alpha);
        mPaint.setDither(true);
        //开始画底色
        mCanvas.drawRect(mIconRect, mPaint);
        //设置图层属性为  相交显示底色
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);

        mCanvas.drawBitmap(mIcon, null, mIconRect, mPaint);
    }

    //使用alpha实现颜色渐变
    public void setIconAlpha(float alpha) {
        mAlpha = alpha;
        invalidateView();
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    private static final String SAVE_INSTANCE = "save_instance";
    private static final String SAVE_ALPHA = "save_alpha";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SAVE_INSTANCE, super.onSaveInstanceState());
        bundle.putFloat(SAVE_ALPHA, mAlpha);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {

        if ( state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mAlpha = bundle.getFloat(SAVE_ALPHA);
            super.onRestoreInstanceState(bundle.getParcelable(SAVE_INSTANCE));
        } else {
            super.onRestoreInstanceState(state);
        }
    }
}
