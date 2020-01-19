package com.warlodya.entitySystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.warlodya.com.warlodya.util.Const;
import com.warlodya.components.Mapper;
import com.warlodya.components.TextureComponent;
import com.warlodya.components.TransformationComponent;
import com.warlodya.components.TypeComponent;
import com.warlodya.game.GameWorld;

public class RenderingSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private GameWorld gameWorld;

    private ShapeRenderer shapeRenderer;

    public RenderingSystem(SpriteBatch spriteBatch, OrthographicCamera camera, GameWorld gameWorld) {
        this.spriteBatch = spriteBatch;
        this.camera = camera;
        this.gameWorld = gameWorld;
        this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformationComponent.class, TextureComponent.class).get());
    }

    private void moveCamera(float x, float y) {
        float cameraX = 0;
        float cameraY = camera.viewportHeight / 2;

        /*if(x<camera.viewportWidth/2) {
            cameraX=camera.viewportWidth/2;

        }else if(x+camera.viewportWidth/2>worldLenght) {
            cameraX=worldLenght-camera.viewportWidth/2;
        }else cameraX=x;
        if(y<camera.viewportHeight/2) {
            cameraY=camera.viewportHeight/2;

        }else if(y+camera.viewportHeight/2>worldLenght) {
            cameraY=worldLenght-camera.viewportHeight/2;
        }else cameraY=y;*/


        //camera.position.set(game.getPlayer().getX(), game.getPlayer().getY(), 0);
        camera.position.set(x, y, 0);
        camera.update();
    }

    @Override
    public void update(float deltaTime) {
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        for (Entity entity : entities) {
            TransformationComponent transformationComponent = Mapper.transformCom.get(entity);
            TypeComponent typeComponent = Mapper.teamCom.get(entity);
            if (typeComponent != null) {
                if (typeComponent.teamId == 1 && typeComponent.type == TypeComponent.Type.Player) {
                    moveCamera(transformationComponent.position.x * Const.METERS_TO_PIXELS,
                            transformationComponent.position.y * Const.METERS_TO_PIXELS);
                }
            }
            switch (typeComponent.type) {
                case Bullet:
                    shapeRenderer.setColor(1, 0, 0, 1);
                    break;
                case Player:
                    shapeRenderer.setColor(0, 1, 0, 1);
                    break;
                case Map:
                    shapeRenderer.setColor(0, 0, 0, 0);
                    break;
                default:
                    break;
            }
            shapeRenderer.circle(transformationComponent.position.x * Const.METERS_TO_PIXELS,
                    transformationComponent.position.y * Const.METERS_TO_PIXELS,
                    transformationComponent.radius * Const.METERS_TO_PIXELS);
        }
        shapeRenderer.end();
    }


}
