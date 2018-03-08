package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Assets;
import com.mygdx.game.Utils.Constants;

public class Foe extends Character {

    public Foe(Assets assets) {
        this.texture = assets.manager.get("badlogic.jpg", Texture.class);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.movementSpeed = Constants.DEFAULT_MOVEMENT_SPEED;
        this.attackSpeed= 1f;
        this.x = 1024;
        this.y = 1024;
        this.currentHealthPoints = 5;
        this.maxHealthPoints = 5;
        this.damage = 1;
    }

    @Override
    public void attackUp() {

    }

    @Override
    public void attackDown() {

    }

    @Override
    public void attackLeft() {

    }

    @Override
    public void attackRight() {

    }
}
