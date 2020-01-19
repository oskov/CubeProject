package com.warlodya.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BulletComponent implements Component, Poolable {
    public float startX;
    public float startY;
    public float xVel;
    public float yVel;
    public float maxLenght;
    public float damage;
    public boolean isDead;

    @Override
    public void reset() {
        startX = 0;
        startY = 0;
        xVel = 0;
        yVel = 0;
        maxLenght = 0;
        damage = 0;
        isDead = false;

    }
}
