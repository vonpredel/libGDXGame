package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.MiscellaneousItem;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.textFile.TextFile;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemsManager {

    private final Map<String, String[]> rawValues;
    private final Map<Class<? extends Item>, Function<String[], Item>> itemCreators;
    private final Map<String, String> itemTypeNameCache;

    private final Assets assets;
    private final ItemsContainer itemsContainer;

    public ItemsManager(Assets assets, ItemsContainer itemsContainer) {
        this.assets = assets;
        this.itemsContainer = itemsContainer;

        this.rawValues = new HashMap<>();
        this.itemCreators = new HashMap<>();
        this.itemTypeNameCache = new HashMap<>();
    }

    private String[] getLines(final TextFile textFile) {
        return textFile.getContent().split("\\n");
    }

    private String[] getValues(final String line) {
        return line.split(",");
    }

    private void loadFile(final String fileName, final Function<String[], String> nameProvider) {
        final String[] fileLines = this.getLines(this.assets.manager.get(fileName, TextFile.class));
        for (final String fileLine : fileLines) {
            final String[] values = this.getValues(fileLine);
            this.rawValues.put(nameProvider.apply(values), values);
        }
    }

    public void loadDefinitions() {
        this.itemCreators.put(Weapon.class, values -> {
            final Weapon weapon = new Weapon(values[0], Boolean.parseBoolean(values[2]), Integer.parseInt(values[3]),
                    Integer.parseInt(values[4]), Integer.parseInt(values[5]), Float.parseFloat(values[6]),
                    Integer.parseInt(values[7]));
            weapon.texture = this.assets.manager.get(values[1], Texture.class);
            return weapon;
        });
        this.itemCreators.put(Armor.class, values -> {
            final Armor armor = new Armor(values[0], Boolean.parseBoolean(values[2]), Integer.parseInt(values[3]),
                    Integer.parseInt(values[4]), Integer.parseInt(values[5]));
            armor.texture = this.assets.manager.get(values[1], Texture.class);
            return armor;
        });
        this.itemCreators.put(UsableItem.class, values -> {
            final UsableItem usableItem = new UsableItem(values[0], Boolean.parseBoolean(values[2]),
                    Integer.parseInt(values[3]));
            usableItem.texture = this.assets.manager.get(values[1], Texture.class);
            return usableItem;
        });
        this.itemCreators.put(QuestItem.class, values -> {
            final QuestItem questItem = new QuestItem(values[0], Boolean.parseBoolean(values[2]));
            questItem.texture = this.assets.manager.get(values[1], Texture.class);
            return questItem;
        });
        this.itemCreators.put(MiscellaneousItem.class, values -> {
            final MiscellaneousItem miscellaneousItem = new MiscellaneousItem(values[0], Boolean.parseBoolean(values[2]));
            miscellaneousItem.texture = this.assets.manager.get(values[1], Texture.class);
            return miscellaneousItem;
        });

        this.loadFile("data/weapons.csv", values -> values[0]);
        this.loadFile("data/armors.csv", values -> values[0]);
        this.loadFile("data/usableitems.csv", values -> values[0]);
        this.loadFile("data/questitems.csv", values -> values[0]);
        this.loadFile("data/miscellaneousitems.csv", values -> values[0]);
    }

    private String itemTypeToName(final ItemType itemType) {
        return this.itemTypeNameCache.computeIfAbsent(itemType.name(), itemName -> Arrays
                .stream(itemName.toLowerCase().split("_"))
                .map(x -> Character.toUpperCase(x.charAt(0)) + x.substring(1))
                .collect(Collectors.joining(" ")));
    }

    @SuppressWarnings("unchecked")
    public <T extends Item> T create(final ItemType itemType) {
        final String typeToName = this.itemTypeToName(itemType);
        final String[] rawValues = this.rawValues.get(typeToName);
        final Function<String[], Item> creator = this.itemCreators.get(itemType.getCls());

        final T createdItem = (T) creator.apply(rawValues);
        this.itemsContainer.addItem(createdItem);

        return createdItem;
    }

    public ItemsContainer getItemsContainer() {
        return itemsContainer;
    }
}
