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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float strokeWidth = 70;
        float endStrokeWidth = strokeWidth + 30;

        roundCirclePaint.setAntiAlias(true);
        roundCirclePaint.setStyle(Paint.Style.STROKE);
        roundCirclePaint.setStrokeWidth(strokeWidth);
        roundCirclePaint.setColor(Color.BLUE);

        halfCirclePaint.setAntiAlias(true);
        halfCirclePaint.setStyle(Paint.Style.STROKE);
        halfCirclePaint.setStrokeWidth(strokeWidth);
        halfCirclePaint.setColor(Color.RED);

        endCirclePaint.setAntiAlias(true);
        endCirclePaint.setStyle(Paint.Style.STROKE);
        endCirclePaint.setStrokeWidth(endStrokeWidth);
        endCirclePaint.setColor(Color.RED);

        templePaint.setAntiAlias(true);
        templePaint.setStyle(Paint.Style.FILL);
        templePaint.setColor(Color.BLUE);
        templePaint.setTextSize(endStrokeWidth);
        templePaint.setTextAlign(Paint.Align.CENTER);
        templePaint.setStrokeWidth(strokeWidth);

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

        float startAngle = 109f;

        for (int i = 0; i < 65; i++) {
            float startItemAngle = startAngle + 5 * i;
            float spaceItemAngle = 1.0f;
            canvas.drawArc((i != 0 && i != 64) ? bottomHalfCircle : endHalfCircle, i == 64 ? startItemAngle + 0.5f : startItemAngle, (i == 0 || i == 64) ? spaceItemAngle - 0.5f : spaceItemAngle, false, (i != 0 && i != 64) ? halfCirclePaint : endCirclePaint);
        }

        Rect textBounds = new Rect();
        templePaint.getTextBounds(templeText, 0, templeText.length(), textBounds);
        Paint.FontMetricsInt fontMetricsInt = templePaint.getFontMetricsInt();
        float baseLine = (getMeasuredHeight() - fontMetricsInt.bottom + fontMetricsInt.top) / 2 - fontMetricsInt.top;
        canvas.drawText("24", roundY, baseLine, templePaint);
    }
}
