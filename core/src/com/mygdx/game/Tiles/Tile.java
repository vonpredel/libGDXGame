package com.mygdx.game.Tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Utils.Constants;
import java.awt.Color;

public abstract class Tile extends Rectangle {

    protected Texture texture;
    protected Color id;

    public Tile() {
        this.x = Constants.DEFAULT_START_POSITION_X;
        this.y = Constants.DEFAULT_START_POSITION_Y;
        this.height = Constants.DEFAULT_TILE_HEIGHT;
        this.width = Constants.DEFAULT_TILE_WIDTH;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public Texture getTexture() {
        return texture;
    }

    public boolean isSolid() {
        return false;
    }

}
