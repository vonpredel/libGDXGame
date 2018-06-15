package com.mygdx.game.inventory;

import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.MiscellaneousItem;
import com.mygdx.game.Items.types.OffHand;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {

    private Weapon equipedWeapon;
    private OffHand equipedOffHand;
    private Armor equipedArmor;
    private Armor equipedHelmet;
    private Armor equipedGloves;
    private Armor equipedBoots;

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
    public List<OffHand> getOffHands() {
        return getSpecifiedItems(OffHand.class);
    }
    public List<Item> getItems() {
        return items;
    }

    public void unEquipOffHand() {
        if (getArmors().size() + getOffHands().size() > 14) return;
        items.add(equipedOffHand);
        if (equipedOffHand.getOffHandType().equals(OffHand.OffHandType.MAGIC)) {
            equipedOffHand.setActive(false);
        }
        equipedOffHand = null;
    }

    public void unEquipItem(Item item, Class clazz) {

    }

    public void equipWeapon(Weapon weapon) {
        if(equipedOffHand != null) {
            if(weapon.getType().equals(Weapon.Type.TWO_HANDED)) {
                unEquipOffHand();
            } else if(weapon.getType().equals(Weapon.Type.RANGED)
                    && !equipedOffHand.getOffHandType().equals(OffHand.OffHandType.QUIVER)) {
                unEquipOffHand();
            } else if (!weapon.getType().equals(Weapon.Type.RANGED)
                    && equipedOffHand.getOffHandType().equals(OffHand.OffHandType.QUIVER)) {
                unEquipOffHand();
            }
        }
        if(equipedWeapon!=null) items.add(equipedWeapon);
        this.equipedWeapon = weapon;
        items.remove(weapon);
    }

    public void removeWholeInventory() {
        equipedWeapon = null;
        equipedOffHand = null;
        equipedArmor = null;
        equipedHelmet = null;
        equipedGloves = null;
        equipedBoots = null;
        items.clear();
    }

    public void equipOffHand(OffHand offHand) {
        if (equipedWeapon != null && equipedWeapon.getType().equals(Weapon.Type.TWO_HANDED)) return;
        switch (offHand.getOffHandType()) {
            case SHIELD:
            case MAGIC:
                if (equipedWeapon != null && !equipedWeapon.getType().equals(Weapon.Type.ONE_HANDED)) return;
                if (equipedOffHand != null) unEquipOffHand();
                this.equipedOffHand = offHand;
                offHand.setActive(true);
                break;
            case QUIVER:
                if (equipedWeapon != null && !equipedWeapon.getType().equals(Weapon.Type.RANGED)) return;
                if (equipedOffHand != null) unEquipOffHand();
                this.equipedOffHand = offHand;
                break;
        }
        items.remove(offHand);
    }

    public void equipArmor(Armor armor) {
        switch (armor.getArmorSlot()) {
            case CHEST:
                if(equipedArmor!=null) {
                    World.getPlayer().setMaxHealthPoints(World.getPlayer()
                            .getMaxHealthPoints() - (int) equipedArmor.getAmount());
                    items.add(equipedArmor);
                }
                World.getPlayer().setMaxHealthPoints(World.getPlayer()
                        .getMaxHealthPoints() + (int) armor.getAmount());
                this.equipedArmor = armor;
                break;
            case HELMET:
                if(equipedHelmet!=null) items.add(equipedHelmet);
                this.equipedHelmet = armor;
                break;
            case GLOVES:
                if(equipedGloves!=null) items.add(equipedGloves);
                this.equipedGloves = armor;
                break;
            case BOOTS:
                if(equipedBoots!=null) items.add(equipedBoots);
                this.equipedBoots = armor;
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

    public OffHand getEquipedOffHand() {
        return equipedOffHand;
    }

    public Armor getEquipedGloves() {
        return equipedGloves;
    }

    public Armor getEquipedBoots() {
        return equipedBoots;
    }
}
