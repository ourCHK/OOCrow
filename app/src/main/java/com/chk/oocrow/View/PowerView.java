package com.chk.oocrow.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by chk on 17-12-27.
 * 力量条自定义View
 */

public class PowerView extends View{

    public static int DEVICE_WIDTH;
    public static int DEVICE_HEIGHT;
    int mViewWidth;
    int mViewHeight;

    Paint mPaint;
    Paint mTextPaint;
    Paint mColorPaint;
    int mRectWidth;
    int mRectHeight;
    int mDetalHeight;

    int mPower = 0;


    public PowerView(Context context) {
        super(context);
    }

    public PowerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    void init() {
        DisplayMetrics dm = getResources().getDisplayMetrics(); //获取屏幕尺寸大小
        DEVICE_WIDTH = dm.widthPixels;
        DEVICE_HEIGHT = dm.heightPixels;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mTextPaint = new Paint();
        mTextPaint.setStrokeWidth(2);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mColorPaint = new Paint();
        mColorPaint.setColor(Color.GREEN);
        mColorPaint.setStrokeWidth(4);
    }

    void drawRects(Canvas canvas) {
        int startHeight = 4*mRectHeight;
        int startWidth = mViewWidth/10;
        int endWidth = mViewWidth*9/10;
        for (int i=0; i<20; i++) {
            canvas.drawRect(startWidth,startHeight+i*mDetalHeight,endWidth,startHeight+i*mDetalHeight+mRectHeight,mPaint);
        }
    }

    void drawColor(Canvas canvas) {
        if (mPower<5)
            return;
        if (mPower == 100)
            mColorPaint.setColor(Color.RED);
        int startHeight = 43*mRectHeight;
        int startWidth = mViewWidth/10;
        int endWidth = mViewWidth*9/10;
        for (int i=0; i<mPower/5; i++) {
            canvas.drawRect(startWidth,startHeight-mDetalHeight*i-mRectHeight,endWidth,startHeight-mDetalHeight*i,mColorPaint);
        }
        mColorPaint.setColor(Color.GREEN);
    }

    void drawTexts(Canvas canvas) {
        canvas.drawText(""+mPower,mViewWidth/2,mDetalHeight*23.5f,mTextPaint);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTexts(canvas);
        drawRects(canvas);
        drawColor(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = getWidth();
        mViewHeight = getHeight();

        mRectHeight = mViewHeight/48;
        mDetalHeight = mRectHeight * 2;
        mRectWidth = mViewWidth*4/5;

        mTextPaint.setTextSize(mRectHeight * 2);
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
            result = 100;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(100,specSize);
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

    public void setPower(int power) {
        mPower = power;
        invalidate();
    }


}
