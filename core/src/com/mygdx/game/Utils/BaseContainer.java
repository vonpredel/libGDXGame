package com.mygdx.game.Utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseContainer<ELEMENT extends Updateable> implements Updateable {

    private final List<ELEMENT> allItems;

    public BaseContainer() {
        this.allItems = new ArrayList<>();
    }

    public void addItem(ELEMENT element) {
        this.allItems.add(element);
    }

    public void removeItem(ELEMENT element) {
        this.allItems.remove(element);
    }

    public abstract void draw(SpriteBatch batch);

    public void update() {
        this.allItems.forEach(Updateable::update);
    }

    public List<ELEMENT> getAllItems() {
        return allItems;
    }

    public void setAllItems(List<ELEMENT> allItems) {
        this.allItems.clear();
        this.allItems.addAll(allItems);
    }
}
