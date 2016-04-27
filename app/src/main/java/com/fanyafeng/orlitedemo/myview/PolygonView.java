package com.fanyafeng.orlitedemo.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by 365rili on 16/4/27.
 */
public class PolygonView extends View {
    //    竖线
    private Paint verPaint;
    //    横线
    private Paint porPaint;
    //    斜线
    private Paint slantPaint;
    //    写字
    private Paint textPaint;
    private static String[] listString = new String[]{"科技", "游戏", "音乐", "动画", "鬼畜", "影视"};


    public PolygonView(Context context) {
        super(context);
    }

    public PolygonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        verPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        porPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        slantPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public PolygonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float strokeWidth = 2;
        verPaint.setAntiAlias(true);
        verPaint.setColor(Color.GREEN);
        verPaint.setStrokeWidth(strokeWidth);

        porPaint.setAntiAlias(true);
        porPaint.setColor(Color.GREEN);
        porPaint.setStrokeWidth(strokeWidth);

        slantPaint.setAntiAlias(true);
        slantPaint.setColor(Color.BLUE);
        slantPaint.setStrokeWidth(strokeWidth);
        slantPaint.setStyle(Paint.Style.FILL);

        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStrokeWidth(10);

        float startX = getLeft();
        float endX = getRight();
        float startY = getTop();
        float endY = getBottom();
        float width = getWidth();
        float height = getHeight();

        Random random = new Random();

//        圆心
        float circleX = width / 2;
        float circleY = height / 2;
//        半径
        float circleRadius = width > height ? (height - 180) / 2 : (width - 180) / 2;
//        外接圆
//        canvas.drawCircle(circleX, circleY, circleRadius, verPaint);

//        每一度所对应的数值
        double eachAngleLength = 2 * Math.PI / 360;
//        *eachAngleLenght相当于响应的度数
        float eachAngle = 60;
//        int[] xList = new int[]{203, 100, 380, 89, 345, 300, 20, 144};
        int[] xList = new int[]{random.nextInt(400), random.nextInt(400), random.nextInt(400), random.nextInt(400), random.nextInt(400), random.nextInt(400), random.nextInt(400), random.nextInt(400)};
        for (int i = 0; i < 6; i++) {
//            与外接圆交点的x,y值
            float finalPointX = (float) (circleX + circleRadius * Math.sin(eachAngle * eachAngleLength * i));
            float finalPointY = (float) (circleY + circleRadius * Math.cos(eachAngle * eachAngleLength * i));
//          画竖线
            canvas.drawLine(circleX, circleY, finalPointX, finalPointY, verPaint);

//            横线起点x,y
            float porPointStartX = (float) (circleRadius * Math.sin(eachAngle * eachAngleLength * i));
            float porPointStartY = (float) (circleRadius * Math.cos(eachAngle * eachAngleLength * i));
//            横线终点x,y
            float porPointEndX = (float) (circleRadius * Math.sin(eachAngle * eachAngleLength * (i + 1)));
            float porPointEndY = (float) (circleRadius * Math.cos(eachAngle * eachAngleLength * (i + 1)));
            for (int j = 1; j < 5; j++) {
//                画横线
                canvas.drawLine(circleX + porPointStartX / 4 * j, circleY + porPointStartY / 4 * j, circleX + porPointEndX / 4 * j, circleY + porPointEndY / 4 * j, porPaint);
            }

//            写字,字体的x,y
            float textPointX = (float) (circleX + (circleRadius + 60) * Math.sin(eachAngle * eachAngleLength * i));
            float textPointY = (float) (circleY + (circleRadius + 60) * Math.cos(eachAngle * eachAngleLength * i));
            canvas.drawText(listString[i], 0, listString[i].length(), textPointX, i == 3 ? textPointY + 30 : textPointY, textPaint);

            /**
             * 在这着重说一下阴影部分
             * 阴影的话应该是个数组,如果把这个view写成控件的话,就是每个项目占得百分比,但是数据需要搞对,ok现在模拟一下数据
             */
            Path path = new Path();
            path.moveTo(circleX, circleY);
            path.lineTo((float) (circleX + xList[i] * Math.sin(eachAngle * eachAngleLength * i)), (float) (circleY + xList[i] * Math.cos(eachAngle * eachAngleLength * i)));
            path.lineTo((float) (circleX + (i != 5 ? xList[i + 1] : xList[0]) * Math.sin(eachAngle * eachAngleLength * (i + 1))), (float) (circleY + (i != 5 ? xList[i + 1] : xList[0]) * Math.cos(eachAngle * eachAngleLength * (i + 1))));
            path.close();
            canvas.drawPath(path, slantPaint);
        }


    }
}
