package com.mygdx.game.World;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Characters.Character;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Assets;
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
    private static SpriteBatch batch;
    private static BitmapFont font;
    private static Assets assets;

    private World() {

    }

    public static void init(List<Entity> entityList, List<Tile> tileList, int worldWidth, int worldHeight, SpriteBatch batch, BitmapFont font, Assets assets) {
        World.batch = batch;
        World.entityList = entityList;
        World.tileList = tileList;
        World.worldWidth = worldWidth;
        World.worldHeight = worldHeight;
        World.font = font;
        World.assets = assets;
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

    public static Tile getTargetDirectionTile(Entity entity, int direction) {
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
                return null;
        }
        return tile;
    }

    public static boolean isTileOccupied(Tile tile) {
        for (Entity e : entityList) {
            if (e.getCurrentTile().equals(tile)) {
                return true;
            }
        }
        return false;
    }

    public static Character getCharacterFromTile(Tile tile) {
        if (isTileOccupied(tile)) {
            for (Entity e : entityList) {
                if (e.getCurrentTile().equals(tile)) {
                    return (Character) e;
                }
            }
        }
        return null;
    }

    public static boolean isAbleToGo(Entity entity, int direction) {
        Tile tile = World.getTargetDirectionTile(entity, direction);
        return tile != null && !tile.isSolid() &&!isTileOccupied(tile);
    }

    public static void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public static void removeEntity(Entity entity) {
        entityList.remove(entity);
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

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static BitmapFont getFont() {
        return font;
    }

    public static Assets getAssets() {
        return assets;
    }
}
