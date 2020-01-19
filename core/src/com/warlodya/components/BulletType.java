package com.warlodya.components;

public enum BulletType {
    EmptyBullet(0, 0, 0, 0, 0),
    FastBullet(0.1f, 50, 400, 3, 0.1f);

    private float size;
    private float speed;
    private float distance;
    private int damage;
    private float shotDelay;


    BulletType(float size, float speed, float distance, int damage, float shotDelay) {
        this.size = size;
        this.speed = speed;
        this.distance = distance;
        this.damage = damage;
        this.shotDelay = shotDelay;
    }

    public float getSize() {
        return size;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDistance() {
        return distance;
    }

    public int getDamage() {
        return damage;
    }

    public float getShotDelay() {
        return shotDelay;
    }
}
