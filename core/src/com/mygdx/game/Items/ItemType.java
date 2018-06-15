package com.mygdx.game.Items;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.Items.types.MiscellaneousItem;
import com.mygdx.game.Items.types.OffHand;

public enum ItemType {
	SWORD(Weapon.class),
	GOLDEN_SWORD(Weapon.class),
	WOODEN_SWORD(Weapon.class),
	BIG_WOODEN_SWORD(Weapon.class),
	AXE(Weapon.class),
	GOLDEN_AXE2(Weapon.class),
	LEGENDARY_AXE(Weapon.class),
	DOUBLE_AXE(Weapon.class),
	GOLDEN_DOUBLE_AXE(Weapon.class),
	LEGENDARY_DOUBLE_AXE(Weapon.class),
	DAGGER(Weapon.class),
	GOLDEN_DAGGER2(Weapon.class),
	SPEAR2(Weapon.class),
	HAMMER(Weapon.class),
	GOLDEN_HAMMER(Weapon.class),
	WAND(Weapon.class),
	GOLDEN_WAND(Weapon.class),
	BOW(Weapon.class),
	GOLDEN_BOW2(Weapon.class),
	KNIFE(Weapon.class),
	IRON_DAGGER(Weapon.class),
	STEEL_DAGGER(Weapon.class),
	GOLDEN_DAGGER(Weapon.class),
	BRASS_DAGGER(Weapon.class),
	ELVEN_DAGGER(Weapon.class),
	MOONSTONE_DAGGER(Weapon.class),
	OBSIDIAN_DAGGER(Weapon.class),
	DRAGON_DAGGER(Weapon.class),
	CRYSTAL_DAGGER(Weapon.class),
	IRON_SHORT_SWORD(Weapon.class),
	STEEL_SHORT_SWORD(Weapon.class),
	GOLDEN_SHORT_SWORD(Weapon.class),
	BRASS_SHORT_SWORD(Weapon.class),
	ELVEN_SHORT_SWORD(Weapon.class),
	MOONSTONE_SHORT_SWORD(Weapon.class),
	OBSIDIAN_SHORT_SWORD(Weapon.class),
	DRAGON_SHORT_SWORD(Weapon.class),
	CRYSTAL_SWORD(Weapon.class),
	RAPIER(Weapon.class),
	SABRE(Weapon.class),
	OGRE_SWORD(Weapon.class),
	GLADIUS(Weapon.class),
	BROAD_SWORD(Weapon.class),
	IRON_AXE(Weapon.class),
	STEEL_AXE(Weapon.class),
	GOLDEN_AXE(Weapon.class),
	BRASS_AXE(Weapon.class),
	ELVEN_AXE(Weapon.class),
	MOONSTONE_AXE(Weapon.class),
	OBSIDIAN_AXE(Weapon.class),
	DRAGON_AXE(Weapon.class),
	CRYSTAL_AXE(Weapon.class),
	OGRE_AXE(Weapon.class),
	SICKLE(Weapon.class),
	IRON_MACE(Weapon.class),
	STEEL_MACE(Weapon.class),
	GOLDEN_MACE(Weapon.class),
	BRASS_MACE(Weapon.class),
	ELVEN_MACE(Weapon.class),
	MOONSTONE_MACE(Weapon.class),
	OBSIDIAN_MACE(Weapon.class),
	DRAGON_MACE(Weapon.class),
	SMALL_HAMMER(Weapon.class),
	MORGENSTERN(Weapon.class),
	FLAIL(Weapon.class),
	CLUB(Weapon.class),
	IRON_LONG_SWORD(Weapon.class),
	STEEL_LONG_SWORD(Weapon.class),
	GOLDEN_LONG_SWORD(Weapon.class),
	BRASS_LONG_SWORD(Weapon.class),
	ELVEN_LONG_SWORD(Weapon.class),
	MOONSTONE_LONG_SWORD(Weapon.class),
	OBSIDIAN_LONG_SWORD(Weapon.class),
	DRAGON_LONG_SWORD(Weapon.class),
	IRON_GREAT_AXE(Weapon.class),
	STEEL_GREAT_AXE(Weapon.class),
	GOLDEN_GREAT_AXE(Weapon.class),
	BRASS_GREAT_AXE(Weapon.class),
	ELVEN_GREAT_AXE(Weapon.class),
	MOONSTONE_GREAT_AXE(Weapon.class),
	OBSIDIAN_GREAT_AXE(Weapon.class),
	DRAGON_GREAT_AXE(Weapon.class),
	IRON_GREAT_HAMMER(Weapon.class),
	STEEL_GREAT_HAMMER(Weapon.class),
	GOLDEN_GREAT_HAMMER(Weapon.class),
	BRASS_GREAT_HAMMER(Weapon.class),
	ELVEN_GREAT_HAMMER(Weapon.class),
	MOONSTONE_GREAT_HAMMER(Weapon.class),
	OBSIDIAN_GREAT_HAMMER(Weapon.class),
	DRAGON_GREAT_HAMMER(Weapon.class),
	CRYSTAL_GREAT_HAMMER(Weapon.class),
	GREAT_CLUB(Weapon.class),
	STAFF(Weapon.class),
	BATTLE_STAFF(Weapon.class),
	PIKE(Weapon.class),
	SPEAR(Weapon.class),
	TRIDENT(Weapon.class),
	GLAIVE(Weapon.class),
	HALABARD(Weapon.class),
	SHORT_BOW(Weapon.class),
	LONG_BOW(Weapon.class),
	COMPOSITE_BOW(Weapon.class),
	GOLDEN_BOW(Weapon.class),
	BRASS_BOW(Weapon.class),
	ELVEN_BOW(Weapon.class),
	MOONSTONE_BOW(Weapon.class),
	OBSIDIAN_BOW(Weapon.class),
	DRAGON_BOW(Weapon.class),
	CRYSTAL_BOW(Weapon.class),
	OGREBOW(Weapon.class),
	CROSSBOW(Weapon.class),
	SMALL_CROSSBOW(Weapon.class),
	BATTLE_CROSSBOW(Weapon.class),
	BLOW_GUN(Weapon.class),
	SLINGSHOT(Weapon.class),
	WHIP(Weapon.class),
	LEATHER_ARMOR2(Armor.class),
	GOLDEN_LEATHER_ARMOR(Armor.class),
	ARMOR(Armor.class),
	GOLDEN_ARMOR2(Armor.class),
	HELMET(Armor.class),
	GOLDEN_HELMET2(Armor.class),
	GLOVES(Armor.class),
	BOOTS(Armor.class),
	LEATHER_ARMOR(Armor.class),
	FUR_ARMOR(Armor.class),
	HARD_LEATHER_ARMOR(Armor.class),
	HUNTER_ARMOR(Armor.class),
	IRON_ARMOR(Armor.class),
	CHAINMAIL_ARMOR(Armor.class),
	STEEL_ARMOR(Armor.class),
	ELITE_ARMOR(Armor.class),
	SILVER_ARMOR(Armor.class),
	GOLDEN_ARMOR(Armor.class),
	BRASS_ARMOR(Armor.class),
	ELVEN_ARMOR(Armor.class),
	MOONSTONE_ARMOR(Armor.class),
	OBSIDIAN_ARMOR(Armor.class),
	DRAGON_ARMOR(Armor.class),
	GUARD_ARMOR(Armor.class),
	ADEPT_ARMOR(Armor.class),
	MILITA_ARMOR(Armor.class),
	KNIGHT_ARMOR(Armor.class),
	CLOTH_ARMOR(Armor.class),
	LEATHER_HELMET(Armor.class),
	FUR_HELMET(Armor.class),
	HARD_LEATHER_HELMET(Armor.class),
	HUNTER_HELMET(Armor.class),
	IRON_HELMET(Armor.class),
	CHAINMAIL_HELMET(Armor.class),
	STEEL_HELMET(Armor.class),
	ELITE_HELMET(Armor.class),
	SILVER_HELMET(Armor.class),
	GOLDEN_HELMET(Armor.class),
	BRASS_HELMET(Armor.class),
	ELVEN_HELMET(Armor.class),
	MOONSTONE_HELMET(Armor.class),
	OBSIDIAN_HELMET(Armor.class),
	DRAGON_HELMET(Armor.class),
	HOOD(Armor.class),
	RITUAL_MASK(Armor.class),
	LEATHER_GLOVES(Armor.class),
	FUR_GLOVES(Armor.class),
	HARD_LEATHER_GLOVES(Armor.class),
	HUNTER_GLOVES(Armor.class),
	IRON_GLOVES(Armor.class),
	STEEL_GLOVES(Armor.class),
	SILVER_GLOVES(Armor.class),
	GOLDEN_GLOVES(Armor.class),
	BRASS_GLOVES(Armor.class),
	ELVEN_GLOVES(Armor.class),
	MOONSTONE_GLOVES(Armor.class),
	OBSIDIAN_GLOVES(Armor.class),
	DRAGON_GLOVES(Armor.class),
	LEATHER_BOOTS(Armor.class),
	FUR_BOOTS(Armor.class),
	HARD_LEATHER_BOOTS(Armor.class),
	HUNTER_BOOTS(Armor.class),
	IRON_BOOTS(Armor.class),
	STEEL_BOOTS(Armor.class),
	SILVER_BOOTS(Armor.class),
	GOLDEN_BOOTS(Armor.class),
	BRASS_BOOTS(Armor.class),
	ELVEN_BOOTS(Armor.class),
	MOONSTONE_BOOTS(Armor.class),
	OBSIDIAN_BOOTS(Armor.class),
	DRAGON_BOOTS(Armor.class),
	LESSER_POTION_OF_HEALTH(UsableItem.class),
	POTION_OF_HEALTH(UsableItem.class),
	LESSER_POTION_OF_STAMINA(UsableItem.class),
	POTION_OF_STAMINA(UsableItem.class),
	LESSER_POTION_OF_MANA(UsableItem.class),
	POTION_OF_MANA(UsableItem.class),
	POTION_OF_SOMETHING(UsableItem.class),
	MAP(QuestItem.class),
	WOLF_FUR(QuestItem.class),
	GOLD(QuestItem.class),
	MISCE_MISCE(MiscellaneousItem.class),
	SHIELD(OffHand.class),
	GOLDEN_SHIELD2(OffHand.class),
	SMALL_SHIELD(OffHand.class),
	GOLDEN_SMALL_SHIELD(OffHand.class),
	WOODEN_SHIELD(OffHand.class),
	GOLDEN_WOODEN_SHIELD(OffHand.class),
	IRON_SHIELD(OffHand.class),
	STEEL_SHIELD(OffHand.class),
	GOLDEN_SHIELD(OffHand.class),
	BRASS_SHIELD(OffHand.class),
	ELVEN_SHIELD(OffHand.class),
	MOONSTONE_SHIELD(OffHand.class),
	OBSIDIAN_SHIELD(OffHand.class),
	DRAGON_SHIELD(OffHand.class),
	SOLARBANE_CLAN_SHIELD(OffHand.class),
	REDPIKES_CLAN_SHIELD(OffHand.class),
	POISONCLOAKS_CLAN_SHIELD(OffHand.class),
	CHAOSLIGHT_CLAN_SHIELD(OffHand.class),
	GRAYSWORDS_CLAN_SHIELD(OffHand.class),
	STORMTHORN_CLAN_SHIELD(OffHand.class),
	SNOWFORCE_CLAN_SHIELD(OffHand.class),
	QUICKHANDS_CLAN_SHIELD(OffHand.class),
	DOOMSTARS_CLAN_SHIELD(OffHand.class),
	LEATHER_QUIVER(OffHand.class),
	STEEL_QUIVER(OffHand.class),
	GOLDEN_QUIVER(OffHand.class),
	BRASS_QUIVER(OffHand.class),
	ELVEN_QUIVER(OffHand.class),
	MOONSTONE_QUIVER(OffHand.class),
	OBSIDIAN_QUIVER(OffHand.class),
	DRAGON_QUIVER(OffHand.class),
	OGRE_QUIVER(OffHand.class),
	CRYSTAL_QUIVER(OffHand.class),
	BELT_QUIVER(OffHand.class),
	BOOK_OF_MAGIC(OffHand.class),
	BOOK_OF_DARK_MAGIC(OffHand.class),
	BOOK_OF_SPELLS(OffHand.class),
	BOOK_OF_INVOCATION(OffHand.class);

    private final Class<? extends Item> cls;

    private ItemType(Class<? extends Item> cls) {
        this.cls = cls;
    }

    public Class<? extends Item> getCls() {
        return cls;
    }
}