package com.jastzeonic.heartbubble.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

public class EffectView extends View {

    private List<HeartItem> items = new ArrayList<>();

    private Paint circlePaint;

    private Paint mPaint;

    public EffectView(Context context) {
        super(context);
        init(context);

    }

    public EffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context c) {
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLUE);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.MITER);
        circlePaint.setStrokeWidth(4f);


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        for (HeartItem item : items) {

            canvas.drawCircle(item.getPointX(), item.getPointY(), 10, mPaint);
            item.fallDown();
            item.drift();
            mPaint.reset();
        }
        postInvalidateDelayed(15);
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {

        boolean reuse = false;

        for (HeartItem item : items) {
            if (!item.isDisplay()) {
                item.setPointX(x);
                item.setPointY(y);

                item.setViewWidth(getWidth());
                item.setViewHeight(getHeight());

                reuse = true;
                item.setDisplay(true);
                break;
            }
        }

        if (!reuse) {
            HeartItem item = new HeartItem(x, y);
            item.setDisplay(true);
            items.add(item);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
        }

        return true;
    }


}