package com.mygdx.game.Items.types;

import com.mygdx.game.Entities.NonStatics.Characters.Player;
import com.mygdx.game.Items.Item;
import com.mygdx.game.World.World;

public class UsableItem extends Item {

    private static final int POTION_OF_HEALTH_MINOR = 0;
    private static final int POTION_OF_HEALTH_NORMAL = 1;
    private static final int POTION_OF_HEALTH_MAJOR = 2;

    public int function;

    public UsableItem(String name, float weight, boolean stackable,int function) {
        super(name, weight, stackable);
        this.function = function;
    }
}
