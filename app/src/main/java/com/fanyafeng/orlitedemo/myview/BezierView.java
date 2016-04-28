package com.fanyafeng.orlitedemo.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 365rili on 16/4/27.
 */
public class BezierView extends View {
    private float staticX = 0.551915024494f;

    private Paint linePaint;
    private Paint circlePaint;

    public BezierView(Context context) {
        super(context);
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.RED);
        linePaint.setStrokeWidth(2);
        linePaint.setStyle(Paint.Style.FILL);

        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLUE);
        circlePaint.setStrokeWidth(2);
        circlePaint.setStyle(Paint.Style.STROKE);

        /**
         * 用贝塞尔曲线画圆
         * 贝塞尔曲线画圆一共需要十二个点
         * 假设圆心(a,b),半径r,偏移量△x=(4/3)*Math.tan(Math.PI/8)
         *
         * 以下为贝塞尔曲线画的四段圆心角为九十度的弧
         * p0:(a,b-r),p1:(a+x,b-r),p2:(a+r,b-x),p3:(a+r,b)
         * p3:(a+r,b),p4:(a+r,b+x),p5:(a+x,b+r),p6:(a,b+r)
         * p6:(a,b+r),p7:(a-x,b+r),p8:(a-r,b+x),p9:(a-r,b)
         * p9:(a-r,b),p10:(a-r,b-x),p11:(a-x,b-r),p0:(a,b-r)
         */

        float r = 100;
//        每度所对应的距离
        double eachAngleLength = 2 * Math.PI / 360;
        float mAngle = 0;
        float mLeft = (float) Math.sin(eachAngleLength * mAngle);
//        画圆所需的△x
//        double x = (0.551915024494f + M) * r;
        float x = staticX * r;

        float a = 400;
        float b = 400;
        PointF circlePoint = new PointF(a, b);
        HorPoint point0 = new HorPoint(a, b - r);
        VerPoint point3 = new VerPoint(a + r, b);
        HorPoint point6 = new HorPoint(a, b + r);
        VerPoint point9 = new VerPoint(a - r, b);

//        贝塞尔曲线画圆
//        左上角
        Path pathFour = new Path();
        pathFour.moveTo(point9.x, point9.y);
        pathFour.cubicTo(point9.x, point9.y - x, point0.x - x, point0.y, point0.x, point0.y);
        canvas.drawPath(pathFour, linePaint);
//        三角形补全,左上
        Path pathThreeTri = new Path();
        pathThreeTri.moveTo(circlePoint.x, circlePoint.y);
        pathThreeTri.lineTo(point0.x, point0.y);
        pathThreeTri.lineTo(point9.x, point9.y);
        pathThreeTri.close();
        canvas.drawPath(pathThreeTri, linePaint);

//        左下角
        Path pathThree = new Path();
        pathThree.moveTo(point6.x, point6.y);
        pathThree.cubicTo(point6.x - x, point6.y, point9.x, point9.y + x, point9.x, point9.y);
        canvas.drawPath(pathThree, linePaint);
//        三角形补全,左下
        Path pathFourTri = new Path();
        pathFourTri.moveTo(circlePoint.x, circlePoint.y);
        pathFourTri.lineTo(point6.x, point6.y);
        pathFourTri.lineTo(point9.x, point9.y);
        pathFourTri.close();
        canvas.drawPath(pathFourTri, linePaint);

//        右上角
        Path pathOne = new Path();
        pathOne.moveTo(point0.x, point0.y);
        pathOne.cubicTo(point0.x + x, point0.y, point3.x, point3.y - x, point3.x, point3.y);
        canvas.drawPath(pathOne, linePaint);
//        三角形补全,右上
        Path pathTwoTri = new Path();
        pathTwoTri.moveTo(circlePoint.x, circlePoint.y);
        pathTwoTri.lineTo(point3.x, point3.y);
        pathTwoTri.lineTo(point0.x, point0.y);
        pathTwoTri.close();
        canvas.drawPath(pathTwoTri, linePaint);

//        右下角
        Path pathTwo = new Path();
        pathTwo.moveTo(point3.x, point3.y);
        pathTwo.cubicTo(point3.x, point3.y + x, point6.x + x, point6.y, point6.x, point6.y);
        canvas.drawPath(pathTwo, linePaint);
//        三角形补全,右下
        Path pathOneTri = new Path();
        pathOneTri.moveTo(circlePoint.x, circlePoint.y);
        pathOneTri.lineTo(point6.x, point6.y);
        pathOneTri.lineTo(point3.x, point3.y);
        pathOneTri.close();
        canvas.drawPath(pathOneTri, linePaint);

//        canvas.drawCircle(a, b, r, circlePaint);

    }

    //    垂直移动和水平移动的点
    class VerPoint {
        public float x;
        public float y;
        public PointF top = new PointF();
        public PointF bottom = new PointF();

        public VerPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public void setX(float x) {
            this.x = x;
            top.x = x;
            bottom.x = x;
        }

        public void adjustY(float offset) {
            top.y -= offset;
            bottom.y += offset;
        }

        public void adjustAllX(float offset) {
            this.x += offset;
            top.x += offset;
            bottom.x += offset;
        }
    }

    //    水平移动的点
    class HorPoint {
        public float x;
        public float y;
        public PointF left = new PointF();
        public PointF right = new PointF();

        public HorPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public void setY(float y) {
            this.y = y;
            left.y = y;
            right.y = y;
        }

        public void adjustAllX(float offset) {
            this.x += offset;
            left.x += offset;
            right.x += offset;
        }
    }

}
