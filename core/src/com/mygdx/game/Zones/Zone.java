package com.mygdx.game.Zones;

import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Tiles.Tile;
import java.util.List;

public class Zone {

    private List<Entity> zoneEntitiesList;
    private List<Item> zoneItemsList;

    private List<Tile> tileList;
    private int width, height;
    private int index;
    private int x;
    private int y;

    public Zone(List<Tile> tileList, int width, int height) {
        this.tileList = tileList;
        this.width = width;
        this.height = height;
        this.index = -1;
    }

    public List<Tile> getTileList() {
        return tileList;
    }

    public void setTileList(List<Tile> tileList) {
        this.tileList = tileList;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getIndex() {
        return index;
    }

    public void setCoordinates(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Entity> getZoneEntitiesList() {
        return zoneEntitiesList;
    }

    public void addZoneEntity(Entity entity) {
        zoneEntitiesList.add(entity);
    }

    public List<Item> getZoneItemsList() {
        return zoneItemsList;
    }

    public void addZoneItem(Item item) {
        zoneItemsList.add(item);
    }
}
