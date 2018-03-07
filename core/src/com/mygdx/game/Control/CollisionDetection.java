package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.Characters.Character;
import com.mygdx.game.Entities.Entity;
import java.util.List;

public class CollisionDetection {

    public void update(List<Entity> list) {
        list.forEach(e -> {
            if(e instanceof Character) list.forEach(t -> {
                if (!e.equals(t)) checkCollisions((Character) e, t);
            });
        });
    }

    public void checkCollisions(Character character, Entity entity) {
        if(entity.isSolid()) {
            if(character.isMovingUP && character.overlaps(entity)) {
                character.y -= 500 * Gdx.graphics.getDeltaTime();
            }
            if(character.isMovingDOWN && character.overlaps(entity)) {
                character.y += 500 * Gdx.graphics.getDeltaTime();
            }
            if(character.isMovingLEFT && character.overlaps(entity)) {
                character.x += 500 * Gdx.graphics.getDeltaTime();
            }
            if(character.isMovingRIGHT && character.overlaps(entity)) {
                character.x -= 500 * Gdx.graphics.getDeltaTime();
            }
            character.releaseBooleans();
        }
    }

}
