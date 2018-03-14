package com.mygdx.game.inventory;

import com.mygdx.game.Entities.NonStatics.Characters.Character;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.ItemType;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.types.MiscellaneousItem;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {

    private Armor equipedArmor;
    private Weapon equipedWeapon;

    List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void startingEquipment() {
        items.add(World.getItemsManager().create(ItemType.SWORD));
        items.add(World.getItemsManager().create(ItemType.MISCE_MISCE));
        items.add(World.getItemsManager().create(ItemType.QUEST_QUEST));
        items.add(World.getItemsManager().create(ItemType.MINOR_POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.MINOR_POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.MINOR_POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.MINOR_POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.MAJOR_POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.MAJOR_POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.MAJOR_POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.MAJOR_POTION_OF_HEALTH));
        items.add(World.getItemsManager().create(ItemType.NO_ARMOR));
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
        if(equipedArmor!=null) items.add(equipedArmor);
        this.equipedArmor = armor;
        items.remove(armor);
    }

    public void useItem(UsableItem usableItem, Character character) {
        switch (usableItem.function) {
            case 0:
                character.setCurrentHealthPoints(character.getCurrentHealthPoints() + 5);
                break;
            case 1:
                character.setCurrentHealthPoints(character.getCurrentHealthPoints() + 20);
                break;
            case 2:
                character.setCurrentHealthPoints(character.getMaxHealthPoints());
                break;
        }
        if(character.getCurrentHealthPoints() > character.getMaxHealthPoints())
            character.setCurrentHealthPoints(character.getMaxHealthPoints());
        items.remove(usableItem);
    }

    public Armor getEquipedArmor() {
        return equipedArmor;
    }

    public Weapon getEquipedWeapon() {
        return equipedWeapon;
    }

    public void setEquipedArmor(Armor armor) {
        this.equipedArmor = armor;
    }

    public void setEquipedWeapon(Weapon weapon) {
        this.equipedWeapon = weapon;
    }
}
