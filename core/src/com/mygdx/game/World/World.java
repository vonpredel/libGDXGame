package com.mygdx.game.World;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Constants;
import java.util.List;

public class World {

    static List<Entity> entityList;
    static List<Tile> tileList;
    static int worldWidth;
    static int worldHeight;

    public World(List<Entity> entityList, List<Tile> tileList, int worldWidth , int worldHeight) {
        this.entityList = entityList;
        this.tileList = tileList;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    public static List<Tile> getFourNearbyTiles(Entity entity) {
        float x = entity.x;
        float y = entity.y;
        Tile tileByXandY = getTileByXandY(x, y);
        return null;
    }

    public static List<Entity> getEntityList() {
        return entityList;
    }

    public static List<Tile> getTileList() {
        return tileList;
    }

    public static Tile getTileByXandY(float x, float y) {
        float xPosition = x / Constants.DEFAULT_CHARACTER_WIDTH;
        float yPosition = y / Constants.DEFAULT_CHARACTER_HEIGHT;
        float position = xPosition + (yPosition*worldHeight);
        Tile tile = tileList.get((int) position);
        tile.setTexture(new Texture("sciana.png"));
        return tileList.get((int) position);
    }

    public static void addEntity(Entity entity) {
        entityList.add(entity);
    }
}
