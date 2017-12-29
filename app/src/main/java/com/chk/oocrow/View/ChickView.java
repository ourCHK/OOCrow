package com.chk.oocrow.View;

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
 * 绘制小鸡自定义View
 */

public class ChickView extends View{

    public static int DEVICE_WIDTH;
    public static int DEVICE_HEIGHT;
    int viewWidth;
    int viewHeight;
    Paint mPaint;
    Paint mEyePaint;
    Paint mMouthPaint;

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

    public ChickView(Context context) {
        super(context);
        init();
    }

    public ChickView(Context context, @Nullable AttributeSet attrs) {
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
        mEyePaint.setStrokeWidth(10);
        mEyePaint.setAntiAlias(true);


        mMouthPaint = new Paint();
        mMouthPaint.setColor(Color.BLACK);
        mMouthPaint.setStyle(Paint.Style.STROKE);
        mMouthPaint.setStrokeWidth(10);
        mMouthPaint.setAntiAlias(true);

    }

    void pathInit() {
//        mLeftPath = new Path();
        mLeftXAnchor = 0;
        mLeftYAnchor = viewHeight/2+200;
//        mLeftPath.moveTo(mViewWidth/2-300,mViewHeight/2-300);
//        mLeftPath.quadTo(mLeftXAnchor,mLeftYAnchor,100,mViewHeight/2+300);

//        mRightPath = new Path();
        mRightXAnchor = viewWidth;
        mRightYAnchor = viewHeight/2+200;
//        mRightPath.moveTo(mViewWidth/2+300,mViewHeight/2-300);
//        mRightPath.quadTo(mRightXAnchor,mRightYAnchor,mViewWidth-100,mViewHeight/2+300);

//        mTopPath = new Path();
        mTopXAnchor = viewWidth/2;
        mTopYAnchor = viewHeight/2-400;
//        mTopPath.moveTo(mViewWidth/2-300,mViewHeight/2-300);
//        mTopPath.quadTo(mTopXAnchor,mTopYAnchor,mViewWidth/2+300,mViewHeight/2-300);

//        mBottomPath = new Path();
        mBottomXAnchor = viewWidth/2;
        mBottomYAnchor = viewHeight/2+400;
//        mBottomPath.moveTo(100,mViewHeight/2+300);
//        mBottomPath.quadTo(mBottomXAnchor,mBottomYAnchor,mViewWidth-100,mViewHeight/2+300);
    }

    void drawPath(Canvas canvas) {
//        mLeftPath.quadTo(mLeftXAnchor,mLeftYAnchor,100,mViewHeight/2+300);
//        mRightPath.quadTo(mRightXAnchor,mRightYAnchor,mViewWidth-100,mViewHeight/2+300);
//        mTopPath.quadTo(mTopXAnchor,mTopYAnchor,mViewWidth/2+300,mViewHeight/2-300);
//        mBottomPath.quadTo(mBottomXAnchor,mBottomYAnchor,mViewWidth-100,mViewHeight/2+300);

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
//        if (mPower != 0) {
//            canvas.save();
//            canvas.scale(1+mPower/50f,1+mPower/50f,mViewWidth/2-200,mViewHeight/2-200);
//            canvas.drawCircle(mViewWidth/2-200,mViewHeight/2-200,10, mEyePaint);
//            canvas.restore();
//            canvas.save();
//            canvas.scale(1+mPower/50f,1+mPower/50f,mViewWidth/2+200,mViewHeight/2-200);
//            canvas.drawCircle(mViewWidth/2+200,mViewHeight/2-200,10, mEyePaint);
//            canvas.restore();
//        } else {
//            canvas.scale(1,1,mViewWidth/2,mViewHeight/2);
//            canvas.drawCircle(mViewWidth/2-200,mViewHeight/2-200,10, mEyePaint);
//            canvas.drawCircle(mViewWidth/2+200,mViewHeight/2-200,10, mEyePaint);
//        }
        if (mPower == 0) {
            mEyePaint.setColor(Color.BLACK);
            canvas.drawLine(viewWidth/2-200,viewHeight/2-200,viewWidth/2-100,viewHeight/2-200, mEyePaint);
            canvas.drawLine(viewWidth/2+200,viewHeight/2-200,viewWidth/2+100,viewHeight/2-200, mEyePaint);
        } else {
            canvas.save();
            canvas.rotate(2*mPower/5f,viewWidth/2-125,viewHeight/2-200);
            canvas.drawLine(viewWidth/2-200,viewHeight/2-200,viewWidth/2-100,viewHeight/2-200, mEyePaint);
            canvas.restore();

            canvas.save();
            canvas.rotate(-2*mPower/5f,viewWidth/2+125,viewHeight/2-200);
            canvas.drawLine(viewWidth/2+200,viewHeight/2-200,viewWidth/2+100,viewHeight/2-200, mEyePaint);
            canvas.restore();
        }

        canvas.drawCircle(viewWidth/2-125,viewHeight/2-175,25,mEyePaint);
        canvas.drawCircle(viewWidth/2+125,viewHeight/2-175,25,mEyePaint);

        mEyePaint.setColor(Color.WHITE);
        float detal = (float)(12.5*Math.sin(Math.PI/4));
        canvas.drawCircle(viewWidth/2-125-detal,viewHeight/2-175-detal,12.5f,mEyePaint);
        canvas.drawCircle(viewWidth/2+125-detal,viewHeight/2-175-detal,12.5f,mEyePaint);
        mEyePaint.setColor(Color.BLACK);
    }

    void drawMouth(Canvas canvas) {
        canvas.save();
        canvas.scale(1-mPower/150f,1+mPower/100f,viewWidth/2,viewHeight/2);
        canvas.drawLine(viewWidth/2,viewHeight/2-30,viewWidth/2+50,viewHeight/2, mMouthPaint);
        canvas.drawLine(viewWidth/2+50,viewHeight/2,viewWidth/2,viewHeight/2+30, mMouthPaint);
        canvas.drawLine(viewWidth/2,viewHeight/2+30,viewWidth/2-50,viewHeight/2, mMouthPaint);
        canvas.drawLine(viewWidth/2-50,viewHeight/2,viewWidth/2,viewHeight/2-30, mMouthPaint);
        canvas.drawLine(viewWidth/2+50,viewHeight/2,viewWidth/2-50,viewHeight/2, mMouthPaint);
        canvas.restore();
    }

    void drawHair(Canvas canvas) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPath(canvas);
        drawEye(canvas);
        drawMouth(canvas);
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
