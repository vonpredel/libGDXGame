package com.mygdx.game.Utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Items.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemsContainer {

    List<Item> allItems;

    public ItemsContainer(List<Entity> entities ) {
        allItems = new ArrayList<>();
        entities.forEach(e -> e.getInventory().getItems().forEach(allItems::add));
    }

    public void update() {

    }

    public void draw(SpriteBatch batch) {
        allItems.forEach(i -> {
            if(i.isDropped()) {
                batch.draw(i.getTexture(),i.x,i.y,Constants.DEFAULT_ITEM_WIDTH, Constants.DEFAULT_ITEM_HEIGHT);
            }
        });
    }

    public List<Item> getAllItems() {
        return allItems;
    }

    public void setAllItems(List<Item> allItems) {
        this.allItems = allItems;
    }

    public void addItem(Item item) {
        allItems.add(item);
    }



}
