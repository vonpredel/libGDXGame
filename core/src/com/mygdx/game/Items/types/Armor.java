package com.mygdx.game.Items.types;

import com.mygdx.game.Items.Item;
import java.util.ArrayList;
import java.util.List;

public class Armor extends Item {
    protected Slot armorSlot;

    protected int defence;
    protected int movementSpeedReduction;

    public Armor(String name, int price, Slot armorSlot, int defence, int movementSpeedReduction) {
        super(name, price);
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
        description.add("Armor class : " + String.valueOf(armorSlot).toLowerCase());
        return description;
    }

    public Slot getArmorSlot() {
        return armorSlot;
    }

    public void setArmorSlot(Slot armorSlot) {
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

    public enum Slot {
        CHEST,SHIELD,HELMET
    }
}
