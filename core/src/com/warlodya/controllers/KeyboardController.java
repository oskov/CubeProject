package com.warlodya.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.warlodya.com.warlodya.util.Const;

public class KeyboardController extends AbstractController {
    private final Vector2 mouseInWorld2D = new Vector2();
    private final Vector3 mouseInWorld3D = new Vector3();
    private Body body;
    private OrthographicCamera camera;

    public KeyboardController(Body body, OrthographicCamera camera) {
        this.body = body;
        this.camera = camera;
    }

    public void update() {
        this.leftPressed = Gdx.input.isKeyPressed(Input.Keys.A);
        this.upPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        this.rightPressed = Gdx.input.isKeyPressed(Input.Keys.D);
        this.downPressed = Gdx.input.isKeyPressed(Input.Keys.S);
        this.shootPressed = Gdx.input.isKeyPressed(Input.Keys.SPACE);

        mouseInWorld3D.x = Gdx.input.getX();
        mouseInWorld3D.y = Gdx.input.getY();
        mouseInWorld3D.z = 0;
        camera.unproject(mouseInWorld3D);
        mouseInWorld2D.x = mouseInWorld3D.x - body.getPosition().x * Const.METERS_TO_PIXELS;
        mouseInWorld2D.y = mouseInWorld3D.y - body.getPosition().y * Const.METERS_TO_PIXELS;
        angle = MathUtils.atan2(mouseInWorld2D.y, mouseInWorld2D.x);


    }
}
