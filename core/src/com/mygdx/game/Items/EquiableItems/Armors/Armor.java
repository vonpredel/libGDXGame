package com.mygdx.game.Items.EquiableItems.Armors;

import com.mygdx.game.Items.EquiableItems.EquiableItem;

public abstract class Armor extends EquiableItem {

    private static final int lightArmor = 1;
    private static final int mediumArmor = 2;
    private static final int heavyArmor = 3;

    protected int armorClass;
    protected int defence;
    protected int movementSpeedReduction;

    public Armor(String name, float weight, boolean stackable, int armorClass, int defence, int movementSpeedReduction) {
        super(name, weight, stackable);
        this.armorClass = armorClass;
        this.defence = defence;
        this.movementSpeedReduction = movementSpeedReduction;
    }

    public static int getLightArmor() {
        return lightArmor;
    }

    public static int getMediumArmor() {
        return mediumArmor;
    }

    public static int getHeavyArmor() {
        return heavyArmor;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getMovementSpeedReduction() {
        return movementSpeedReduction;
    }

    public void setMovementSpeedReduction(int movementSpeedReduction) {
        this.movementSpeedReduction = movementSpeedReduction;
    }
}
