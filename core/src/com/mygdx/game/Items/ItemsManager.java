package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.MiscellaneousItem;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Utils.BaseManager;
import com.mygdx.game.Utils.assets.Assets;

public class ItemsManager extends BaseManager<Item, ItemType, ItemsContainer> {

    public ItemsManager(Assets assets, ItemsContainer itemsContainer) {
        super(assets, itemsContainer);
    }

    @Override
    protected Class<? extends Item> enumToClass(final ItemType typeEnum) {
        return typeEnum.getCls();
    }

    @Override
    public void loadDefinitions() {
        this.creators.put(Weapon.class, values -> {
            final Weapon weapon = new Weapon(values[0], Boolean.parseBoolean(values[2]), Integer.parseInt(values[3]),
                    Integer.parseInt(values[4]), Integer.parseInt(values[5]), Float.parseFloat(values[6]),
                    Integer.parseInt(values[7]), Integer.parseInt(values[9]), Boolean.parseBoolean(values[10]));
            weapon.texture = this.assets.manager.get(values[1], Texture.class);
            return weapon;
        });
        this.creators.put(Armor.class, values -> {
            final Armor armor = new Armor(values[0], Boolean.parseBoolean(values[2]), Integer.parseInt(values[3]),
                    Integer.parseInt(values[4]), Integer.parseInt(values[5]));
            armor.texture = this.assets.manager.get(values[1], Texture.class);
            return armor;
        });
        this.creators.put(UsableItem.class, values -> {
            final UsableItem usableItem = new UsableItem(values[0], Boolean.parseBoolean(values[2]),
                    Integer.parseInt(values[3]));
            usableItem.texture = this.assets.manager.get(values[1], Texture.class);
            return usableItem;
        });
        this.creators.put(QuestItem.class, values -> {
            final QuestItem questItem = new QuestItem(values[0], Boolean.parseBoolean(values[2]),
                    Integer.parseInt(values[3]));
            questItem.texture = this.assets.manager.get(values[1], Texture.class);
            return questItem;
        });
        this.creators.put(MiscellaneousItem.class, values -> {
            final MiscellaneousItem miscellaneousItem = new MiscellaneousItem(values[0], Boolean.parseBoolean(values[2]));
            miscellaneousItem.texture = this.assets.manager.get(values[1], Texture.class);
            return miscellaneousItem;
        });

        this.loadFile("dataItems/weapons.csv", values -> values[0]);
        this.loadFile("dataItems/armors.csv", values -> values[0]);
        this.loadFile("dataItems/usableitems.csv", values -> values[0]);
        this.loadFile("dataItems/questitems.csv", values -> values[0]);
        this.loadFile("dataItems/miscellaneousitems.csv", values -> values[0]);
    }

}
