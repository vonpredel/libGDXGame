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
}
