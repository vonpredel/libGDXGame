package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Utils.Constants;
import java.awt.Rectangle;

public abstract class Character extends Rectangle {

    protected Texture texture;

    public Character() {
        this.x = 0;
        this.y = 0;
        this.height = Constants.DEFAULT_CHARACTER_HEIGHT;
        this.width = Constants.DEFAULT_CHARACTER_WIDTH;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
