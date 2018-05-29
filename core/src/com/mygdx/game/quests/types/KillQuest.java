package com.mygdx.game.quests.types;

import com.mygdx.game.Entities.EntityType;
import com.mygdx.game.quests.Quest;

public class KillQuest extends Quest {

    private EntityType type;
    private int amountCounter;
    private int requiredAmount;

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public int getAmountCounter() {
        return amountCounter;
    }

    public void setAmountCounter(int amountCounter) {
        this.amountCounter = amountCounter;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public boolean checkIsEnought() {
        return amountCounter >= requiredAmount;
    }

    public void increaseCounter() {
        amountCounter++;
    }
}
