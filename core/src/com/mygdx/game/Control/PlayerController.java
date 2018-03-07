package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Entities.Characters.Player;
import com.mygdx.game.Entities.Entity;

public class PlayerController {

    Player player;
    Entity entity;

    public PlayerController(Player player, Entity entity) {
        this.player = player;
        this.entity = entity;
    }

    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight();
        }
    }
}
