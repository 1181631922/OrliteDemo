package com.fanyafeng.orlitedemo.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fanyafeng.orlitedemo.util.MyUtils;

/**
 * Created by 365rili on 16/4/20.
 */
public class CircleLoadingView extends View {

    private Paint circleContour;
    private double durSpace = 0;

    private CharSequence message;
    private StaticLayout messageLayout;
    private static TextPaint textPaint;

    private int circleContourColor;
    private int messageColor;

    public int getCircleContourColor() {
        return circleContourColor;
    }

    public void setCircleContourColor(int circleContourColor) {
        this.circleContourColor = circleContourColor;
        invalidate();
    }

    public int getMessageColor() {
        return messageColor;
    }

    public void setMessageColor(int messageColor) {
        this.messageColor = messageColor;
        setMessage(getMessage());
    }

    public CharSequence getMessage() {
        return message;
    }

    public void setMessage(CharSequence message) {
        this.message = message;
        if (message != null && !message.equals("")) {
            textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
            if (getMessageColor() != 0) {
                textPaint.setColor(getMessageColor());
            } else {
                textPaint.setColor(Color.parseColor("#3498DB"));
            }
            textPaint.setTextSize(14 * MyUtils.getDensity(getContext()));
            textPaint.setStyle(Paint.Style.FILL);
            messageLayout = new StaticLayout(message, textPaint, MyUtils.getScreenWidth(getContext()) / 4, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            invalidate();
        }

    }

    public CircleLoadingView(Context context) {
        super(context);
    }

    public CircleLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        circleContour = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public CircleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float circleContourStrokeWidth = 5;
        circleContour.setAntiAlias(true);
        if (getCircleContourColor() != 0) {
            circleContour.setColor(getCircleContourColor());
        } else {
            circleContour.setColor(Color.parseColor("#3498DB"));
        }
//        circleContour.setStyle(Paint.Style.STROKE);
        circleContour.setStrokeWidth(circleContourStrokeWidth);


        float startX = getLeft();
        float endX = getRight();
//        Log.d("TAG","得到的endX的数值:"+endX);
        float startY = getTop();
        float endY = getBottom();
        float height = getHeight();
        float width = getWidth();
//        轮廓的半径
        float circleContourRadius = (endY > endX ? (endX - startX) / 2 - circleContourStrokeWidth / 2 : (endY - startY) / 2 - circleContourStrokeWidth / 2);
        float circleCountourX;
        float circleCountourY;
        if (endX > endY) {
            circleCountourX = width / 2;
            circleCountourY = height / 2;
        } else {
            circleCountourX = circleCountourY = width / 2;
        }

//        canvas.drawCircle(circleCountourX, circleCountourY, circleContourRadius, circleContour);

//        画一条垂直的线
//        canvas.drawLine(circleCountourX, circleCountourY, circleCountourX, circleCountourY - circleContourRadius, circleContour);

//        根据公式推导,每个间隔所占的角度为:11.25度,最小的圆的度数为7.5度,每个圆以7.5度等差增长,一共需要画8个圆
//        第一个:sin3.75*第一个大圆的半径   sin7.5  sin11.25 sin15 sin18.75 sin22.5 sin26.25 sin30
//        内接大圆的直径为外接大圆半径的三分之二
        float radius = circleContourRadius / 3;
//        loading圆们距离圆心的距离
        float circleLength = 2 * radius;
//        每一度所对应的数值
        double everyRadiusLength = 2 * Math.PI / 360;

//        Log.d("TAG", "圆心距离:" + circleLength + "每一度的距离:" + everyRadiusLength);

//      每个间隔的角度
        double spaceAngle = 11.25;

        //      画最后一个要旋转的圆(大圆)
//      canvas.drawCircle(circleCountourX, circleCountourY - radius * 2, (float) eighthRadius, circleContour);
        double eighthAngle = durSpace + 30;
        double eighthX = circleCountourX - circleLength * Math.sin(eighthAngle * everyRadiusLength);
        double eighthY = circleCountourY - circleLength * Math.cos(eighthAngle * everyRadiusLength);
        double eighthRadius = circleLength * Math.sin(30 * everyRadiusLength);
        canvas.drawCircle((float) eighthX, (float) eighthY, (float) eighthRadius, circleContour);

        double seventhAngle = durSpace + 30 * 2 + spaceAngle + 26.25;
        double seventhX = circleCountourX - circleLength * Math.sin(seventhAngle * everyRadiusLength);
        double seventhY = circleCountourY - circleLength * Math.cos(seventhAngle * everyRadiusLength);
        double seventhRadius = circleLength * Math.sin(26.25 * everyRadiusLength);
        canvas.drawCircle((float) seventhX, (float) seventhY, (float) seventhRadius, circleContour);

        double sixthAngle = durSpace + 30 * 2 + spaceAngle + 26.25 * 2 + spaceAngle + 22.5;
        double sixthX = circleCountourX - circleLength * Math.sin(sixthAngle * everyRadiusLength);
        double sixthY = circleCountourY - circleLength * Math.cos(sixthAngle * everyRadiusLength);
        double sixthRadius = circleLength * Math.sin(22.5 * everyRadiusLength);
        canvas.drawCircle((float) sixthX, (float) sixthY, (float) sixthRadius, circleContour);

        double fifthAngle = durSpace + 30 * 2 + spaceAngle + 26.25 * 2 + spaceAngle + 22.5 * 2 + spaceAngle + 18.75;
        double fifthX = circleCountourX - circleLength * Math.sin(fifthAngle * everyRadiusLength);
        double fifthY = circleCountourY - circleLength * Math.cos(fifthAngle * everyRadiusLength);
        double fifthRadius = circleLength * Math.sin(18.75 * everyRadiusLength);
        canvas.drawCircle((float) fifthX, (float) fifthY, (float) fifthRadius, circleContour);

        double forthAngle = durSpace + 30 * 2 + spaceAngle + 26.25 * 2 + spaceAngle + 22.5 * 2 + spaceAngle + 18.75 * 2 + spaceAngle + 15;
        double forthX = circleCountourX - circleLength * Math.sin(forthAngle * everyRadiusLength);
        double forthY = circleCountourY - circleLength * Math.cos(forthAngle * everyRadiusLength);
        double forthRadius = circleLength * Math.sin(15 * everyRadiusLength);
        canvas.drawCircle((float) forthX, (float) forthY, (float) forthRadius, circleContour);

        double thirdAngle = durSpace + 30 * 2 + spaceAngle + 26.25 * 2 + spaceAngle + 22.5 * 2 + spaceAngle + 18.75 * 2 + spaceAngle + 15 * 2 + spaceAngle + 11.25;
        double thirdX = circleCountourX - circleLength * Math.sin(thirdAngle * everyRadiusLength);
        double thirdY = circleCountourY - circleLength * Math.cos(thirdAngle * everyRadiusLength);
        double thirdRadius = circleLength * Math.sin(11.25 * everyRadiusLength);
        canvas.drawCircle((float) thirdX, (float) thirdY, (float) thirdRadius, circleContour);

        double secondAngle = durSpace + 30 * 2 + spaceAngle + 26.25 * 2 + spaceAngle + 22.5 * 2 + spaceAngle + 18.75 * 2 + spaceAngle + 15 * 2 + spaceAngle + 11.25 * 2 + spaceAngle + 7.5;
        double secondX = circleCountourX - circleLength * Math.sin(secondAngle * everyRadiusLength);
        double secondY = circleCountourY - circleLength * Math.cos(secondAngle * everyRadiusLength);
        double secondRadius = circleLength * Math.sin(7.5 * everyRadiusLength);
        canvas.drawCircle((float) secondX, (float) secondY, (float) secondRadius, circleContour);

//      求出第一个圆的x和y的位置
        double firstAngle = durSpace + 30 * 2 + spaceAngle + 26.25 * 2 + spaceAngle + 22.5 * 2 + spaceAngle + 18.75 * 2 + spaceAngle + 15 * 2 + spaceAngle + 11.25 * 2 + spaceAngle + 7.5 * 2 + spaceAngle + 3.75;
        double firstX = circleCountourX - circleLength * Math.sin(firstAngle * everyRadiusLength);
        double firstY = circleCountourY - circleLength * Math.cos(firstAngle * everyRadiusLength);
        double firstRadius = circleLength * Math.sin(3.75 * everyRadiusLength);
        canvas.drawCircle((float) firstX, (float) firstY, (float) firstRadius, circleContour);

//        Log.d("TAG", "第一个圆半径:" + firstRadius + "第二个圆半径:" + secondRadius + "第三个圆半径:" + thirdRadius + "第四个圆半径:" + forthRadius);
//        Log.d("TAG", "第五个圆半径:" + fifthRadius + "第六个圆半径:" + sixthRadius + "第七个圆半径:" + seventhRadius + "第八个圆半径:" + eighthRadius);
//        同心圆
//        canvas.drawCircle(circleCountourX, circleCountourY, circleLength, circleContour);
        canvas.save();
        canvas.translate(0, 2 * circleContourRadius + 50);
        messageLayout.draw(canvas);
        canvas.restore();

        postDelayed(refresh, 37);
    }

    private Runnable refresh = new Runnable() {
        @Override
        public void run() {
            durSpace -= 3;
            invalidate();
        }
    };
}
