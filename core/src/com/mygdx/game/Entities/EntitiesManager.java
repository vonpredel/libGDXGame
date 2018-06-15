package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entities.NonStatics.Enemy;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Entities.Npc.Merchant;
import com.mygdx.game.Entities.Npc.Quester;
import com.mygdx.game.Entities.Statics.Chest;
import com.mygdx.game.Entities.Statics.Door;
import com.mygdx.game.Entities.Statics.Fountain;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemType;
import com.mygdx.game.Items.ItemsManager;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Utils.AILogic;
import com.mygdx.game.Utils.BaseManager;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.quests.QuestType;
import com.mygdx.game.quests.QuestsManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntitiesManager extends BaseManager<Entity, EntityType, EntitiesContainer> {

    private static final String UNDEFINED_ITEM = "NULL";
    private final ItemsManager itemsManager;
    private final QuestsManager questsManager;

    public EntitiesManager(Assets assets, EntitiesContainer entitiesContainer, ItemsManager itemsManager, QuestsManager questsManager) {
        super(assets, entitiesContainer);
        this.itemsManager = itemsManager;
        this.questsManager = questsManager;
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

    private List<Item> createItemsList(final String itemStringValue) {
        return Arrays.stream(itemStringValue.split("\\|"))
                .map(ItemType::valueOf)
                .map(itemsManager::create)
                .map(Item.class::cast)
                .collect(Collectors.toList());
    }

    private void loadNameAndTexture(final String name,final String texture, final Entity entity) {
        entity.setName(name);
        entity.setTexture(this.assets.manager.get(texture, Texture.class));
    }

    private void loadStatistics(final String[] values, final Player player) {
        player.setStrength(Integer.parseInt(values[2]));
        player.setDexterity(Integer.parseInt(values[3]));
        player.setVitality(Integer.parseInt(values[4]));
        player.setEnergy(Integer.parseInt(values[5]));
        player.setMaxHealthPoints(Integer.parseInt(values[6]));
        player.setMaxStaminaPoints(Integer.parseInt(values[7]));
        player.setMaxManaPoints(Integer.parseInt(values[8]));
        player.setMovementSpeed(Float.parseFloat(values[9]));
    }

    private void loadEnemyParams(final String[] values, final Enemy enemy) {
        enemy.setDefence(Integer.parseInt(values[2]));
        enemy.setMinDamage(Integer.parseInt(values[3]));
        enemy.setMaxDamage(Integer.parseInt(values[4]));
        enemy.setAttackSpeed(Float.parseFloat(values[5]));
        enemy.setCritChance(Integer.parseInt(values[6]));
        enemy.setMaxHealthPoints(Integer.parseInt(values[7]));
        enemy.setAccuracy(Integer.parseInt(values[8]));
        enemy.setRange(Integer.parseInt(values[9]));

        for (String s : values[11].split("\\|")) {
            this.addItemToInventory(s,enemy,false);
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
        this.creators.put(Player.class, values -> {
            final Player player = new Player(Float.parseFloat(values[9]));
            this.loadNameAndTexture(values[0],values[1],player);

            this.loadStatistics(values,player);

            this.loadInventory(values[10], player);
            return player;
        });

        this.creators.put(Enemy.class, values -> {
            final Enemy enemy = new Enemy(Float.parseFloat(values[10]));

            this.loadNameAndTexture(values[0],values[1],enemy);
            this.loadEnemyParams(values,enemy);

            enemy.setAiType(AILogic.AIType.valueOf(values[12]));
            enemy.setExperience(Integer.parseInt(values[13]));
            return enemy;
        });

        this.creators.put(Door.class, values -> {
            final Door door = new Door();
            loadNameAndTexture(values[0],values[1],door);
            return door;
        });

        this.creators.put(Fountain.class, values -> {
            final Fountain fountain = new Fountain(Fountain.FountainType.valueOf(values[2]),
                    Integer.parseInt(values[3]));
            loadNameAndTexture(values[0],values[1],fountain);
            return fountain;
        });

        this.creators.put(Chest.class, values -> {
            final Chest chest = new Chest(createItemsList(values[2]));
            loadNameAndTexture(values[0],values[1],chest);
            return chest;
        });

        this.creators.put(Quester.class, values -> {
            final Quester quester = new Quester();
            loadNameAndTexture(values[0],values[1],quester);
            quester.setQuest(questsManager.create(QuestType.valueOf(values[2])));
            return quester;
        });

        this.creators.put(Merchant.class, values -> {
            final Merchant merchant = new Merchant();
            loadNameAndTexture(values[0],values[1],merchant);
            merchant.setMerchantType(Merchant.MerchantType.valueOf(values[2]));
            merchant.setItemList(createItemsList(values[3]));
            return merchant;
        });

        this.loadFile("dataEntities/enemies.csv", values -> values[0]);
        this.loadFile("dataEntities/player.csv", values -> values[0]);
        this.loadFile("dataEntities/doors.csv", values -> values[0]);
        this.loadFile("dataEntities/fountains.csv", values -> values[0]);
        this.loadFile("dataEntities/chests.csv", values -> values[0]);
        this.loadFile("dataEntities/questers.csv", values -> values[0]);
        this.loadFile("dataEntities/merchants.csv", values -> values[0]);
    }
}
