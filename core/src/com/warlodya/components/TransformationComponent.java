package com.warlodya.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TransformationComponent implements Component, Poolable {
    public Vector2 position = new Vector2(0, 0);
    public float width;
    public float height;
    public float angle;
    public float radius;

    @Override
    public void reset() {
        position = new Vector2(0, 0);
        angle = 0;
        width = 0;
        height = 0;
    }
}
