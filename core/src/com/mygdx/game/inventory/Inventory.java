package com.mygdx.game.inventory;

import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.MiscellaneousItem;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.Items.types.Weapon;
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

    public void removeWholeInventory() {
        equipedWeapon = null;
        equipedShield = null;
        equipedArmor = null;
        equipedHelmet = null;
        items.clear();
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
