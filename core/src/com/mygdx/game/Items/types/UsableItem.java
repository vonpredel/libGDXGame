package com.mygdx.game.Items.types;

import com.mygdx.game.Items.Item;

public class UsableItem extends Item {

    public int function;

    public UsableItem(String name, boolean stackable,int function) {
        super(name, stackable);
        this.function = function;
    }
}
