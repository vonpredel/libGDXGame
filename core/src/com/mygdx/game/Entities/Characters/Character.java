package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;

public abstract class Character extends Entity {

    protected boolean isMoving;
    protected float movementTimeHelper = 0;
    protected float movementSpeed;

    @Override
    public boolean isSolid() {
        return true;
    }

    public void releaseBooleans() {
        movementTimeHelper += Gdx.graphics.getDeltaTime();
        if(movementTimeHelper > this.movementSpeed) {
            this.isMoving = false;
        }
    }

    public void moveUp() {
        if(!isMoving && World.isAbleToGo(this,World.UP)) {
            this.movementTimeHelper = 0;
            this.isMoving = true;
            this.y += Constants.DEFAULT_TILE_HEIGHT;
        }
    }

    public void moveLeft() {
        if(!isMoving && World.isAbleToGo(this,World.LEFT)) {
            this.movementTimeHelper = 0;
            this.isMoving = true;
            this.x -= Constants.DEFAULT_TILE_WIDTH;
        }
    }

    public void moveDown() {
        if(!isMoving && World.isAbleToGo(this,World.DOWN)) {
            this.movementTimeHelper = 0;
            this.isMoving = true;
            this.y -= Constants.DEFAULT_TILE_HEIGHT;
        }
    }

    public void moveRight() {
        if(!isMoving && World.isAbleToGo(this,World.RIGHT)) {
            this.movementTimeHelper = 0;
            this.isMoving = true;
            this.x += Constants.DEFAULT_TILE_WIDTH;
        }
    }

}
