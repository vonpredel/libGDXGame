package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.Utils.Updateable;
import com.mygdx.game.inventory.Inventory;
import java.util.List;

public abstract class Item implements Updateable {

    protected String name;
    protected int price;
    protected Texture texture;

    protected int width, height;
    public float x,y;
    protected boolean isDropped = false;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
        this.width = Constants.DEFAULT_TILE_WIDTH;
        this.height = Constants.DEFAULT_TILE_HEIGHT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void update() {

    }

    public abstract List<String> getDescription();

    public void generateOnMap(float x, float y) {
        isDropped = true;
        this.x = x;
        this.y = y;
    }

    public void moveToInventory(Inventory inventory) {
        inventory.getItems().add(this);
        isDropped = false;
    }

    public String getTypeName() {
        return name.toUpperCase().replace(" ","_");
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
