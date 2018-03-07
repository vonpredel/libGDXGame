package com.mygdx.game.Control;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entities.Characters.Character;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Constants;
import java.util.List;

public class CollisionDetection {

    private boolean isSolid = false;

    public void update(List<Entity> list, List<Tile> tiles) {
        list.forEach(e -> {
            if(e instanceof Character && ((Character) e).isMoving) {
                list.forEach(t -> {
                    if (!e.equals(t)) checkCollisions((Character) e, t);
                });
                tiles.forEach(tile -> checkCollisions((Character) e, tile));
            }
            if(e instanceof Character) ((Character) e).releaseBooleans();
        });
    }

    private void checkCollisions(Character character, Rectangle rectangle) {

        if (rectangle instanceof Entity ) {
            isSolid = ((Entity) rectangle).isSolid();
        } else if (rectangle instanceof Tile) {
            isSolid = ((Tile) rectangle).isSolid();
        }
        if(isSolid) {
            if(character.isMovingUP && character.overlaps(rectangle)) {
                character.y -= Constants.DEFAULT_TILE_HEIGHT;
                character.timerHelper = character.movementSpeed + 1;
                character.isMovingUP = false;
                character.isMoving = false;
            }
            if(character.isMovingDOWN && character.overlaps(rectangle)) {
                character.y += Constants.DEFAULT_TILE_HEIGHT;
                character.timerHelper = character.movementSpeed + 1;
                character.isMovingDOWN = false;
                character.isMoving = false;
            }
            if(character.isMovingRIGHT && character.overlaps(rectangle)) {
                character.x -= Constants.DEFAULT_TILE_WIDTH;
                character.timerHelper = character.movementSpeed + 1;
                character.isMovingRIGHT = false;
                character.isMoving = false;
            }
            if(character.isMovingLEFT && character.overlaps(rectangle)) {
                character.x += Constants.DEFAULT_TILE_WIDTH;
                character.timerHelper = character.movementSpeed + 1;
                character.isMovingLEFT = false;
                character.isMoving = false;
            }
        }
    }
}
