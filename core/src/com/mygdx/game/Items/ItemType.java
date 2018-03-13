package com.mygdx.game.Items;
import com.mygdx.game.Items.Armors.Armor;
import com.mygdx.game.Items.Weapons.Weapon;
import com.mygdx.game.Items.QuestItems.QuestItem;
import com.mygdx.game.Items.UsableItems.UsableItem;
import com.mygdx.game.Items.MiscellaneousItems.MiscellaneousItem;

public enum ItemType {
	SWORD(Weapon.class),
	NO_WEAPON(Weapon.class),
	NO_ARMOR(Armor.class),
	USABLE_USABLE(UsableItem.class),
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