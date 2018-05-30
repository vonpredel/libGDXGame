package com.mygdx.game.quests.types;

import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemType;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.World.World;
import com.mygdx.game.quests.Quest;
import java.util.List;
import java.util.stream.Collectors;

public class CollectQuest extends Quest {

    private ItemType itemType;
    private int requiredAmount;

    @Override
    public boolean checkIsQuestCompleted() {
        return getQuestItemsWithType().size() >= requiredAmount;
    }

    @Override
    public String getProgressStatus() {
        return itemType + " | " + requiredAmount;
    }

    private List<QuestItem> getQuestItemsWithType() {
        return World.getPlayer().getInventory().getQuestItems().stream()
                .filter(e -> e.getTypeName().equals(String.valueOf(itemType)))
                .collect(Collectors.toList());
    }

    @Override
    public void completeQuest() {
        super.completeQuest();
        World.getPlayer().getInventory().getItems()
                .removeAll(getQuestItemsWithType());
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
