package com.warlodya.entitySystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.warlodya.com.warlodya.util.Const;
import com.warlodya.components.B2dBodyComponent;
import com.warlodya.components.Mapper;
import com.warlodya.components.TransformationComponent;

public class PhysicsSystem extends IteratingSystem {
    private float accumulator = 0;
    private World world;
    private Engine engine;
    private Array<Entity> bodiesQueue;

    public PhysicsSystem(World world, Engine engine) {
        super(Family.all(B2dBodyComponent.class).get());
        this.world = world;
        this.engine = engine;
        this.bodiesQueue = new Array<Entity>();
        Gdx.app.log("Systems init", "PhysicsSystem");
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        if (accumulator >= Const.WORLD_TIME_STEP) {
            world.step(Const.WORLD_TIME_STEP, Const.WORLD_VELOCITY_ITERATIONS, Const.WORLD_POSITION_ITERATIONS);
            accumulator -= Const.WORLD_TIME_STEP;
            for (Entity entity : bodiesQueue) {
                B2dBodyComponent bodyComp = Mapper.b2dCom.get(entity);
                Vector2 position = bodyComp.body.getPosition();
                TransformationComponent transformationComponent = Mapper.transformCom.get(entity);

                Fixture fixture = bodyComp.body.getFixtureList().peek();
                transformationComponent.radius = fixture.getShape().getRadius();

                transformationComponent.position = position;
                if (bodyComp.isDead) {
                    world.destroyBody(bodyComp.body);
                    getEngine().removeEntity(entity);
                }

            }
        }
        bodiesQueue.clear();
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bodiesQueue.add(entity);
    }
}
