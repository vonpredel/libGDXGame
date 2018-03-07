package com.mygdx.game.Entities.Characters;

import com.mygdx.game.Entities.Entity;

public abstract class Character extends Entity {

    public boolean isMovingUP, isMovingDOWN, isMovingLEFT, isMovingRIGHT;

    @Override
    public boolean isSolid() {
        return true;
    }

    public abstract void moveUp();
    public abstract void moveLeft();
    public abstract void moveDown();
    public abstract void moveRight();

    public void releaseBooleans() {
        this.isMovingRIGHT = false;
        this.isMovingLEFT = false;
        this.isMovingUP = false;
        this.isMovingDOWN = false;
    }

}
