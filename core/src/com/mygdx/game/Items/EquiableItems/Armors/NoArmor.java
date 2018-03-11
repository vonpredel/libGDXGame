package com.mygdx.game.Items.EquiableItems.Armors;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Items.EquiableItems.EquiableItem;

public class NoArmor extends Armor {

    public NoArmor() {
        this("No Armor",0f,false,Armor.class,0,0,0);
        this.texture = new Texture("items/noArmor.png");
    }

    public NoArmor(String name, float weight, boolean stackable, Class<? extends EquiableItem> classType, int armorClass, int defence, int movementSpeedReduction) {
        super(name, weight, stackable, classType, armorClass, defence, movementSpeedReduction);
    }
}
