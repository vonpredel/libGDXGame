package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entities.NonStatics.Enemy;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Entities.Statics.Static;
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

    private void loadNameAndTexture(final String name,final String texture, final Entity entity) {
        entity.setName(name);
        entity.setTexture(this.assets.manager.get(texture, Texture.class));
    }

    private void loadStatistics(final String[] values, final NonStatic nonStatic) {
        nonStatic.setStrength(Integer.parseInt(values[2]));
        nonStatic.setDexterity(Integer.parseInt(values[3]));
        nonStatic.setVitality(Integer.parseInt(values[4]));
        nonStatic.setEnergy(Integer.parseInt(values[5]));
        nonStatic.setMaxHealthPoints(Integer.parseInt(values[6]));
        nonStatic.setMaxStaminaPoints(Integer.parseInt(values[7]));
        nonStatic.setMaxManaPoints(Integer.parseInt(values[8]));
        nonStatic.setMovementSpeed(Float.parseFloat(values[9]));
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
        this.creators.put(Player.class, values -> {
            final Player player = new Player();

            this.loadNameAndTexture(values[0],values[1],player);
            this.loadStatistics(values,player);
            this.loadInventory(values[10], player);
            return player;
        });

        this.creators.put(Enemy.class, values -> {
            final Enemy enemy = new Enemy();

            this.loadNameAndTexture(values[0],values[1],enemy);
            this.loadStatistics(values,enemy);
            this.loadInventory(values[10], enemy);
            enemy.setAiType(AILogic.AIType.valueOf(values[11]));
            enemy.setExperience(Integer.parseInt(values[12]));
            return enemy;
        });

        this.creators.put(Static.class, values -> {
            final Static aStatic = new Static();

            loadNameAndTexture(values[0],values[1],aStatic);
            return aStatic;
        });

        this.loadFile("dataEntities/enemies.csv", values -> values[0]);
        this.loadFile("dataEntities/player.csv", values -> values[0]);
        this.loadFile("dataEntities/statics.csv", values -> values[0]);
    }
}
