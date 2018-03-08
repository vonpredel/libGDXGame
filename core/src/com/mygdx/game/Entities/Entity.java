package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;

public abstract class Entity extends Rectangle {

    protected Texture texture;

    public Entity() {
        this.x = Constants.DEFAULT_START_POSITION_X;
        this.y = Constants.DEFAULT_START_POSITION_Y;
        this.height = Constants.DEFAULT_CHARACTER_HEIGHT;
        this.width = Constants.DEFAULT_CHARACTER_WIDTH;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public Texture getTexture() {
        return texture;
    }

    public abstract boolean isSolid();

    public Tile getCurrentTile() {
        return World.getTileByPosition(World.getCurrentEntityPosition(this));
    }

    public void update() {

    }
}
