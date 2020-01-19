package com.warlodya.entitySystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.warlodya.components.AnimationComponent;
import com.warlodya.components.TextureComponent;

public class AnimationSystem extends IteratingSystem {
    private ComponentMapper<TextureComponent> tm;
    private ComponentMapper<AnimationComponent> am;

    public AnimationSystem() {
        super(Family.all(TextureComponent.class, AnimationComponent.class).get());
        tm = ComponentMapper.getFor(TextureComponent.class);
        am = ComponentMapper.getFor(AnimationComponent.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
		/*AnimationComponent ani = am.get(entity);

		if (ani.animations.containsKey(currentState.getState().num)) {
			TextureComponent tex = tm.get(entity);
			// Test
			Animation animation = ani.animations.get(currentState.getState().num);
			if (!currentState.isLooping() && currentState.getTime() > animation.getAnimationDuration()) {
				Gdx.app.log("ANIMATION", "SToP PLAY");
				currentState.returnState();
				animation = ani.animations.get(currentState.getState().num);
			}

			//
			tex.texture = (TextureRegion) animation.getKeyFrame(currentState.getTime(), currentState.isLooping());
			if (currentState.isLookLeft() != tex.texture.isFlipX()) {
				tex.texture.flip(true, false);
			}
		}
		currentState.addTime(deltaTime);
		*/
    }

}
