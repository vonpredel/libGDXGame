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
            if(e instanceof Character) ((Character) e).releaseBooleans();
        });
    }

    public void checkCollisions(Character character, Entity entity) {
        if(entity.isSolid()) {
            if(character.isMovingUP && character.overlaps(entity)) {
                character.y -= character.movementSpeed * Gdx.graphics.getDeltaTime();
//                character.y = entity.y - entity.height;
            }
            if(character.isMovingDOWN && character.overlaps(entity)) {
                character.y += character.movementSpeed * Gdx.graphics.getDeltaTime();
//                character.y = entity.y + entity.height;
            }
            if(character.isMovingRIGHT && character.overlaps(entity)) {
                if(character.isMovingUP) {
                    character.y += character.movementSpeed * Gdx.graphics.getDeltaTime();
//                    character.y = entity.y - entity.height;
                } if (character.isMovingDOWN) {
                    character.y -= character.movementSpeed * Gdx.graphics.getDeltaTime();
//                    character.y = entity.y + entity.height;
                }
                character.x -= character.movementSpeed * Gdx.graphics.getDeltaTime();
//                character.x = entity.x - entity.width;
            }
            if(character.isMovingLEFT && character.overlaps(entity)) {
                if(character.isMovingUP) {
                    character.y += character.movementSpeed * Gdx.graphics.getDeltaTime();
//                    character.y = entity.y - entity.height;
                } if(character.isMovingDOWN) {
                    character.y -= character.movementSpeed * Gdx.graphics.getDeltaTime();
//                    character.y = entity.y + entity.height;
                }
                character.x += character.movementSpeed * Gdx.graphics.getDeltaTime();
//                character.x = entity.x + entity.width;
            }
        }
    }

}
