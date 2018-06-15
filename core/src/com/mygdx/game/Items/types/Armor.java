package com.mygdx.game.Items.types;

import com.mygdx.game.Items.Item;
import java.util.ArrayList;
import java.util.List;

public class Armor extends Item {
    private Slot armorSlot;

    private int defence;
    private float amount;

    public Armor(String name, int price, Slot armorSlot, int defence, float amount) {
        super(name, price);
        this.armorSlot = armorSlot;
        this.defence = defence;
        this.amount = amount;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>(4);
        description.add(name);
        description.add("Defence : " + defence);
        String des = "";
        switch (armorSlot) {
            case BOOTS:
                des = "Increase movement speed : ";
                break;
            case CHEST:
                des = "Increase health points : ";
                break;
            case GLOVES:
                des = "Increase critical chance : ";
                break;
            case HELMET:
                des = "Increase accuracy : ";
                break;
        }
        description.add(des + amount);
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public enum Slot {
        CHEST,HELMET,GLOVES,BOOTS
    }
}
