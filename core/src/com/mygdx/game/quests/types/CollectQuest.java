package com.mygdx.game.quests.types;

import com.mygdx.game.Items.ItemType;
import com.mygdx.game.quests.Quest;

public class CollectQuest extends Quest {

    private ItemType itemType;
    private int requiredAmount;

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }
}
