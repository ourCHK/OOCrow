package com.chk.oocrow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by chk on 17-12-26.
 */

public class MyChickView extends View{

    public static int DEVICE_WIDTH;
    public static int DEVICE_HEIGHT;
    int viewWidth;
    int viewHeight;
    Paint mPaint;
    Paint mEyePaint;
    Paint mMousePaint;

    Path mLeftPath;
    float mLeftXAnchor;
    float mLeftYAnchor;
    Path mRightPath;
    float mRightXAnchor;
    float mRightYAnchor;
    Path mTopPath;
    float mTopXAnchor;
    float mTopYAnchor;
    Path mBottomPath;
    float mBottomXAnchor;
    float mBottomYAnchor;

    int mPower;

    public MyChickView(Context context) {
        super(context);
        init();
    }

    public MyChickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    void init() {
        DisplayMetrics dm = getResources().getDisplayMetrics(); //获取屏幕尺寸大小
        DEVICE_WIDTH = dm.widthPixels;
        DEVICE_HEIGHT = dm.heightPixels;

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);

        mEyePaint = new Paint();
        mEyePaint.setColor(Color.BLACK);
        mEyePaint.setStrokeWidth(10);
        mEyePaint.setAntiAlias(true);


        mMousePaint = new Paint();
        mMousePaint.setColor(Color.BLACK);
        mMousePaint.setStyle(Paint.Style.STROKE);
        mMousePaint.setStrokeWidth(10);
        mMousePaint.setAntiAlias(true);

    }

    void pathInit() {
//        mLeftPath = new Path();
        mLeftXAnchor = 0;
        mLeftYAnchor = viewHeight/2+200;
//        mLeftPath.moveTo(viewWidth/2-300,viewHeight/2-300);
//        mLeftPath.quadTo(mLeftXAnchor,mLeftYAnchor,100,viewHeight/2+300);

//        mRightPath = new Path();
        mRightXAnchor = viewWidth;
        mRightYAnchor = viewHeight/2+200;
//        mRightPath.moveTo(viewWidth/2+300,viewHeight/2-300);
//        mRightPath.quadTo(mRightXAnchor,mRightYAnchor,viewWidth-100,viewHeight/2+300);

//        mTopPath = new Path();
        mTopXAnchor = viewWidth/2;
        mTopYAnchor = viewHeight/2-400;
//        mTopPath.moveTo(viewWidth/2-300,viewHeight/2-300);
//        mTopPath.quadTo(mTopXAnchor,mTopYAnchor,viewWidth/2+300,viewHeight/2-300);

//        mBottomPath = new Path();
        mBottomXAnchor = viewWidth/2;
        mBottomYAnchor = viewHeight/2+400;
//        mBottomPath.moveTo(100,viewHeight/2+300);
//        mBottomPath.quadTo(mBottomXAnchor,mBottomYAnchor,viewWidth-100,viewHeight/2+300);
    }

    void drawPath(Canvas canvas) {
//        mLeftPath.quadTo(mLeftXAnchor,mLeftYAnchor,100,viewHeight/2+300);
//        mRightPath.quadTo(mRightXAnchor,mRightYAnchor,viewWidth-100,viewHeight/2+300);
//        mTopPath.quadTo(mTopXAnchor,mTopYAnchor,viewWidth/2+300,viewHeight/2-300);
//        mBottomPath.quadTo(mBottomXAnchor,mBottomYAnchor,viewWidth-100,viewHeight/2+300);

        mLeftPath = new Path();
        mLeftPath.moveTo(viewWidth/2-300,viewHeight/2-300);
        mLeftPath.quadTo(mLeftXAnchor,mLeftYAnchor,200,viewHeight/2+300);

        mRightPath = new Path();
        mRightPath.moveTo(viewWidth/2+300,viewHeight/2-300);
        mRightPath.quadTo(mRightXAnchor,mRightYAnchor,viewWidth-200,viewHeight/2+300);

        mTopPath = new Path();
        mTopPath.moveTo(viewWidth/2-300,viewHeight/2-300);
        mTopPath.quadTo(mTopXAnchor,mTopYAnchor,viewWidth/2+300,viewHeight/2-300);

        mBottomPath = new Path();
        mBottomPath.moveTo(200,viewHeight/2+300);
        mBottomPath.quadTo(mBottomXAnchor,mBottomYAnchor,viewWidth-200,viewHeight/2+300);


        canvas.drawPath(mLeftPath,mPaint);
        canvas.drawPath(mRightPath,mPaint);
        canvas.drawPath(mTopPath,mPaint);
        canvas.drawPath(mBottomPath,mPaint);
    }

    void drawEye(Canvas canvas) {
        canvas.drawCircle(viewWidth/2-200,viewHeight/2-200,10, mEyePaint);
        canvas.drawCircle(viewWidth/2+200,viewHeight/2-200,10, mEyePaint);
    }

    void drawMouse(Canvas canvas) {
        canvas.drawLine(viewWidth/2,viewHeight/2-30,viewWidth/2+50,viewHeight/2,mMousePaint);
        canvas.drawLine(viewWidth/2+50,viewHeight/2,viewWidth/2,viewHeight/2+30,mMousePaint);
        canvas.drawLine(viewWidth/2,viewHeight/2+30,viewWidth/2-50,viewHeight/2,mMousePaint);
        canvas.drawLine(viewWidth/2-50,viewHeight/2,viewWidth/2,viewHeight/2-30,mMousePaint);
        canvas.drawLine(viewWidth/2+50,viewHeight/2,viewWidth/2-50,viewHeight/2,mMousePaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPath(canvas);
        drawEye(canvas);
        drawMouse(canvas);
    }

    public void setPower (int power) {
        mPower = power;
        mTopYAnchor = viewHeight/2-400-300f*power/100;
        mBottomYAnchor = viewHeight/2+400+100f*power/100;
        mLeftXAnchor = 100f*power/100;
        mRightXAnchor = viewWidth - 100f*power/100;
        invalidate();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = getWidth();
        viewHeight = getHeight();
        pathInit();
    }

    /**
     * 测量view的宽度
     * @param widthMeasureSpec
     * @return
     */
    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {  //父亲制定大小，对应match_parent
            result = specSize;
        } else {
            result = 50;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(50,specSize);
            }
        }
        return result;
    }

    /**
     * 测量view的高度
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {  //父亲制定大小，对应match_parent
            result = specSize;
        } else {
            //这样，当时用wrap_content时，View就获得一个默认值200px，而不是填充整个父布局
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {  //如果说父亲给的更小的话，那么就用更小的，默认是200
                result = Math.min(200,specSize);
            }
        }
        return result;
    }


}
