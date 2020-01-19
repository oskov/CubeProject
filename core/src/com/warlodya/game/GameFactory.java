package com.warlodya.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.warlodya.com.warlodya.util.Const;
import com.warlodya.com.warlodya.util.Materials;
import com.warlodya.components.*;
import com.warlodya.controllers.AbstractController;
import com.warlodya.controllers.KeyboardController;

public class GameFactory {

    private PooledEngine engine;
    private World world;
    private GameWorld game;
    private B2dFactory factory;

    public GameFactory(PooledEngine engine, World world, GameWorld game) {
        this.engine = engine;
        this.world = world;
        this.game = game;
        this.factory = new B2dFactory(world);
    }

    public void createFloor() {
        factory.createFloor();
    }

    public void createBullet(float x, float y, float xVel, float yVel, int teamId, BulletType type) {
        Entity entity = engine.createEntity();
        TypeComponent typeComponent = engine.createComponent(TypeComponent.class);
        BulletComponent bulletComponent = engine.createComponent(BulletComponent.class);
        B2dBodyComponent bodyComponent = engine.createComponent(B2dBodyComponent.class);
        CollisionComponent collisionComponent = engine.createComponent(CollisionComponent.class);
        TransformationComponent transformationComponent = engine.createComponent(TransformationComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        bodyComponent.body = factory.createCircleBody(x, y, type.getSize(), Materials.Stone, BodyDef.BodyType.DynamicBody, true);
        bodyComponent.body.setBullet(true);

        for (Fixture fix : bodyComponent.body.getFixtureList()) {
            fix.setSensor(true);
        }

        typeComponent.type = TypeComponent.Type.Bullet;
        typeComponent.teamId = teamId;

        bulletComponent.xVel = xVel;
        bulletComponent.yVel = yVel;
        bulletComponent.startX = x;
        bulletComponent.startY = y;
        bulletComponent.maxLenght = type.getDistance();

        entity.add(typeComponent);
        entity.add(bulletComponent);
        entity.add(bodyComponent);
        entity.add(collisionComponent);
        entity.add(transformationComponent);
        entity.add(textureComponent);

        engine.addEntity(entity);
    }

    public void createEnemyPlayer() {
        Entity ent = engine.createEntity();
        PlayerComponent pc = engine.createComponent(PlayerComponent.class);
        TypeComponent tm = engine.createComponent(TypeComponent.class);
        B2dBodyComponent b2body = engine.createComponent(B2dBodyComponent.class);
        TransformationComponent transformationComponent = engine.createComponent(TransformationComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        Body body = factory.createCircleBody(10, 10, Const.PLAYER_SIZE, Materials.Stone, BodyDef.BodyType.DynamicBody, false);
        body.setSleepingAllowed(false);
        b2body.body = body;

        pc.controller = new AbstractController() {
            @Override
            public void update() {

            }
        };
        tm.teamId = 0;
        tm.type = TypeComponent.Type.Player;
        ent.add(b2body);
        ent.add(pc);
        ent.add(tm);
        ent.add(transformationComponent);
        ent.add(textureComponent);

        engine.addEntity(ent);
    }

    public void createPlayer() {
        Entity ent = engine.createEntity();
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        TypeComponent typeComponent = engine.createComponent(TypeComponent.class);
        B2dBodyComponent b2body = engine.createComponent(B2dBodyComponent.class);
        CombatComponent combatComponent = engine.createComponent(CombatComponent.class);
        TransformationComponent transformationComponent = engine.createComponent(TransformationComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        playerComponent.bulletType = BulletType.FastBullet;
        combatComponent.setBulletProperties(playerComponent.bulletType);

        Body body = factory.createCircleBody(0, 0, Const.PLAYER_SIZE, Materials.Stone, BodyDef.BodyType.DynamicBody, false);
        b2body.body = body;

        playerComponent.controller = new KeyboardController(body, game.getCamera());
        typeComponent.teamId = 1;
        typeComponent.type = TypeComponent.Type.Player;
        ent.add(b2body);
        ent.add(playerComponent);
        ent.add(typeComponent);
        ent.add(transformationComponent);
        ent.add(combatComponent);
        ent.add(textureComponent);

        engine.addEntity(ent);
    }

}
