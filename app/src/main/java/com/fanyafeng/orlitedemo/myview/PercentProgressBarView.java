package com.fanyafeng.orlitedemo.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 365rili on 16/4/20.
 */
public class PercentProgressBarView extends View {

    private Paint reachPaint;
    private Paint unReachPaint;
    private Paint textPaint;

    private int reachPaintColor;
    private int unReachPaintColor;
    private int textPaintColor;

    private int percent;
    private String progressText;
    private String initText;
    private String finalText;

    public int getReachPaintColor() {
        return reachPaintColor;
    }

    public void setReachPaintColor(int reachPaintColor) {
        this.reachPaintColor = reachPaintColor;
        invalidate();
    }

    public int getUnReachPaintColor() {
        return unReachPaintColor;
    }

    public void setUnReachPaintColor(int unReachPaintColor) {
        this.unReachPaintColor = unReachPaintColor;
        invalidate();
    }

    public int getTextPaintColor() {
        return textPaintColor;
    }

    public void setTextPaintColor(int textPaintColor) {
        this.textPaintColor = textPaintColor;
        invalidate();
    }

    public String getInitText() {
        return initText;
    }

    public void setInitText(String initText) {
        this.initText = initText;
        setPercent(0, initText);
    }

    public String getFinalText() {
        return finalText;
    }

    public void setFinalText(String finalText) {
        this.finalText = finalText;
        setPercent(100, finalText);
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent, String progressText) {
        this.percent = percent;
        this.progressText = progressText;
        invalidate();
    }

    public String getProgressText() {
        return progressText;
    }

    public PercentProgressBarView(Context context) {
        super(context);
    }

    public PercentProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        reachPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        unReachPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint();
    }

    public PercentProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        reachPaint.setAntiAlias(true);
        if (reachPaintColor != 0) {
            reachPaint.setColor(reachPaintColor);
        } else {
            reachPaint.setColor(Color.parseColor("#3498DB"));
        }
        reachPaint.setStyle(Paint.Style.STROKE);

        unReachPaint.setAntiAlias(true);
        if (unReachPaintColor != 0) {
            unReachPaint.setColor(unReachPaintColor);
        } else {
            unReachPaint.setColor(Color.parseColor("#CCCCCC"));
        }
        unReachPaint.setStyle(Paint.Style.STROKE);

        textPaint.setAntiAlias(true);
        if (textPaintColor != 0) {
            textPaint.setColor(textPaintColor);
        } else {
            textPaint.setColor(Color.BLUE);
        }

        float endX = getRight();
        float startY = getTop();
        float endY = getBottom();
        float height = getHeight();

//        画笔所画的高度
        reachPaint.setStrokeWidth(height);
        unReachPaint.setStrokeWidth(height);


//        Log.d("TAG", "startx:" + startX + "endx:" + endX + "starty:" + startY + "endy:" + endY + "height:" + height + "width:" + width);

//        画完成进度
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setStrokeWidth(10);

        if (progressText != null) {

            if (percent >= 0 && percent <= 100) {

                Rect textBounds = new Rect();
                textPaint.getTextBounds(progressText, 0, progressText.length(), textBounds);
                Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
                float baseLine = (getMeasuredHeight() - fontMetricsInt.bottom + fontMetricsInt.top) / 2 - fontMetricsInt.top;

                float tempEndX = percent * (endX - textBounds.width()) / 100;

                Log.d("TAG", "percent数值:" + percent);
                Log.d("TAG", "进度条整体长度:" + endX);
                Log.d("TAG", "进度条显示:" + tempEndX);

//              进度
                canvas.drawText(progressText, tempEndX - textBounds.width(), baseLine, textPaint);
//              画左侧已完成
                canvas.drawLine(0, (endY - startY) / 2, tempEndX - (textBounds.width() + 30), (endY - startY) / 2, reachPaint);
//              画右侧未完成
                canvas.drawLine(tempEndX+textBounds.width()/2, (endY - startY) / 2, endX, (endY - startY) / 2, unReachPaint);

                /*
//              进度
                canvas.drawText(progressText, endX / 2 - textBounds.width() / 2, baseLine, textPaint);
//              画左侧已完成
                canvas.drawLine(0, (endY - startY) / 2, endX / 2 - textBounds.width() / 2 - 10, (endY - startY) / 2, reachPaint);
//              画右侧未完成
                canvas.drawLine(endX / 2 + textBounds.width() / 2 + 10, (endY - startY) / 2, endX, (endY - startY) / 2, unReachPaint);
**/
            }
        }


    }

}
