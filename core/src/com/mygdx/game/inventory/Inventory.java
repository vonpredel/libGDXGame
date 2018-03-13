package com.mygdx.game.inventory;

import com.mygdx.game.Items.EquiableItems.Armors.Armor;
import com.mygdx.game.Items.EquiableItems.Armors.NoArmor;
import com.mygdx.game.Items.EquiableItems.Weapons.NoWeapon;
import com.mygdx.game.Items.EquiableItems.Weapons.Sword;
import com.mygdx.game.Items.EquiableItems.Weapons.Weapon;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.MiscellaneousItems.MiscellaneousItem;
import com.mygdx.game.Items.QuestItems.QuestItem;
import com.mygdx.game.Items.UsableItems.UsableItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {

    private static final NoArmor noArmor = new NoArmor();
    private static final NoWeapon noWeapon = new NoWeapon();

    private Armor equipedArmor;
    private Weapon equipedWeapon;

    List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
        this.equipedArmor = noArmor;
        this.equipedWeapon = noWeapon;
    }

    public void startingEquipment() {
        items.add(new Sword());
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
        if(equipedWeapon.equals(weapon)) {
            return;
        }
        items.add(equipedWeapon);
        this.equipedWeapon = weapon;
        items.remove(weapon);
    }

    public void equipArmor(Armor armor) {
        if(equipedArmor.equals(armor)) {
            return;
        }
        items.add(equipedArmor);
        this.equipedArmor = armor;
        items.remove(armor);
    }

    public void uNequipWeapon() {
        items.add(equipedWeapon);
        this.equipedWeapon = noWeapon;
    }

    public void uNequipArmor() {
        items.add(equipedArmor);
        this.equipedArmor = noArmor;
    }

    public Armor getEquipedArmor() {
        return equipedArmor;
    }

    public Weapon getEquipedWeapon() {
        return equipedWeapon;
    }

    public void setEquipedArmor(Armor equipedArmor) {
        this.equipedArmor = equipedArmor;
    }

    public void setEquipedWeapon(Weapon equipedWeapon) {
        this.equipedWeapon = equipedWeapon;
    }
}
