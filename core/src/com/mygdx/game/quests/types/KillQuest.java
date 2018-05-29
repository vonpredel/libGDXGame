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

    public boolean checkIsEnought() {
        return amountCounter >= requiredAmount;
    }

    public void increaseCounter() {
        amountCounter++;
    }
}
