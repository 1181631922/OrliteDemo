package com.fanyafeng.orlitedemo.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 365rili on 16/4/24.
 */
public class SunCloudView extends View {

    private Paint sunPain;
    private Paint sunLightPaint;
    private Paint leftCloudPaint;
    private Paint rightCloudPaint;
    private Paint rightSidePaint;
    private Paint topCloudPaint;
    private Paint topSidePaint;
    private Paint reactPaint;

    public SunCloudView(Context context) {
        super(context);
    }

    public SunCloudView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sunPain = new Paint(Paint.ANTI_ALIAS_FLAG);
        sunLightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        leftCloudPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rightCloudPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rightSidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        topCloudPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        topSidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        reactPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public SunCloudView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float strokeWidth = 20;
        float sideStrokeWidth = 10;

        sunPain.setAntiAlias(true);
        sunPain.setColor(Color.BLUE);

        sunLightPaint.setAntiAlias(true);
        sunLightPaint.setColor(Color.BLUE);
        sunLightPaint.setStyle(Paint.Style.STROKE);
        sunLightPaint.setStrokeWidth(strokeWidth);

        leftCloudPaint.setAntiAlias(true);
        leftCloudPaint.setColor(Color.BLUE);

        rightCloudPaint.setAntiAlias(true);
        rightCloudPaint.setColor(Color.BLUE);

        rightSidePaint.setAntiAlias(true);
        rightSidePaint.setColor(Color.WHITE);
        rightSidePaint.setStrokeWidth(sideStrokeWidth);
        rightSidePaint.setStyle(Paint.Style.STROKE);

        topCloudPaint.setAntiAlias(true);
        topCloudPaint.setColor(Color.BLUE);

        topSidePaint.setAntiAlias(true);
        topSidePaint.setColor(Color.WHITE);
        topSidePaint.setStrokeWidth(sideStrokeWidth);
        topSidePaint.setStyle(Paint.Style.STROKE);

        reactPaint.setAntiAlias(true);
        reactPaint.setColor(Color.BLUE);

        float width = getWidth();
        float height = getHeight();
        float startX = getLeft();
        float endX = getRight();
        float startY = getTop();
        float endY = getBottom();

//        画云彩
        float smallCircleRadius = width / 6;
        float bigCircleRadius = smallCircleRadius / 2 * 3;

        //        太阳
        canvas.drawCircle(smallCircleRadius * 4, height - smallCircleRadius / 2 * 5, smallCircleRadius, sunPain);
//        光芒
        RectF sunLightRF = new RectF(smallCircleRadius / 2 * 5 + strokeWidth, 0 + strokeWidth, smallCircleRadius / 2 * 11 - strokeWidth, height - smallCircleRadius - strokeWidth);
        float spaceAngle = 2;
        for (int i = 0; i < 36; i++) {
            float startAngle = i * 10;
            canvas.drawArc(sunLightRF, startAngle, spaceAngle, false, sunLightPaint);
        }

        canvas.drawRect(smallCircleRadius, height - smallCircleRadius * 2, smallCircleRadius * 4, height, reactPaint);
//        左边
        RectF leftCircleRF = new RectF(0, height - smallCircleRadius * 2, smallCircleRadius * 2, height);
        canvas.drawArc(leftCircleRF, 90f, 180f, true, leftCloudPaint);
//        右边
        RectF rightCircleRF = new RectF(smallCircleRadius * 3, height - smallCircleRadius * 2, 5 * smallCircleRadius, height);
        canvas.drawArc(rightCircleRF, 270f, 180f, true, rightCloudPaint);
//        右边白边
        RectF rightSideRF = new RectF(smallCircleRadius * 3 - sideStrokeWidth/2, height - smallCircleRadius * 2 - sideStrokeWidth/2, smallCircleRadius * 5 + sideStrokeWidth/2, height + sideStrokeWidth/2);
        canvas.drawArc(rightSideRF, 270f, 180f, false, rightSidePaint);

//        上面
        RectF topCircleRF = new RectF(smallCircleRadius, smallCircleRadius / 2, smallCircleRadius * 4, height - smallCircleRadius / 2);
        canvas.drawArc(topCircleRF, 180f, 180f, true, topCloudPaint);
//        上面白边
        RectF topSideRF = new RectF(smallCircleRadius - sideStrokeWidth/2, smallCircleRadius / 2 - sideStrokeWidth/2, smallCircleRadius * 4 + sideStrokeWidth/2, height - smallCircleRadius / 2 + sideStrokeWidth/2);
        canvas.drawArc(topSideRF, 180f, 180f, false, topSidePaint);



    }
}
