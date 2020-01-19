package com.warlodya.entitySystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.warlodya.components.B2dBodyComponent;
import com.warlodya.components.CombatComponent;
import com.warlodya.components.Mapper;
import com.warlodya.components.PlayerComponent;
import com.warlodya.controllers.AbstractController;
import com.warlodya.game.GameFactory;

public class PlayerSystem extends IteratingSystem {
    private GameFactory factory;


    public PlayerSystem(GameFactory factory) {
        super(Family.all(PlayerComponent.class).get());
        this.factory = factory;
        Gdx.app.log("Systems init", "PlayerSystem");
    }

    @Override
    public void update(float deltaTime) {
        //Gdx.app.log("System update","PlayerSystem");
        super.update(deltaTime);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerComponent player = Mapper.playerCom.get(entity);
        B2dBodyComponent bodyComponent = Mapper.b2dCom.get(entity);
        AbstractController controller = player.controller;
        Body body = bodyComponent.body;
        controller.update();
        //Velocity
        float velocityX = 0;
        float velocityY = 0;

        if (controller.isLeftPressed() && !controller.isRightPressed()) {
            velocityX = -player.maxVelocity;
        } else if (!controller.isLeftPressed() && controller.isRightPressed()) {
            velocityX = player.maxVelocity;
        } else {
            //TODO
        }

        if (controller.isUpPressed() && !controller.isDownPressed()) {
            velocityY = player.maxVelocity;
        } else if (!controller.isUpPressed() && controller.isDownPressed()) {
            velocityY = -player.maxVelocity;
        } else {
            //TODO
        }
        body.setLinearVelocity(MathUtils.lerp(body.getLinearVelocity().x, velocityX, player.acceleration), MathUtils.lerp(body.getLinearVelocity().y, velocityY, player.acceleration));

        // Angle
        float nextAngle = body.getAngle() + body.getAngularVelocity() / 60.0f;
        float totalRotation = controller.getAngle() - nextAngle;
        while (totalRotation < -180 * MathUtils.degRad) totalRotation += 360 * MathUtils.degRad;
        while (totalRotation > 180 * MathUtils.degRad) totalRotation -= 360 * MathUtils.degRad;
        float desiredAngularVelocity = totalRotation * 60;
        //float change = 1 * MathUtils.degRad; //allow 1 degree rotation per time step
        //desiredAngularVelocity = Math.min( change, Math.max(-change, desiredAngularVelocity));
        float impulse = body.getInertia() * desiredAngularVelocity;
        body.applyAngularImpulse(impulse, true);
        //shooting
        CombatComponent combat = Mapper.combatCom.get(entity);
        if (combat != null) {
            if (combat.timeSinceLastShot > 0) {
                combat.timeSinceLastShot -= deltaTime;
            }

            if (controller.isShootPressed()) { // if mouse button is pressed
                //System.out.println(player.timeSinceLastShot+" ls:sd "+player.shootDelay);
                // user wants to fire
                if (combat.timeSinceLastShot <= 0) { // check the player hasn't just shot
                    //player can shoot so do player shoot
                    float speed = combat.bulletSpeed;  // set the speed of the bullet
                    float velx = MathUtils.cos(body.getAngle());
                    float vely = MathUtils.sin(body.getAngle()); // get distance from shooter to target on y plain
                    float length = (float) Math.sqrt(velx * velx + vely * vely); // get distance to target direct
                    if (length != 0) {
                        velx = velx / length;  // get required x velocity to aim at target
                        vely = vely / length;  // get required y velocity to aim at target
                    }
                    // create a bullet
                    factory.createBullet(body.getPosition().x, body.getPosition().y, velx * speed, vely * speed, 0, player.bulletType);
                    //reset timeSinceLastShot
                    combat.timeSinceLastShot = combat.shootDelay;
                }
            }
        }


    }


}
