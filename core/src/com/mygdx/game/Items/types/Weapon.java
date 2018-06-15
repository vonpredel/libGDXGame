package com.mygdx.game.Items.types;

import com.mygdx.game.Items.Item;
import java.util.ArrayList;
import java.util.List;

public class Weapon extends Item {
    private int minDamage;

    private int maxDamage;
    private int accuracy;
    private float speed;
    private int critChance;
    private int range;
    private boolean piercing;
    private Type type;

    public Weapon(String name, int price, int minDamage, int maxDamage, int accuracy, float speed, int critChance, Type type, int range, boolean piercing) {
        super(name, price);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.accuracy = accuracy;
        this.speed = speed;
        this.critChance = critChance;
        this.range = range;
        this.piercing = piercing;
        this.type = type;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>(5);
        description.add(name);
        description.add("Damage : " + minDamage + " - " + maxDamage);
        description.add("Accuracy : " + accuracy + " | " + "Speed : " + speed);
        description.add("Critical Chance : " + critChance + "%");
        description.add("Range : " + range + " | " + "Piercing : " + piercing);
        return description;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isPiercing() {
        return piercing;
    }

    public void setPiercing(boolean piercing) {
        this.piercing = piercing;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        ONE_HANDED, TWO_HANDED, RANGED
    }
}
