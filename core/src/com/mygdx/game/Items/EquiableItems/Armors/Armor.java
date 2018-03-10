package com.mygdx.game.Items.EquiableItems.Armors;

import com.mygdx.game.Items.EquiableItems.EquiableItem;

public abstract class Armor extends EquiableItem {

    private static final int lightArmor = 1;
    private static final int mediumArmor = 2;
    private static final int heavyArmor = 3;

    protected int armorClass;
    protected int defence;
    protected int movementSpeedReduction;

    public Armor(String name, float weight, boolean stackable, Class<? extends EquiableItem> classType, int armorClass, int defence, int movementSpeedReduction) {
        super(name, weight, stackable, classType);
        this.armorClass = armorClass;
        this.defence = defence;
        this.movementSpeedReduction = movementSpeedReduction;
    }
}
