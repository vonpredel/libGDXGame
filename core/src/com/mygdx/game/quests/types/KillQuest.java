package com.mygdx.game.quests.types;

import com.mygdx.game.Entities.EntityType;
import com.mygdx.game.quests.Quest;

public class KillQuest extends Quest {

    private EntityType type;
    private int amountCounter;
    private int requiredAmount;

    @Override
    public boolean checkIsQuestCompleted() {
        return amountCounter >= requiredAmount;
    }

    @Override
    public String getProgressStatus() {
        return type.toString().replace("_"," ").toLowerCase() + " | " + amountCounter + " / " + requiredAmount;
    }

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

    public void increaseCounter() {
        amountCounter++;
    }
}
