package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.Utils.Updateable;
import com.mygdx.game.inventory.Inventory;

public abstract class Item implements Updateable {

    protected String name;
    protected boolean stackable;
    protected Texture texture;

    protected int width, height;
    public float x,y;
    protected boolean isDropped = false;

    public Item(String name, boolean stackable) {
        this.name = name;
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

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    @Override
    public void update() {

    }

    public void generateOnMap(float x, float y) {
        isDropped = true;
        this.x = x;
        this.y = y;
    }

    public void moveToInventory(Inventory inventory) {
        inventory.getItems().add(this);
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
