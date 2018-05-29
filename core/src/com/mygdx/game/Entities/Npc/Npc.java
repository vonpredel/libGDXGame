package com.mygdx.game.Entities.Npc;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;
import java.util.List;

public abstract class Npc extends Entity {

    private boolean isPlayerNearby = false;

    public abstract void performAction();

    private void hotKeyUpdate() {
        final List<Tile> nearbyTilesCross = World.getNearbyTilesCross(1, this);
        final long count = World.getNonStaticsFromTiles(nearbyTilesCross)
                .values().stream()
                .filter(ns -> ns instanceof Player).count();
        if (count==0) {
            isPlayerNearby = false;
            return;
        }
        isPlayerNearby = true;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public void update() {
        hotKeyUpdate();
    }

    @Override
    public void draw(SpriteBatch batch, BitmapFont font) {
        super.draw(batch, font);
        if (isPlayerNearby) {
            font.draw(batch,"F",x+ width/2,y + height/2);
        }
    }
}
