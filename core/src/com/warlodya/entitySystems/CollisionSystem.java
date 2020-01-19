package com.warlodya.entitySystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.warlodya.components.CollisionComponent;
import com.warlodya.components.Mapper;

public class CollisionSystem extends IteratingSystem {

    public CollisionSystem() {
        super(Family.all(CollisionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // get collision for this entity
        CollisionComponent cc = Mapper.collisionCom.get(entity);
        //get collided entity
        Entity collidedEntity = cc.collisionEntity;
        if (collidedEntity != null) {

        }
    }
}
