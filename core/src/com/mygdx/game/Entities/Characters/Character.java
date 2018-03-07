package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.Entity;

public abstract class Character extends Entity {

    public boolean isMovingUP, isMovingDOWN, isMovingLEFT, isMovingRIGHT;
    public int movementSpeed;

    @Override
    public boolean isSolid() {
        return true;
    }

    public void releaseBooleans() {
        this.isMovingRIGHT = false;
        this.isMovingLEFT = false;
        this.isMovingUP = false;
        this.isMovingDOWN = false;
    }

    public void moveUp() {
        this.isMovingUP = true;
        this.y += movementSpeed * Gdx.graphics.getDeltaTime();
    }

    public void moveLeft() {
        this.isMovingLEFT = true;
        this.x -= movementSpeed * Gdx.graphics.getDeltaTime();
    }

    public void moveDown() {
        this.isMovingDOWN = true;
        this.y -= movementSpeed * Gdx.graphics.getDeltaTime();
    }

    public void moveRight() {
        this.isMovingRIGHT = true;
        this.x += movementSpeed * Gdx.graphics.getDeltaTime();
    }

}
