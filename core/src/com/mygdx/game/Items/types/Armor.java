package com.mygdx.game.Items.types;

import com.mygdx.game.Items.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Armor extends Item {
    protected int armorSlot;

    protected int defence;
    protected int movementSpeedReduction;

    public Armor(String name, boolean stackable, int armorSlot, int defence, int movementSpeedReduction) {
        super(name, stackable);
        this.armorSlot = armorSlot;
        this.defence = defence;
        this.movementSpeedReduction = movementSpeedReduction;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add(name);
        description.add("Defence : " + defence);
        description.add("Movement speed reduction : " + movementSpeedReduction);
        description.add("Armor class : " + getArmorClassToString());
        return description;
    }

    public int getArmorSlot() {
        return armorSlot;
    }

    public void setArmorSlot(int armorSlot) {
        this.armorSlot = armorSlot;
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
        switch (armorSlot) {
            case 0:
                return "Chest";
            case 1:
                return "Shield";
            case 2:
                return "Helmet";
            default:
                return "ERROR";
        }
    }
}
