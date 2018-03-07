package com.mygdx.game.World.Zones;

import com.mygdx.game.Tiles.Tile;
import java.util.List;

public class Zone {

    protected List<Tile> tileList;
    protected int width,height;

    public Zone(List<Tile> tileList, int width, int height) {
        this.tileList = tileList;
        this.width = width;
        this.height = height;
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
}
