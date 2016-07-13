package com.example.crazyandroid.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mym_0314 on 2016/5/28.
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3f);
        //画一个圆
        canvas.drawCircle(40,40,30,paint);
        //画一个正方形
        canvas.drawRect(10,80,70,140,paint);
        //矩形
        canvas.drawRect(10, 150, 70, 190, paint);





    }
}
