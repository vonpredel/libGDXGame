package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Assets;
import com.mygdx.game.Utils.Constants;

public class Player extends Character {

    public Player(Assets assets) {
        this.texture = assets.manager.get("badlogic.jpg", Texture.class);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.movementSpeed = Constants.DEFAULT_MOVEMENT_SPEED;
        this.x = 700;
        this.y = 400;
    }
}
