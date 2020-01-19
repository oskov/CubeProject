package com.warlodya.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.warlodya.controllers.AbstractController;

public class PlayerComponent implements Component, Poolable {
    public AbstractController controller;
    public float maxVelocity = 10;
    public float acceleration = 0.5f;

    public BulletType bulletType;

    @Override
    public void reset() {
        controller = null;
        maxVelocity = 0;
        acceleration = 1;
        BulletType bulletType = BulletType.EmptyBullet;
    }
}
