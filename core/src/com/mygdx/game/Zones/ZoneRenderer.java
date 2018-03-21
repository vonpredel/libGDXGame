package com.mygdx.game.Zones;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.List;

public class ZoneRenderer {

    private Zone zone;
    private List<Zone> neighbours;
    private SpriteBatch batch;

    public ZoneRenderer(SpriteBatch batch) {
        this.batch = batch;
        this.neighbours = new ArrayList<>(8);
    }

    private void prepareZoneTiles(final Zone zone) {
        List<Tile> tileList = zone.getTileList();
        int height = zone.getHeight();
        final int zonePixelHeight = zone.getY() * (zone.getHeight() * Constants.DEFAULT_TILE_HEIGHT);

        int xpositionToRender = zone.getX() * (zone.getWidth() * Constants.DEFAULT_TILE_WIDTH);
        int ypositionToRender = zonePixelHeight;

        for (Tile t : tileList) {
            t.x = xpositionToRender;
            t.y = ypositionToRender;

            ypositionToRender += t.height;

            if (ypositionToRender >= (zonePixelHeight + (height * t.height))) {
                ypositionToRender = zonePixelHeight;
                xpositionToRender += t.width;
            }
        }
    }

    public void renderZone() {
        zone.getTileList().forEach(tile -> tile.draw(this.batch));
        neighbours.forEach(neighbour -> neighbour.getTileList().forEach(tile -> {
            tile.draw(this.batch);
        }));
    }

    private void addNeighbour(int index) {
        if (index >= 0 && index < World.getZoneContainer().getZoneList().size()) {
            this.neighbours.add(World.getZoneContainer().getZoneList().get(index));
        }
    }

    private int flattenCoords(int x, int y) {
        if (x < 0 || y < 0) {
            return -1;
        }
        if (x > World.getWorldWidth() - 1 || y > World.getWorldHeight() - 1) {
            return -1;
        }
        return y + (x * World.getWorldHeight());
    }

    public void prepareAllTiles() {
        World.getZoneContainer().getZoneList().forEach(this::prepareZoneTiles);
    }

    public void setZone(Zone zone) {
        this.zone = zone;

        this.neighbours.clear();

        int x = this.zone.getX();
        int y = this.zone.getY();

        this.addNeighbour(flattenCoords(x - 1, y - 1));
        this.addNeighbour(flattenCoords(x - 1, y));
        this.addNeighbour(flattenCoords(x - 1, y + 1));

        this.addNeighbour(flattenCoords(x, y - 1));
        this.addNeighbour(flattenCoords(x, y + 1));

        this.addNeighbour(flattenCoords(x + 1, y - 1));
        this.addNeighbour(flattenCoords(x + 1, y));
        this.addNeighbour(flattenCoords(x + 1, y + 1));
    }

    public Zone getZone() {
        return zone;
    }

    public List<Zone> getNeighbours() {
        return neighbours;
    }
}
