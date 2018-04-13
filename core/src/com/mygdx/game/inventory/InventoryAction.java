package com.mygdx.game.inventory;

import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;

public class InventoryAction {

    private Player player;
    private Inventory inventory;

    public InventoryAction(Player player) {
        this.player = player;
        this.inventory = player.getInventory();
    }

    public void useItem(UsableItem usableItem) {
        switch (usableItem.function) {
            case ItemsFunctionConst.POTION_OF_HEALTH_MINOR:
                player.setCurrentHealthPoints(player.getCurrentHealthPoints() + 20);
                break;
            case ItemsFunctionConst.POTION_OF_HEALTH_MAJOR:
                player.setCurrentHealthPoints(player.getMaxHealthPoints());
                break;
            case ItemsFunctionConst.POTION_OF_STAMINA_MINOR:
                player.setCurrentStaminaPoints(player.getCurrentStaminaPoints() + 20);
                break;
            case ItemsFunctionConst.POTION_OF_STAMINA_MAJOR:
                player.setCurrentStaminaPoints(player.getMaxStaminaPoints());
                break;
            case ItemsFunctionConst.POTION_OF_MANA_MINOR:
                player.setCurrentManaPoints(player.getCurrentManaPoints() + 20);
                break;
            case ItemsFunctionConst.POTION_OF_MANA_MAJOR:
                player.setCurrentManaPoints(player.getMaxManaPoints());
                break;
            case ItemsFunctionConst.POTION_SOMETHING:
                System.out.println("SOMETHING");
                break;
        }
        if(player.getCurrentHealthPoints() > player.getMaxHealthPoints())
            player.setCurrentHealthPoints(player.getMaxHealthPoints());
        if(player.getCurrentStaminaPoints() > player.getMaxStaminaPoints())
            player.setCurrentStaminaPoints(player.getMaxStaminaPoints());
        if(player.getCurrentManaPoints() > player.getMaxManaPoints())
            player.setCurrentManaPoints(player.getMaxManaPoints());
        inventory.getItems().remove(usableItem);
    }

    public void useQuestItem(QuestItem questItem, Inventory inventory) {

    }
}
