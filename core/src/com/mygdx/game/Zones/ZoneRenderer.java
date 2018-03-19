package com.mygdx.game.Zones;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tiles.Tile;
import java.util.List;

public class ZoneRenderer {

    private Zone zone;
    private SpriteBatch batch;

    public ZoneRenderer(SpriteBatch batch) {
        this.batch = batch;
    }

    private void prepareZoneTiles() {
        List<Tile> tileList = zone.getTileList();
        int heigth = zone.getHeight();

        int xpositionToRender = 0;
        int ypositionToRender = 0;


        for (Tile t : tileList) {
            t.x = xpositionToRender;
            t.y = ypositionToRender;
            ypositionToRender += t.height;
            if (ypositionToRender >= heigth * t.height) {
                ypositionToRender = 0;
                xpositionToRender += t.width;
            }
        }
    }

    public void renderZone() {
        zone.getTileList().forEach(tile -> tile.draw(this.batch));
    }

    public void setZone(Zone zone) {
        this.zone = zone;
        this.prepareZoneTiles();
    }

    public Zone getZone() {
        return zone;
    }
}
