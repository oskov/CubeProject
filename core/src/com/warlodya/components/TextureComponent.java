package com.warlodya.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TextureComponent implements Component, Poolable {
    public TextureRegion texture;

    public TextureComponent(TextureRegion texture) {
        this.texture = texture;
    }

    public TextureComponent() {

    }

    @Override
    public void reset() {
        texture = null;
    }
}
