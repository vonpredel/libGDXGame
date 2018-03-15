package com.mygdx.game.Items.types;

import com.mygdx.game.Items.Item;

public class Armor extends Item {

    private static final int lightArmor = 1;
    private static final int mediumArmor = 2;
    private static final int heavyArmor = 3;

    protected int armorClass;
    protected int defence;
    protected int movementSpeedReduction;

    public Armor(String name, boolean stackable, int armorClass, int defence, int movementSpeedReduction) {
        super(name, stackable);
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

    public String getArmorClassToString() {
        switch (armorClass) {
            case 0:
                return "Light Armor";
            case 1:
                return "Medium Armor";
            case 2:
                return "Heavy Armor";
            default:
                return "ERROR";
        }
    }
}
