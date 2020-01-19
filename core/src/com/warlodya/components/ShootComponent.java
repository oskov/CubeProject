package com.warlodya.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ShootComponent implements Component, Poolable {
    public float shootDelay = 0.5f;
    public float timeSinceLastShot = 0.0f;

    @Override
    public void reset() {
        shootDelay = 0;
        timeSinceLastShot = 0;
    }
}
