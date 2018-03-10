package com.mygdx.game.Items.EquiableItems.Weapons;

import com.mygdx.game.Items.EquiableItems.EquiableItem;

public abstract class Weapon extends EquiableItem {

    protected int minDamage;
    protected int maxDamage;
    protected int accuracy;
    protected float speed;
    protected int critChance;

    public Weapon(String name, float weight, boolean stackable, Class<? extends EquiableItem> classType, int minDamage, int maxDamage, int accuracy, float speed, int critChance) {
        super(name, weight, stackable, classType);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.accuracy = accuracy;
        this.speed = speed;
        this.critChance = critChance;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }
}
