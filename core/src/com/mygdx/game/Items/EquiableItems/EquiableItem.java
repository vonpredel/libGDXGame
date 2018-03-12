package com.mygdx.game.Items.EquiableItems;

import com.mygdx.game.Items.Item;

public abstract class EquiableItem extends Item {

    public EquiableItem(String name, float weight, boolean stackable) {
        super(name, weight, stackable);
    }
}
