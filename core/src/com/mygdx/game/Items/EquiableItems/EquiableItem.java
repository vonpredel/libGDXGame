package com.mygdx.game.Items.EquiableItems;

import com.mygdx.game.Items.Item;

public abstract class EquiableItem extends Item {

    protected Class<? extends EquiableItem> classType;

    public EquiableItem(String name, float weight, boolean stackable,Class<? extends EquiableItem> classType) {
        super(name, weight, stackable);
        this.classType = classType;
    }
}
