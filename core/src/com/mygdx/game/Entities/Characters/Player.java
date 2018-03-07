package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Assets;

public class Player extends Character {

    public Player(Assets assets) {
        this.texture = assets.manager.get("badlogic.jpg", Texture.class);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    @Override
    public void moveUp() {
        this.isMovingUP = true;
        this.y += 500 * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void moveLeft() {
        this.isMovingLEFT = true;
        this.x -= 500 * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void moveDown() {
        this.isMovingDOWN = true;
        this.y -= 500 * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void moveRight() {
        this.isMovingRIGHT = true;
        this.x += 500 * Gdx.graphics.getDeltaTime();
    }
}
