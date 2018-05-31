package com.mygdx.game.Entities.Statics;

import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;
import java.util.List;

public class Chest extends Static {

    private List<Item> itemList;

    public Chest(List<Item> itemList) {
        this.itemList = itemList;
        setState(State.CLOSED);
    }

    @Override
    public void performAction() {
        if (State.OPEN == getState()) return;
        setState(State.OPEN);
        drop();
    }

    private void drop() {
        final Player player = World.getPlayer();
        final Tile tile = World.getTileByPosition(World.getCurrentEntityPosition(player));
        itemList.forEach(i -> i.generateOnMap(tile.x,tile.y));
        itemList.clear();
    }
}
