package com.warlodya.controllers;

public abstract class AbstractController {
    protected boolean upPressed;
    protected boolean downPressed;
    protected boolean leftPressed;
    protected boolean rightPressed;
    protected boolean shootPressed;
    protected float angle;

    public abstract void update();

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isShootPressed() {
        return shootPressed;
    }

    public float getAngle() {
        return angle;
    }
}
