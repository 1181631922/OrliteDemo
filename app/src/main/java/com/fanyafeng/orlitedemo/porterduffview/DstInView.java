package com.fanyafeng.orlitedemo.porterduffview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 365rili on 16/4/25.
 */
public class DstInView extends View {
    private Paint circlePaint;
    private Paint rectPaint;

    public DstInView(Context context) {
        super(context);
    }

    public DstInView(Context context, AttributeSet attrs) {
        super(context, attrs);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public DstInView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.GREEN);

        rectPaint.setAntiAlias(true);
        rectPaint.setColor(Color.BLUE);

        float startX = getLeft();
        float endX = getRight();
        float startY = getTop();
        float endY = getBottom();
        float width = getWidth();
        float height = getHeight();

        float circleRadius = width / 3;
        float circleX = width / 3;
        float circleY = width / 3;

        float rectA = width / 3;
        float rectB = width / 3;
        float rectC = width;
        float rectD = height;

//        src为原图,dst为目标图
//        circlePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        canvas.drawCircle(circleX, circleY, circleRadius, circlePaint);

//        rectPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        canvas.drawRect(rectA, rectB, rectC, rectD, rectPaint);

//        src为原图,dst为目标图
//        Canvas canvas1=new Canvas(src);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//        canvas1.drawBitmap(dst,0f,0f,paint);

    }
}
