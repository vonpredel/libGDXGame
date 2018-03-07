package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Assets;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;

public class Player extends Character {

    public Player(Assets assets) {
        this.texture = assets.manager.get("badlogic.jpg", Texture.class);
        this.width = 64;
        this.height = 64;
        this.movementSpeed = Constants.DEFAULT_MOVEMENT_SPEED;
        this.x = 512;
        this.y = 512;
    }
}
