package com.warlodya.entitySystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.warlodya.components.B2dBodyComponent;
import com.warlodya.components.BulletComponent;
import com.warlodya.components.Mapper;

public class BulletSystem extends IteratingSystem {

    public BulletSystem() {
        super(Family.all(BulletComponent.class, B2dBodyComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        //Gdx.app.log("System update","BulletSystem");
        super.update(deltaTime);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //get box 2d body and bullet components
        B2dBodyComponent b2body = Mapper.b2dCom.get(entity);
        BulletComponent bullet = Mapper.bulletCom.get(entity);

        // apply bullet velocity to bullet body
        b2body.body.setLinearVelocity(bullet.xVel, bullet.yVel);

        //get bullet pos
        float bx = b2body.body.getPosition().x;
        float by = b2body.body.getPosition().y;

        double lenght = Math.sqrt(Math.pow((bx - bullet.startX), 2) + Math.pow((by - bullet.startY), 2));
        // if bullet is 20 units away from player on any axis then it is probably off screen
        if (lenght > bullet.maxLenght) {
            bullet.isDead = true;
        }

        //check if bullet is dead
        if (bullet.isDead) {
            b2body.isDead = true;
        }
    }
}
