package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;

public abstract class Entity extends Rectangle {

    private Texture texture;

    public Entity() {
        this.x = Constants.DEFAULT_START_POSITION_X;
        this.y = Constants.DEFAULT_START_POSITION_Y;
        this.height = Constants.DEFAULT_CHARACTER_HEIGHT;
        this.width = Constants.DEFAULT_CHARACTER_WIDTH;
    }

    public void draw(SpriteBatch batch, BitmapFont font) {
        batch.draw(texture, x, y, width, height);
    }

    protected void die() {
        World.removeEntity(this);
    }

    public void createItem(Item item) {
        Tile tile = getCurrentTile();
        item.generateOnMap(tile.x,tile.y);
        item.setDropped(true);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public abstract boolean isSolid();

    public Tile getCurrentTile() {
        return World.getTileByPosition(World.getCurrentEntityPosition(this));
    }

    public abstract void update();
}
