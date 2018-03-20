package com.mygdx.game.inventory;

import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemType;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.MiscellaneousItem;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {

    private static final int ARMOR = 0;
    private static final int SHIELD = 1;
    private static final int HELMET = 2;

    private Weapon equipedWeapon;
    private Armor equipedArmor;
    private Armor equipedShield;
    private Armor equipedHelmet;

    List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public <T extends Item> List<T> getSpecifiedItems(Class<T> itemClass) {
        return items.stream().filter(item -> itemClass
                .isAssignableFrom(item.getClass()))
                .map(itemClass::cast)
                .collect(Collectors.toList());
    }

    public List<UsableItem> getUsableItems() {
        return getSpecifiedItems(UsableItem.class);
    }
    public List<QuestItem> getQuestItems() {
        return getSpecifiedItems(QuestItem.class);
    }
    public List<MiscellaneousItem> getMiscellaneousItems() {
        return getSpecifiedItems(MiscellaneousItem.class);
    }
    public List<Armor> getArmors() {
        return getSpecifiedItems(Armor.class);
    }
    public List<Weapon> getWeapons() {
        return getSpecifiedItems(Weapon.class);
    }
    public List<Item> getItems() {
        return items;
    }

    public void equipWeapon(Weapon weapon) {
        if(equipedWeapon!=null) items.add(equipedWeapon);
        this.equipedWeapon = weapon;
        items.remove(weapon);
    }

    public void equipArmor(Armor armor) {
        switch (armor.getArmorSlot()) {
            case ARMOR:
                if(equipedArmor!=null) items.add(equipedArmor);
                this.equipedArmor = armor;
                break;
            case SHIELD:
                if(equipedShield!=null) items.add(equipedShield);
                this.equipedShield = armor;
                break;
            case HELMET:
                if(equipedHelmet!=null) items.add(equipedHelmet);
                this.equipedHelmet = armor;
                break;
        }
        items.remove(armor);
    }

    public void useItem(UsableItem usableItem, Player player) {
        switch (usableItem.function) {
            case 0:
                player.setCurrentHealthPoints(player.getCurrentHealthPoints() + 20);
                break;
            case 1:
                player.setCurrentHealthPoints(player.getMaxHealthPoints());
                break;
            case 2:
                player.setCurrentStaminaPoints(player.getCurrentStaminaPoints() + 20);
                break;
            case 3:
                player.setCurrentStaminaPoints(player.getMaxStaminaPoints());
                break;
            case 4:
                player.setCurrentManaPoints(player.getCurrentManaPoints() + 20);
                break;
            case 5:
                player.setCurrentManaPoints(player.getMaxManaPoints());
                break;
            case 6:
                System.out.println("SOMETHING");
                break;
        }
        if(player.getCurrentHealthPoints() > player.getMaxHealthPoints())
            player.setCurrentHealthPoints(player.getMaxHealthPoints());
        if(player.getCurrentStaminaPoints() > player.getMaxStaminaPoints())
            player.setCurrentStaminaPoints(player.getMaxStaminaPoints());
        if(player.getCurrentManaPoints() > player.getMaxManaPoints())
            player.setCurrentManaPoints(player.getMaxManaPoints());
        items.remove(usableItem);
    }

    public Armor getEquipedArmor() {
        return equipedArmor;
    }

    public Weapon getEquipedWeapon() {
        return equipedWeapon;
    }

    public Armor getEquipedHelmet() {
        return equipedHelmet;
    }

    public Armor getEquipedShield() {
        return equipedShield;
    }
}
