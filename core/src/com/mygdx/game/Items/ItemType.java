package com.mygdx.game.Items;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.Items.types.MiscellaneousItem;

public enum ItemType {
	SWORD(Weapon.class),
	NO_WEAPON(Weapon.class),
	NO_ARMOR(Armor.class),
	MINOR_POTION_OF_HEALTH(UsableItem.class),
	POTION_OF_HEALTH(UsableItem.class),
	MAJOR_POTION_OF_HEALTH(UsableItem.class),
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