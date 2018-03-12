package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Characters.Player;

public abstract class AbstractGUI {

    protected Texture texture;

    protected float x,y;
    protected int width,height;
    public boolean isEnabled;

    protected Player player;

    public void draw(SpriteBatch batch, BitmapFont font) {
        if(isEnabled) {
            batch.draw(texture, x, y, width, height);
        }
    }

    public void update() {
        this.x = player.x - width / 2 + player.width / 2;
        this.y = player.y - height / 2 + player.height / 2;
    }

}
