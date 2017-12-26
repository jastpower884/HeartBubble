package com.jastzeonic.heartbubble.view;

import java.util.Random;

/**
 * Created by jast.lai on 26/12/2017.
 */

public class HeartItem {


    private Random random = new Random();
    private int fallingLength = random.nextInt(3) + 1;

    /**
     * vibration amplitude 波幅
     */
    private final int amplitude = random.nextInt(10) + 20;

    /**
     * 波長 wavelength
     */
    private final int wavelength = random.nextInt(50 * fallingLength) + 50;
    private int startPointX = 0;

    private float pointX;
    private float pointY;


    private int viewHeight = 0;
    private int viewWidth = 0;

    private boolean isDisplay = false;


    public HeartItem(float pointX, float pointY) {
        setPointX(pointX);
//        this.pointX = pointX;
        this.pointY = pointY;
    }

    public float getPointX() {
        return pointX;
    }

    public void setPointX(float pointX) {
        this.pointX = pointX;
        this.startPointX = (int) pointX;
    }

    public float getPointY() {
        return pointY;
    }

    public void setPointY(float pointY) {
        this.pointY = pointY;
    }


    public void fallDown() {

        int fallDownPoint = (int) (getPointY() - fallingLength);

        if (fallDownPoint < -10) {
//            fallDownPoint = -10;
            setDisplay(false);
        }
        setPointY(fallDownPoint);

    }


    public void drift() {

        int driftPoint;
        double pointY = getPointY();
        // 象限角，這邊也作為角頻率
        double angularFrequency = pointY / wavelength;

        int movementDistance = (int) (amplitude * Math.sin(angularFrequency));
        driftPoint = startPointX + movementDistance;
        setPointX(driftPoint);

    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;
    }
}
