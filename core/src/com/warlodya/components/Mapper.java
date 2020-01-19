package com.warlodya.components;

import com.badlogic.ashley.core.ComponentMapper;

public class Mapper {
    public static final ComponentMapper<B2dBodyComponent> b2dCom = ComponentMapper.getFor(B2dBodyComponent.class);
    public static final ComponentMapper<BulletComponent> bulletCom = ComponentMapper.getFor(BulletComponent.class);
    public static final ComponentMapper<CollisionComponent> collisionCom = ComponentMapper.getFor(CollisionComponent.class);
    public static final ComponentMapper<TypeComponent> teamCom = ComponentMapper.getFor(TypeComponent.class);
    public static final ComponentMapper<PlayerComponent> playerCom = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<TextureComponent> texCom = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<ShootComponent> shootCom = ComponentMapper.getFor(ShootComponent.class);
    public static final ComponentMapper<CombatComponent> combatCom = ComponentMapper.getFor(CombatComponent.class);
    public static final ComponentMapper<TransformationComponent> transformCom = ComponentMapper.getFor(TransformationComponent.class);
}
