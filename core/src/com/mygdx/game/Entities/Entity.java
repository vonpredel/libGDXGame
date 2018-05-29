package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.Utils.Updateable;
import com.mygdx.game.World.World;

public abstract class Entity extends Rectangle implements Updateable {

    private String name;
    private Texture texture;

    public Entity() {
        this.height = Constants.DEFAULT_CHARACTER_HEIGHT;
        this.width = Constants.DEFAULT_CHARACTER_WIDTH;
    }

    public void draw(SpriteBatch batch, BitmapFont font) {
        batch.draw(texture, x, y, width, height);
    }

    protected void die() {
        World.getEntitiesContainer().removeItem(this);
    }

    public void warp(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    // TODO
    public abstract boolean isSolid();

    public Tile getCurrentTile() {
        return World.getTileByPosition(World.getCurrentEntityPosition(this));
    }

    public abstract void update();

    public String getName() {
        return name;
    }

    // TODO TEST
    public String getTypeName() {
        return name.toUpperCase().replace(" ","_");
    }

    public void setName(String name) {
        this.name = name;
    }
}
