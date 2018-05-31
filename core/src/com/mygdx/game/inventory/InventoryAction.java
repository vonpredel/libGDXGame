package com.mygdx.game.inventory;

import com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Items.types.MiscellaneousItem;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.World.World;

public class InventoryAction {

    private NonStatic nonStatic;

    public InventoryAction(NonStatic nonStatic) {
        this.nonStatic = nonStatic;
    }

    public void useItem(UsableItem usableItem) {
        switch (usableItem.function) {
            case ItemsConstants.POTION_OF_HEALTH_MINOR:
                nonStatic.setCurrentHealthPoints(nonStatic.getCurrentHealthPoints() + 20);
                break;
            case ItemsConstants.POTION_OF_HEALTH_MAJOR:
                nonStatic.setCurrentHealthPoints(nonStatic.getMaxHealthPoints());
                break;
            case ItemsConstants.POTION_OF_STAMINA_MINOR:
                nonStatic.setCurrentStaminaPoints(nonStatic.getCurrentStaminaPoints() + 20);
                break;
            case ItemsConstants.POTION_OF_STAMINA_MAJOR:
                nonStatic.setCurrentStaminaPoints(nonStatic.getMaxStaminaPoints());
                break;
            case ItemsConstants.POTION_OF_MANA_MINOR:
                nonStatic.setCurrentManaPoints(nonStatic.getCurrentManaPoints() + 20);
                break;
            case ItemsConstants.POTION_OF_MANA_MAJOR:
                nonStatic.setCurrentManaPoints(nonStatic.getMaxManaPoints());
                break;
            case ItemsConstants.POTION_SOMETHING:
                System.out.println("SOMETHING");
                break;
        }
        if(nonStatic.getCurrentHealthPoints() > nonStatic.getMaxHealthPoints())
            nonStatic.setCurrentHealthPoints(nonStatic.getMaxHealthPoints());
        if(nonStatic.getCurrentStaminaPoints() > nonStatic.getMaxStaminaPoints())
            nonStatic.setCurrentStaminaPoints(nonStatic.getMaxStaminaPoints());
        if(nonStatic.getCurrentManaPoints() > nonStatic.getMaxManaPoints())
            nonStatic.setCurrentManaPoints(nonStatic.getMaxManaPoints());
        nonStatic.getInventory().getItems().remove(usableItem);
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
            default:
                return "DESCRIPTION DESCRIPTION";
        }
    }
}
