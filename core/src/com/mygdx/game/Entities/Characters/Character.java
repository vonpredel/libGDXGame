package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;

public abstract class Character extends Entity {

    public boolean isMovingUP, isMovingDOWN, isMovingLEFT, isMovingRIGHT;
    public boolean isMoving;
    public float timerHelper = 0;
    public float movementSpeed;

    @Override
    public boolean isSolid() {
        return true;
    }

    public void releaseBooleans() {
        timerHelper += Gdx.graphics.getDeltaTime();
        if(timerHelper > this.movementSpeed) {
            this.isMovingRIGHT = false;
            this.isMovingLEFT = false;
            this.isMovingUP = false;
            this.isMovingDOWN = false;
            this.isMoving = false;
        }
    }

    public void moveUp() {
        if(!isMoving) {
            World.getFourNearbyTiles(this);
            this.timerHelper = 0;
            this.isMoving = true;
            this.isMovingUP = true;
            this.y += Constants.DEFAULT_TILE_HEIGHT;
        }
    }

    public void moveLeft() {
        if(!isMoving) {
            this.timerHelper = 0;
            this.isMoving = true;
            this.isMovingLEFT = true;
            this.x -= Constants.DEFAULT_TILE_WIDTH;
        }
    }

    public void moveDown() {
        if(!isMoving) {
            this.timerHelper = 0;
            this.isMoving = true;
            this.isMovingDOWN = true;
            this.y -= Constants.DEFAULT_TILE_HEIGHT;
        }
    }

    public void moveRight() {
        if(!isMoving) {
            this.timerHelper = 0;
            this.isMoving = true;
            this.isMovingRIGHT = true;
            this.x += Constants.DEFAULT_TILE_WIDTH;
        }
    }

}
