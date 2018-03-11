package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Constants;

public abstract class Item {

    protected String name;
    protected float weight;
    protected boolean stackable;
    protected Texture texture;

    protected int width, height;
    public float x,y;
    protected boolean isDropped = false;

    public Item(String name, float weight, boolean stackable) {
        this.name = name;
        this.weight = weight;
        this.stackable = stackable;
        this.width = Constants.DEFAULT_TILE_WIDTH;
        this.height = Constants.DEFAULT_TILE_HEIGHT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public void update() {

    }

    public void generateOnMap(float x, float y) {
        isDropped = true;
        this.x = x;
        this.y = y;
    }

    public void moveToInventory() {
        isDropped = false;
    }

    public boolean isDropped() {
        return isDropped;
    }

    public void setDropped(boolean dropped) {
        isDropped = dropped;
    }

    public Texture getTexture() {
        return texture;
    }
}
