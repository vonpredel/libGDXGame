package com.mygdx.game.Items.UsableItems;

import com.mygdx.game.Items.Item;

public abstract class UsableItem extends Item {
    public UsableItem(String name, float weight, boolean stackable) {
        super(name, weight, stackable);
    }
}