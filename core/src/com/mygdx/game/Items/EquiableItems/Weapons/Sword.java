package com.mygdx.game.Items.EquiableItems.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Items.EquiableItems.EquiableItem;

public class Sword extends Weapon {

    public Sword() {
        this("Sword",3f,false,3,6,80,1f,25);
        this.texture = new Texture("items/sword.png");
    }

    public Sword(String name, float weight, boolean stackable, int minDamage, int maxDamage, int accuracy, float speed, int critChance) {
        super(name, weight, stackable, minDamage, maxDamage, accuracy, speed, critChance);
    }
}
