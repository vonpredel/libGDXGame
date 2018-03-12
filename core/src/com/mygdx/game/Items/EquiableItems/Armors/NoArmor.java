package com.mygdx.game.Items.EquiableItems.Armors;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Items.EquiableItems.EquiableItem;

public class NoArmor extends Armor {

    public NoArmor() {
        this("No Armor",0f,false,0,0,0);
        this.texture = new Texture("items/noArmor.png");
    }

    public NoArmor(String name, float weight, boolean stackable, int armorClass, int defence, int movementSpeedReduction) {
        super(name, weight, stackable, armorClass, defence, movementSpeedReduction);
    }
}
