package com.mygdx.game.Items.EquiableItems.Weapons;

import com.mygdx.game.Items.EquiableItems.EquiableItem;

public abstract class Weapon extends EquiableItem {

    protected int minDamage;
    protected int maxDamage;
    protected int accuracy;
    protected int speed;
    protected int critChance;

    public Weapon(String name, float weight, boolean stackable, Class<? extends EquiableItem> classType, int minDamage, int maxDamage, int accuracy, int speed, int critChance) {
        super(name, weight, stackable, classType);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.accuracy = accuracy;
        this.speed = speed;
        this.critChance = critChance;
    }
}
