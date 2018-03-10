package com.mygdx.game.Items.EquiableItems.Weapons;

import com.mygdx.game.Items.EquiableItems.EquiableItem;

public class Sword extends Weapon {

    public Sword() {
        this("Sword",3f,false,Weapon.class,3,6,50,1,10);
    }

    public Sword(String name, float weight, boolean stackable, Class<? extends EquiableItem> classType, int minDamage, int maxDamage, int accuracy, int speed, int critChance) {
        super(name, weight, stackable, classType, minDamage, maxDamage, accuracy, speed, critChance);
    }
}
