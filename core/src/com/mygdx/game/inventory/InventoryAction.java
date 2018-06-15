package com.mygdx.game.inventory;

import com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Items.types.MiscellaneousItem;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.World.World;

public class InventoryAction {

    private Player player;

    public InventoryAction(Player player) {
        this.player = player;
    }

    public void useItem(UsableItem usableItem) {
        switch (usableItem.function) {
            case ItemsConstants.POTION_OF_HEALTH_MINOR:
                player.setCurrentHealthPoints(player.getCurrentHealthPoints() + 20);
                break;
            case ItemsConstants.POTION_OF_HEALTH_MAJOR:
                player.setCurrentHealthPoints(player.getMaxHealthPoints());
                break;
            case ItemsConstants.POTION_OF_STAMINA_MINOR:
                player.setCurrentStaminaPoints(player.getCurrentStaminaPoints() + 20);
                break;
            case ItemsConstants.POTION_OF_STAMINA_MAJOR:
                player.setCurrentStaminaPoints(player.getMaxStaminaPoints());
                break;
            case ItemsConstants.POTION_OF_MANA_MINOR:
                player.setCurrentManaPoints(player.getCurrentManaPoints() + 20);
                break;
            case ItemsConstants.POTION_OF_MANA_MAJOR:
                player.setCurrentManaPoints(player.getMaxManaPoints());
                break;
            case ItemsConstants.POTION_SOMETHING:
                System.out.println("SOMETHING");
                break;
        }
        if(player.getCurrentHealthPoints() > player.getMaxHealthPoints())
            player.setCurrentHealthPoints(player.getMaxHealthPoints());
        if(player.getCurrentStaminaPoints() > player.getMaxStaminaPoints())
            player.setCurrentStaminaPoints(player.getMaxStaminaPoints());
        if(player.getCurrentManaPoints() > player.getMaxManaPoints())
            player.setCurrentManaPoints(player.getMaxManaPoints());
        player.getInventory().getItems().remove(usableItem);
    }

    public void useQuestItem(QuestItem questItem) {
        switch (questItem.function) {
            case ItemsConstants.MAP:
                // TODO MAP
                final ControlsAndGUIsHandler controlsAndGUIsHandler = World.getControlsAndGUIsHandler();
                controlsAndGUIsHandler.setMapState();
                controlsAndGUIsHandler.inventoryGUI.isEnabled = false;
                controlsAndGUIsHandler.mapGUI.isEnabled = true;
                break;
        }
    }

    public void useMiscItem(MiscellaneousItem miscellaneousItem) {
        switch (miscellaneousItem.function) {
            case ItemsConstants.MISCE:
                break;
        }
    }

    public static String getDescriptionForItemBasedByFunction(int itemsFunctionConst) {
        switch (itemsFunctionConst) {
            case ItemsConstants.POTION_OF_HEALTH_MINOR:
                return "Regenerate 20 Health Points";
            case ItemsConstants.POTION_OF_HEALTH_MAJOR:
                return "Regenerate Full Health Points";
            case ItemsConstants.POTION_OF_STAMINA_MINOR:
                return "Regenerate 20 Stamina Points";
            case ItemsConstants.POTION_OF_STAMINA_MAJOR:
                return "Regenerate Full Stamina Points";
            case ItemsConstants.POTION_OF_MANA_MINOR:
                return "Regenerate 20 Mana Points";
            case ItemsConstants.POTION_OF_MANA_MAJOR:
                return "Regenerate Full Mana Points";
            case ItemsConstants.POTION_SOMETHING:
                return "Regenerate Something //MISSING FUNCTION//";
            case ItemsConstants.MAP:
                return "Old paper map";
            case ItemsConstants.WOLF_FUR:
                return "Warm grey wolf fur";
            case ItemsConstants.MISCE:
                return "Miscelanous blalablalba";
            case ItemsConstants.GOLD:
                return "Gold : " + World.getPlayer().getGold();
            default:
                return "DESCRIPTION DESCRIPTION";
        }
    }
}
