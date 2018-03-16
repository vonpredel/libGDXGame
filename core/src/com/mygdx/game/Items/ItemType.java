package com.mygdx.game.Items;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.Items.types.MiscellaneousItem;

public enum ItemType {
	SWORD(Weapon.class),
	GOLDEN_SWORD(Weapon.class),
	WOODEN_SWORD(Weapon.class),
	BIG_WOODEN_SWORD(Weapon.class),
	AXE(Weapon.class),
	GOLDEN_AXE(Weapon.class),
	LEGENDARY_AXE(Weapon.class),
	DOUBLE_AXE(Weapon.class),
	GOLDEN_DOUBLE_AXE(Weapon.class),
	LEGENDARY_DOUBLE_AXE(Weapon.class),
	DAGGER(Weapon.class),
	GOLDEN_DAGGER(Weapon.class),
	SPEAR(Weapon.class),
	HAMMER(Weapon.class),
	GOLDEN_HAMMER(Weapon.class),
	WAND(Weapon.class),
	GOLDEN_WAND(Weapon.class),
	BOW(Weapon.class),
	GOLDEN_BOW(Weapon.class),
	LEATHER_ARMOR(Armor.class),
	GOLDEN_LEATHER_ARMOR(Armor.class),
	ARMOR(Armor.class),
	GOLDEN_ARMOR(Armor.class),
	SHIELD(Armor.class),
	GOLDEN_SHIELD(Armor.class),
	SMALL_SHIELD(Armor.class),
	GOLDEN_SMALL_SHIELD(Armor.class),
	WOODEN_SHIELD(Armor.class),
	GOLDEN_WOODEN_SHIELD(Armor.class),
	HELMET(Armor.class),
	GOLDEN_HELMET(Armor.class),
	LESSER_POTION_OF_HEALTH(UsableItem.class),
	POTION_OF_HEALTH(UsableItem.class),
	LESSER_POTION_OF_STAMINA(UsableItem.class),
	POTION_OF_STAMINA(UsableItem.class),
	LESSER_POTION_OF_MANA(UsableItem.class),
	POTION_OF_MANA(UsableItem.class),
	POTION_OF_SOMETHING(UsableItem.class),
	QUEST_QUEST(QuestItem.class),
	MISCE_MISCE(MiscellaneousItem.class);

    private final Class<? extends Item> cls;

    private ItemType(Class<? extends Item> cls) {
        this.cls = cls;
    }

    public Class<? extends Item> getCls() {
        return cls;
    }
}