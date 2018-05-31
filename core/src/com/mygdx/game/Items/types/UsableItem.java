package com.mygdx.game.Items.types;

import com.mygdx.game.Items.Item;
import com.mygdx.game.inventory.InventoryAction;
import java.util.ArrayList;
import java.util.List;

public class UsableItem extends Item {

    public int function;

    public UsableItem(String name, boolean stackable,int function) {
        super(name, stackable);
        this.function = function;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add(name);
        description.add(InventoryAction.getDescriptionForItemBasedByFunction(function));
        return description;
    }
}
