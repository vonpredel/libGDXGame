package com.mygdx.game.inventory;

import com.mygdx.game.Entities.NonStatics.NonStatic;
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
            case ItemsFunctionConst.POTION_OF_HEALTH_MINOR:
                nonStatic.setCurrentHealthPoints(nonStatic.getCurrentHealthPoints() + 20);
                break;
            case ItemsFunctionConst.POTION_OF_HEALTH_MAJOR:
                nonStatic.setCurrentHealthPoints(nonStatic.getMaxHealthPoints());
                break;
            case ItemsFunctionConst.POTION_OF_STAMINA_MINOR:
                nonStatic.setCurrentStaminaPoints(nonStatic.getCurrentStaminaPoints() + 20);
                break;
            case ItemsFunctionConst.POTION_OF_STAMINA_MAJOR:
                nonStatic.setCurrentStaminaPoints(nonStatic.getMaxStaminaPoints());
                break;
            case ItemsFunctionConst.POTION_OF_MANA_MINOR:
                nonStatic.setCurrentManaPoints(nonStatic.getCurrentManaPoints() + 20);
                break;
            case ItemsFunctionConst.POTION_OF_MANA_MAJOR:
                nonStatic.setCurrentManaPoints(nonStatic.getMaxManaPoints());
                break;
            case ItemsFunctionConst.POTION_SOMETHING:
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
            case ItemsFunctionConst.MAP:
                // TODO MAP
                World.getControlsAndGUIsHandler().setMapState();
                World.getControlsAndGUIsHandler().inventoryGUI.isEnabled = false;
                World.getControlsAndGUIsHandler().mapGUI.isEnabled = true;
                break;
        }
    }
}
