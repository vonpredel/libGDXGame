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
        this.currentHealthPoints = 100;
        this.maxHealthPoints = 100;
        this.damage = 1;
    }
}
