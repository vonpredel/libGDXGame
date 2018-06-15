package com.mygdx.game.Entities.NonStatics;

import com.mygdx.game.Utils.AILogic;
import com.mygdx.game.World.World;

public class Enemy extends NonStatic {

    private AILogic.AIType aiType;
    private int experience;

    private int defence;
    private int minDamage;
    private int maxDamage;
    private float attackSpeed;
    private int critChance;
    private int accuracy;
    private int range;

    public Enemy(float movementSpeed) {
        this.setMovementSpeed(movementSpeed);
        defaultMovementSpeed = movementSpeed;
    }

    @Override
    public void ai() {
        if (this.aiType != null) {
            this.aiType.getConsumer().accept(this);
        }
    }

    public void setAiType(AILogic.AIType aiType) {
        this.aiType = aiType;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    protected void die() {
        super.die();
        final Player player = World.getPlayer();
        player.setExperience(player.getExperience() + this.getExperience());
    }

    @Override
    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    public float getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    @Override
    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    @Override
    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    @Override
    public float getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    @Override
    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    @Override
    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    @Override
    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
