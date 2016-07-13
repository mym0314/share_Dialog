package com.example.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

public class BitmapUtils {

    public static Bitmap getRoundBitmap(Context ctx, int resource) {
        Bitmap mBitmap = getScaleBitmap(ctx, resource);
        Bitmap bgBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(bgBitmap);

        Paint mPaint = new Paint();
        Rect mRect = new Rect(0, 0, 200, 200);

        mPaint.setAntiAlias(true);
        int roundPX = mBitmap.getWidth() / 4;

        mCanvas.drawCircle(mBitmap.getWidth() / 2, mBitmap.getHeight()  / 2, roundPX, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mCanvas.drawBitmap(mBitmap, mRect, mRect, mPaint);

        return bgBitmap;
    }

    public static Bitmap getScaleBitmap(Context ctx, int resource) {
        BitmapDrawable mBitmapDrawable = (BitmapDrawable) ctx.getResources().getDrawable(resource);

        assert mBitmapDrawable != null;
        Bitmap mBitmap = mBitmapDrawable.getBitmap();
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        Matrix mMatrix = new Matrix();
        mMatrix.preScale(0.2f, 0.2f);
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, mMatrix, true);
    }
}
