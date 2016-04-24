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
public class CloudView extends View {

    private Paint leftCirclePaint;
    private Paint rightCirclePaint;
    private Paint topCirclePaint;
    private Paint rectPaint;

    public CloudView(Context context) {
        super(context);
    }

    public CloudView(Context context, AttributeSet attrs) {
        super(context, attrs);
        leftCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rightCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        topCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public CloudView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        leftCirclePaint.setAntiAlias(true);
        leftCirclePaint.setColor(Color.BLUE);

        rightCirclePaint.setAntiAlias(true);
        rightCirclePaint.setColor(Color.BLUE);

        topCirclePaint.setAntiAlias(true);
        topCirclePaint.setColor(Color.BLUE);

        rectPaint.setAntiAlias(true);
        rectPaint.setColor(Color.BLUE);

        float width = getWidth();
        float height = getHeight();
        float startX = getLeft();
        float endX = getRight();
        float startY = getTop();
        float endY = getBottom();

//        大圆的半径是小圆半径的1.5倍,view的宽度比高度为50:35,锁定的,下方未考虑除此之外的情况
        float smallRadius = width / height < (10 / 7) ? width / 5 : height / 7 * 2;
        float bigRadius = smallRadius * 3 / 2;


        RectF topCircleRF = new RectF(smallRadius, 0, width - smallRadius, bigRadius * 2);
        canvas.drawArc(topCircleRF, 180f, 180f, true, topCirclePaint);

        canvas.drawRect(smallRadius, smallRadius * 3 / 2, smallRadius * 4, height, rectPaint);

        RectF leftCircleRF = new RectF(0, bigRadius, smallRadius * 2, height);
        canvas.drawArc(leftCircleRF, 90f, 180f, true, leftCirclePaint);

        RectF rightCircleRF = new RectF(smallRadius * 3, bigRadius, width, height);
        canvas.drawArc(rightCircleRF, 270f, 180f, true, rightCirclePaint);
    }
}
