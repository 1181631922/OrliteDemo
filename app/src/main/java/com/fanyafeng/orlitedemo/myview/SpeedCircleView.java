package com.fanyafeng.orlitedemo.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 365rili on 16/4/22.
 */
public class SpeedCircleView extends View {

    private Paint roundCirclePaint;
    private Paint halfCirclePaint;
    private Paint endCirclePaint;
    private Paint templePaint;
    private Paint statusShapPaint;
    private Paint lightShapPaint;
    private Paint percentCirclePaint;
    private Paint percentEndCirclePaint;
    private int percent;
    private int tempPercent = 0;
    private int tempLight = 0;
    private String templeText = "24";

    public SpeedCircleView(Context context) {
        super(context);
    }

    public SpeedCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        roundCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        halfCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        endCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        templePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        statusShapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lightShapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        percentCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        percentEndCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public SpeedCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getTempleText() {
        return templeText;
    }

    public void setTempleText(String templeText) {
        this.templeText = templeText;
        invalidate();
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float strokeWidth = 70;
        float endStrokeWidth = strokeWidth + 30;

        roundCirclePaint.setAntiAlias(true);
        roundCirclePaint.setStyle(Paint.Style.STROKE);
        roundCirclePaint.setStrokeWidth(strokeWidth);
        roundCirclePaint.setColor(Color.RED);

        lightShapPaint.setAntiAlias(true);
        lightShapPaint.setStyle(Paint.Style.STROKE);
        lightShapPaint.setColor(Color.RED);

        halfCirclePaint.setAntiAlias(true);
        halfCirclePaint.setStyle(Paint.Style.STROKE);
        halfCirclePaint.setStrokeWidth(strokeWidth);
        halfCirclePaint.setColor(Color.RED);

        percentCirclePaint.setAntiAlias(true);
        percentCirclePaint.setStyle(Paint.Style.STROKE);
        percentCirclePaint.setStrokeWidth(strokeWidth);
        percentCirclePaint.setColor(Color.GREEN);

        endCirclePaint.setAntiAlias(true);
        endCirclePaint.setStyle(Paint.Style.STROKE);
        endCirclePaint.setStrokeWidth(endStrokeWidth);
        endCirclePaint.setColor(Color.RED);

        percentEndCirclePaint.setAntiAlias(true);
        percentEndCirclePaint.setStyle(Paint.Style.STROKE);
        percentEndCirclePaint.setStrokeWidth(endStrokeWidth);
        percentEndCirclePaint.setColor(Color.GREEN);

        templePaint.setAntiAlias(true);
        templePaint.setStyle(Paint.Style.FILL);
        templePaint.setColor(Color.BLUE);
        templePaint.setTextSize(endStrokeWidth);
        templePaint.setTextAlign(Paint.Align.CENTER);
        templePaint.setStrokeWidth(strokeWidth);

        statusShapPaint.setAntiAlias(true);
        statusShapPaint.setStyle(Paint.Style.FILL);
        statusShapPaint.setStrokeWidth(endStrokeWidth);
        statusShapPaint.setColor(Color.RED);

        float startX = getLeft();
        float endX = getRight();
        float startY = getTop();
        float endY = getBottom();
        float height = getHeight();
        float width = getWidth();

        float roundRadius = (endY > endX) ? (endX - startX) / 2 - strokeWidth / 2 : (endY - startY) / 2 - strokeWidth / 2;
        float roundX = width / 2;
        float roundY = height / 2;
//        画最外环的圆
//        canvas.drawCircle(roundX, roundY, roundRadius, roundCirclePaint);
//        给太阳留出显示的位置,先画相同的背景色遮盖圆环,或者直接画外环
        RectF bottomHalfCircle = new RectF(strokeWidth / 2, strokeWidth / 2, width - strokeWidth / 2, height - strokeWidth / 2);
//        canvas.drawArc(bottomHalfCircle, 110f, 320f, false, halfCirclePaint);
        RectF endHalfCircle = new RectF(strokeWidth - endStrokeWidth / 2, strokeWidth - endStrokeWidth / 2, width - strokeWidth + endStrokeWidth / 2, height - strokeWidth + endStrokeWidth / 2);

//        每一个percent对应3度
        float startAngle = 119f;
        float endI = 101;

        for (int i = 0; i < endI; i++) {
            float startItemAngle = startAngle + 3 * i;
            float spaceItemAngle = 1.0f;
            canvas.drawArc((i != 0 && i != (endI - 1)) ? bottomHalfCircle : endHalfCircle, i == (endI - 1) ? startItemAngle + 0.5f : startItemAngle, (i == 0 || i == (endI - 1)) ? spaceItemAngle - 0.5f : spaceItemAngle, false, (i != 0 && i != (endI - 1)) ? halfCirclePaint : endCirclePaint);
        }


        for (int i = 0; i < tempPercent; i++) {
            float startItemAngle = startAngle + 3 * i;
            float spaceItemAngle = 1.0f;
            canvas.drawArc((i != 0 && i != (endI - 1)) ? bottomHalfCircle : endHalfCircle, i == (endI - 1) ? startItemAngle + 0.5f : startItemAngle, (i == 0 || i == (endI - 1)) ? spaceItemAngle - 0.5f : spaceItemAngle, false, (i != 0 && i != (endI - 1)) ? percentCirclePaint : percentEndCirclePaint);
        }

//      中间温度数值
        Rect textBounds = new Rect();
        templePaint.getTextBounds(templeText, 0, templeText.length(), textBounds);
        Paint.FontMetricsInt fontMetricsInt = templePaint.getFontMetricsInt();
        float baseLine = (getMeasuredHeight() - fontMetricsInt.bottom + fontMetricsInt.top) / 2 - fontMetricsInt.top;
        canvas.drawText(tempPercent + "°", roundY, baseLine, templePaint);

//        画底部的太阳
//        先画一个圆,然后再去画光芒,简单来说就是先确认太阳的圆心,剩下的也就好做了
        float statusRadius = strokeWidth / 2;
        float statusShapX = width / 2;
        float statusShapY = height - strokeWidth;
        canvas.drawCircle(statusShapX, statusShapY, statusRadius, statusShapPaint);
//        画光芒
        float lightWidth = statusRadius - 5;
        lightShapPaint.setStrokeWidth(lightWidth);
        RectF lightShap = new RectF(statusShapX - statusRadius * 2 + lightWidth / 2, statusShapY - statusRadius * 2 + lightWidth / 2, statusShapX + statusRadius * 2 - lightWidth / 2, statusShapY + statusRadius * 2 - lightWidth / 2);


        float spaceAngle = 10f;
        float spaceItemAngle = 3.0f;
        tempLight++;
        for (int i = 0; i < 360f / spaceAngle; i++) {
            float startItemAngle = spaceAngle * i + tempLight;
            canvas.drawArc(lightShap, startItemAngle, spaceItemAngle, false, lightShapPaint);
        }
        canvas.save();
        postDelayed(refresh, 100);
        canvas.restore();

    }

    private Runnable refresh = new Runnable() {
        @Override
        public void run() {
            if (tempPercent < percent) {
                tempPercent++;
                invalidate();
            }

        }
    };
}
