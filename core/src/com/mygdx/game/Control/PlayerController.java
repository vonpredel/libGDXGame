package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Entities.Characters.Player;

public class PlayerController {

    Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.y += 500 * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.y -= 500 * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.x -= 500 * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.x += 500 * Gdx.graphics.getDeltaTime();
        }
    }
}
