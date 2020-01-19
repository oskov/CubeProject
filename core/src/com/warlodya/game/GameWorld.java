package com.warlodya.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.warlodya.entitySystems.BulletSystem;
import com.warlodya.entitySystems.PhysicsSystem;
import com.warlodya.entitySystems.PlayerSystem;
import com.warlodya.entitySystems.RenderingSystem;

public class GameWorld {
    private World world;
    private PooledEngine engine;
    private GameFactory factory;
    private OrthographicCamera camera;

    public GameWorld() {
        world = new World(new Vector2(0, -10), true);
        world.setContactListener(new B2dContactListener());
        engine = new PooledEngine();
        factory = new GameFactory(engine, world, this);

        SpriteBatch batch = new SpriteBatch();
        camera = new OrthographicCamera(1600, 900);

        engine.addSystem(new PhysicsSystem(world, engine));
        engine.addSystem(new PlayerSystem(factory));
        engine.addSystem(new BulletSystem());
        engine.addSystem(new RenderingSystem(batch, camera, this));
        //engine.addSystem(new PhysicsDebugSystem(world, camera));
        factory.createPlayer();
        factory.createEnemyPlayer();
        factory.createFloor();
    }

    private void init() {

    }

    public void update(float delta) {
        engine.update(delta);
        //System.out.println("update");

    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
