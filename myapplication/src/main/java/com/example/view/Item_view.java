package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.myapplication.R;

import java.lang.reflect.TypeVariable;

/**
 * Created by mym_0314 on 2016/5/23.
 * <attr name="icon"></attr>
 * <attr name="color"></attr>
 * <attr name="text"></attr>
 * <attr name="text_size"></attr>
 */
public class Item_view extends View {
    private String mText = "属性标题";
    private int mTextColor = 0xffffffff;
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
    private Bitmap mIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    private Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

    private Paint textPaint;
    private Paint iconPaint;
    private Rect textRect;
    private Rect iconRect;

    public Item_view(Context context) {
        this(context, null);
    }

    public Item_view(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Item_view(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Item_view);
        int count = a.getIndexCount();
        for (int i = 0; i < count; i++) {
            int index = a.getIndex(i);
            switch (index) {
                case R.styleable.Item_view_text:
                    mText = a.getString(index);
                    break;
                case R.styleable.Item_view_textColor:
                    mTextColor = a.getColor(index, 0x00000000);
                    break;
                case R.styleable.Item_view_icon:
                    BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(index);
                    if (drawable != null)
                        mIcon = drawable.getBitmap();
                    break;
                case R.styleable.Item_view_textSize:
                    mTextSize = (int) a.getDimension(index, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
        textPaint = new Paint();
        textRect = new Rect();

        textPaint.setTextSize(mTextSize);
        textPaint.setColor(mTextColor);
        textPaint.getTextBounds(mText, 0, mText.length(), textRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iconWidth = Math.min(getMeasuredWidth() - textRect.width() - getPaddingLeft() - getPaddingRight(),
                getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
        int x = getMeasuredWidth() - mBitmap.getWidth() - mIcon.getWidth();
        int y = getMeasuredHeight() / 2 - mIcon.getHeight() / 2;
        iconRect = new Rect(x, y, x + iconWidth, y + iconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setUpText(canvas);
        setUpIcon(canvas);
    }

    private void setUpIcon(Canvas canvas) {
        iconPaint = new Paint();
        iconPaint.setAntiAlias(true);
        iconPaint.setDither(true);
        canvas.drawBitmap(mIcon, null, iconRect, iconPaint);
    }

    private void setUpText(Canvas canvas) {
        int x = getPaddingLeft();
        int y = (getMeasuredHeight() + getPaddingTop() + getPaddingBottom()) / 2 + (int) textPaint.getFontMetrics().descent;
        canvas.drawText(mText, x, y, textPaint);
    }


}
