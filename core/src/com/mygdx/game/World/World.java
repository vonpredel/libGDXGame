package com.mygdx.game.World;

import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Constants;
import java.util.List;

public class World {

    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final int EAST = 4;

    public static final int UP = NORTH;
    public static final int DOWN = SOUTH;
    public static final int LEFT = WEST;
    public static final int RIGHT = EAST;

    private static List<Entity> entityList;
    private static List<Tile> tileList;
    private static int worldWidth;
    private static int worldHeight;

    private World() {

    }

    public static void init(List<Entity> entityList, List<Tile> tileList, int worldWidth, int worldHeight) {
        World.entityList = entityList;
        World.tileList = tileList;
        World.worldWidth = worldWidth;
        World.worldHeight = worldHeight;
    }

    public static Tile getTileByPosition(int position) {
        return tileList.get(position);
    }

    public static int getCurrentEntityPosition(Entity entity) {
        float xPosition = entity.x / Constants.DEFAULT_TILE_WIDTH;
        float yPosition = entity.y / Constants.DEFAULT_TILE_HEIGHT;
        float position = xPosition + (yPosition * worldWidth);
        return (int) position;
    }

    public static boolean isAbleToGo(Entity entity, int direction) {
        Tile tile;
        int currentEntityPosition = getCurrentEntityPosition(entity);
        switch (direction) {
            case UP:
                tile = getTileByPosition(currentEntityPosition + worldHeight);
                break;
            case DOWN:
                tile = getTileByPosition(currentEntityPosition - worldHeight);
                break;
            case LEFT:
                tile = getTileByPosition(currentEntityPosition - 1);
                break;
            case RIGHT:
                tile = getTileByPosition(currentEntityPosition + 1);
                break;
            default:
                return true;

        }
        return !tile.isSolid();
    }

    public static void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public static List<Entity> getEntityList() {
        return entityList;
    }

    public static List<Tile> getTileList() {
        return tileList;
    }

    public static int getWorldWidth() {
        return worldWidth;
    }

    public static int getWorldHeight() {
        return worldHeight;
    }
}
