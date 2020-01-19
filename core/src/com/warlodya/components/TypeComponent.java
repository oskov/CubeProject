package com.warlodya.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TypeComponent implements Component, Poolable {

    public int teamId;
    public Type type;

    @Override
    public void reset() {
        teamId = 0;
        type = Type.Empty;
    }

    public enum Type {
        Player, Bullet, Map, Empty
    }
}
