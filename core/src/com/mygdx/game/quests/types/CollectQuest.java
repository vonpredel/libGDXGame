package com.mygdx.game.quests.types;

import com.mygdx.game.Items.ItemType;
import com.mygdx.game.World.World;
import com.mygdx.game.quests.Quest;

public class CollectQuest extends Quest {

    private ItemType itemType;
    private int requiredAmount;

    @Override
    public boolean checkIsQuestCompleted() {
        return World.getPlayer().getInventory().getQuestItems().stream()
                .filter(e -> e.getTypeName().equals(String.valueOf(itemType)))
                .count() >= requiredAmount;
    }

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
