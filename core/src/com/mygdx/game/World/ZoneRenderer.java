package com.mygdx.game.World;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.Zones.Zone;
import java.util.List;

public class ZoneRenderer {

    Zone zone;
    SpriteBatch batch;

    public ZoneRenderer(Zone zone, SpriteBatch batch) {
        this.zone = zone;
        this.batch = batch;
    }

    public void renderZone() {
        List<Tile> tileList = zone.getTileList();
        int width = zone.getWidth();

        int xpositionToRender = 0;
        int ypositionToRender = 0;


        for (Tile t : tileList) {
            t.x = xpositionToRender;
            t.y = ypositionToRender;
            t.draw(batch);
            xpositionToRender += t.width;
            if (xpositionToRender >= width * t.width) {
                xpositionToRender = 0;
                ypositionToRender += t.height;
            }
        }
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
