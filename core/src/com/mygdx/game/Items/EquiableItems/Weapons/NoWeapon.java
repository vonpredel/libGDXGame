package com.mygdx.game.Items.EquiableItems.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Items.EquiableItems.EquiableItem;

public class NoWeapon extends Weapon {

    public NoWeapon() {
        this("No Weapon", 0f, false,Weapon.class,1,1,50,1f,0);
        this.texture = new Texture("items/noWeapon.png");
    }

    public NoWeapon(String name, float weight, boolean stackable, Class<? extends EquiableItem> classType, int minDamage, int maxDamage, int accuracy, float speed, int critChance) {
        super(name, weight, stackable, classType, minDamage, maxDamage, accuracy, speed, critChance);
    }
}
