package com.warlodya.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class CombatComponent implements Component, Pool.Poolable {
    public float shootDelay = 0.25f;
    public float timeSinceLastShot = 0.0f;

    public float bulletSize;
    public float bulletSpeed;
    public float bulletDistance;
    public int bulletDamage;

    public int maxHealthPoints;
    public int currentHealthPoints;
    public int damageReduction;

    public void setBulletProperties(BulletType type) {
        bulletDamage = type.getDamage();
        bulletDistance = type.getDistance();
        bulletSize = type.getSize();
        bulletSpeed = type.getSpeed();
    }

    @Override
    public void reset() {
        shootDelay = 0;
        timeSinceLastShot = 0;
        bulletSize = 0;
        bulletSpeed = 0;
        bulletDistance = 0;
        bulletDamage = 0;
        shootDelay = 0;

        maxHealthPoints = 0;
        currentHealthPoints = 0;
        damageReduction = 0;
    }
}
