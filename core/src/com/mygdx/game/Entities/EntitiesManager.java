package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entities.NonStatics.Enemy;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemType;
import com.mygdx.game.Items.ItemsManager;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Utils.AILogic;
import com.mygdx.game.Utils.BaseManager;
import com.mygdx.game.Utils.assets.Assets;

public class EntitiesManager extends BaseManager<Entity, EntityType, EntitiesContainer> {

    private static final String UNDEFINED_ITEM = "NULL";
    private final ItemsManager itemsManager;

    public EntitiesManager(Assets assets, EntitiesContainer entitiesContainer, ItemsManager itemsManager) {
        super(assets, entitiesContainer);
        this.itemsManager = itemsManager;
    }

    @Override
    protected Class<? extends Entity> enumToClass(EntityType typeEnum) {
        return typeEnum.getCls();
    }


    private void addItemToInventory(final String itemName, final NonStatic nonStatic, final boolean equip) {
        if (UNDEFINED_ITEM.equals(itemName)) {
            return;
        }

        final Item itemByName = this.itemsManager.create(ItemType.valueOf(itemName));
        itemByName.moveToInventory(nonStatic.getInventory());

        if (!equip) {
            return;
        }

        if (itemByName instanceof Armor) {
            nonStatic.getInventory().equipArmor((Armor) itemByName);
        } else if (itemByName instanceof Weapon) {
            nonStatic.getInventory().equipWeapon((Weapon) itemByName);
        }
    }

    private void loadInventory(final String value, final NonStatic nonStatic) {
        final String[] splitInvValue = value.split("\\|");
        if (splitInvValue.length < 4) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            this.addItemToInventory(splitInvValue[i], nonStatic, true);
        }
        for (int i = 4; i < splitInvValue.length; i++) {
            this.addItemToInventory(splitInvValue[i], nonStatic, false);
        }
    }

    @Override
    public void loadDefinitions() {
        this.creators.put(Enemy.class, values -> {
            final Enemy enemy = new Enemy();

            enemy.setName(values[0]);
            enemy.setTexture(this.assets.manager.get(values[1], Texture.class));
            enemy.setStrength(Integer.parseInt(values[2]));
            enemy.setDexterity(Integer.parseInt(values[3]));
            enemy.setVitality(Integer.parseInt(values[4]));
            enemy.setEnergy(Integer.parseInt(values[5]));
            enemy.setMaxHealthPoints(Integer.parseInt(values[6]));
            enemy.setMaxStaminaPoints(Integer.parseInt(values[7]));
            enemy.setMaxManaPoints(Integer.parseInt(values[8]));
            enemy.setMovementSpeed(Float.parseFloat(values[9]));
            this.loadInventory(values[10], enemy);
            enemy.setAiType(AILogic.AIType.valueOf(values[11]));
            return enemy;
        });

        this.loadFile("dataEntities/enemies.csv", values -> values[0]);
    }
}
