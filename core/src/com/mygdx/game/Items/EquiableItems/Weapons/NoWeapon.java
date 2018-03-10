package com.mygdx.game.Items.EquiableItems.Weapons;

import com.mygdx.game.Items.EquiableItems.EquiableItem;

public class NoWeapon extends Weapon {

    public NoWeapon() {
        this("No Weapon", 0f, false,Weapon.class,0,0,0,1,0);
    }

    public NoWeapon(String name, float weight, boolean stackable, Class<? extends EquiableItem> classType, int minDamage, int maxDamage, int accuracy, int speed, int critChance) {
        super(name, weight, stackable, classType, minDamage, maxDamage, accuracy, speed, critChance);
    }
}
