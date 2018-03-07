package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Assets;

public class Foe extends Character {

    public Foe(Assets assets) {
        this.texture = assets.manager.get("badlogic.jpg", Texture.class);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.x = 400;
        this.y = 400;
    }

//    public boolean isSolid() {
//        return true;
//    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveRight() {

    }
}
