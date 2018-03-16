package com.mygdx.game.Items.types;

import com.mygdx.game.Items.Item;

public class UsableItem extends Item {

    private static final int POTION_OF_HEALTH_MINOR = 0;
    private static final int POTION_OF_HEALTH_MAJOR = 1;
    private static final int POTION_OF_STAMINA_MINOR = 2;
    private static final int POTION_OF_STAMINA_MAJOR = 3;
    private static final int POTION_OF_MANA_MINOR = 4;
    private static final int POTION_OF_MANA_MAJOR = 5;
    private static final int POTION_SOMETHING = 6;

    public int function;

    public UsableItem(String name, boolean stackable,int function) {
        super(name, stackable);
        this.function = function;
    }
}
