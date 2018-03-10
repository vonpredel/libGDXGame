package com.mygdx.game.Items;

public abstract class Item {

    protected String name;
    protected float weight;
    protected boolean stackable;

    public Item(String name, float weight, boolean stackable) {
        this.name = name;
        this.weight = weight;
        this.stackable = stackable;
    }
}
