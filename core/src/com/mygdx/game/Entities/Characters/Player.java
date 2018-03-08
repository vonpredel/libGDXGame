package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Assets;
import com.mygdx.game.Utils.Constants;

public class Player extends Character {

    public Player(Assets assets) {
        this.texture = assets.manager.get("badlogic.jpg", Texture.class);
        this.width = Constants.DEFAULT_CHARACTER_WIDTH;
        this.height = Constants.DEFAULT_CHARACTER_HEIGHT;
        this.movementSpeed = Constants.DEFAULT_MOVEMENT_SPEED;
        this.attackSpeed= 1f;
        this.x = 512;
        this.y = 512;
        this.currentHealthPoints = 5;
        this.maxHealthPoints = 5;
        this.damage = 1;
    }
}
