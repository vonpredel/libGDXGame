package com.mygdx.game.Entities.NonStatics.Creatures;

import com.mygdx.game.Entities.NonStatics.NonStatic;

public abstract class Creature extends NonStatic {

    protected int minDamage,maxDamage;
    protected int accuracy;
    protected int defence;
    protected int critChance;
    protected float attackSpeed;

    @Override
    protected void die() {
        super.die();
        dropLoot();
    }

    protected abstract void dropLoot();

    @Override
    public float getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public int getMinDamage() {
        return minDamage;
    }

    @Override
    public int getMaxDamage() {
        return maxDamage;
    }

    @Override
    public int getAccuracy() {
        return accuracy;
    }

    @Override
    public int getCritChance() {
        return critChance;
    }

    @Override
    public float getAttackSpeed() {
        return attackSpeed;
    }
}
