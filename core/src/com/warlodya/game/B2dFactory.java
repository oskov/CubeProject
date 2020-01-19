package com.warlodya.game;

import com.badlogic.gdx.physics.box2d.*;
import com.warlodya.com.warlodya.util.Materials;

public class B2dFactory {
    //private FixtureDef fixtureDef;
    //private BodyDef bodyDef;
    private World world;

    public B2dFactory(World world) {
        this.world = world;
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
    }

    public Body createFloor() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(-1, -1);
        bodyDef.fixedRotation = true;

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(200, 1);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public Body createCircleBody(float x, float y, float radius, Materials material, BodyDef.BodyType bodyType, boolean fixedRotation) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(x, y);
        bodyDef.fixedRotation = fixedRotation;

        Body body = world.createBody(bodyDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(radius);
        body.createFixture(createFixtureDefinition(material, circle));
        circle.dispose();
        return body;
    }

    private FixtureDef createFixtureDefinition(Materials material, Shape shape) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        switch (material) {
            case Stone:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.9f;
                fixtureDef.restitution = 0.01f;
                break;
            default:

        }
        return fixtureDef;
    }

    @Deprecated
    private void resetFixtureDefinition() {
        /*fixtureDef.density = 0;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 0;
        fixtureDef.shape.dispose();*/
    }

    @Deprecated
    private void resetBodyDefinition() {
        /*bodyDef.BulletType = BodyDef.BodyType.StaticBody;
        bodyDef.active = true;
        bodyDef.position.x = 0;
        bodyDef.position.y = 0;
        bodyDef.angle = 0;
        bodyDef.angularDamping = 0;
        bodyDef.angularVelocity = 0;
        bodyDef.allowSleep = true;
        bodyDef.linearDamping=0;
        bodyDef.linearVelocity.x = 0;
        bodyDef.linearVelocity.y = 0;*/
    }
}
