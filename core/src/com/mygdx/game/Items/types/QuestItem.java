package com.mygdx.game.Items.types;

import com.mygdx.game.Items.Item;

public class QuestItem extends Item {

    public int function;

    public QuestItem(String name, boolean stackable, int function) {
        super(name, stackable);
        this.function = function;
    }
}
