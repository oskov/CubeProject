package com.warlodya.entitySystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsDebugSystem extends IteratingSystem {
    private Box2DDebugRenderer debugRenderer;
    private World world;
    private OrthographicCamera camera;

    public PhysicsDebugSystem(World world, OrthographicCamera camera) {
        super(Family.all().get());
        debugRenderer = new Box2DDebugRenderer();
        this.world = world;
        this.camera = camera;
        Gdx.app.log("Systems init", "PsyhicsDebugSystem");
    }

    @Override
    public void update(float deltaTime) {
        //Gdx.app.log("System update","PhysicsDebugSystem");
        super.update(deltaTime);
        debugRenderer.render(world, camera.combined);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
