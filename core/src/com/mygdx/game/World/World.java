package com.mygdx.game.World;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.EntitiesContainer;
import com.mygdx.game.Entities.EntitiesManager;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Entities.Statics.Static;
import com.mygdx.game.Items.ItemsManager;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.CameraHandler;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Zones.Zone;
import com.mygdx.game.Zones.ZoneContainer;
import com.mygdx.game.Zones.ZoneRenderer;
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

    private static Zone currentZone;
    private static List<Tile> tileList;
    private static ZoneRenderer zoneRenderer;
    private static ZoneContainer zoneContainer;
    private static int worldWidth;
    private static int worldHeight;
    private static SpriteBatch batch;
    private static BitmapFont font;
    private static Assets assets;
    private static CameraHandler cameraHandler;
    private static ItemsManager itemsManager;
    private static EntitiesManager entitiesManager;
    private static EntitiesContainer entitiesContainer;
    private static Player player;

    private World() {

    }

    public static void init(ZoneRenderer zoneRenderer,ZoneContainer zoneContainer, SpriteBatch batch,
                            BitmapFont font, Assets assets, ItemsManager itemsManager,
                            CameraHandler cameraHandler, EntitiesManager entitiesManager, Player player) {
        World.batch = batch;
        World.zoneRenderer = zoneRenderer;
        World.zoneContainer = zoneContainer;
        World.currentZone = zoneRenderer.getZone();
        World.tileList = currentZone.getTileList();
        World.worldWidth = currentZone.getWidth();
        World.worldHeight = currentZone.getHeight();
        World.font = font;
        World.assets = assets;
        World.itemsManager = itemsManager;
        World.cameraHandler = cameraHandler;
        World.entitiesManager = entitiesManager;
        World.entitiesContainer = entitiesManager.getContainer();
        World.player = player;
    }

    public static Tile getTileByPosition(int position) {
        return tileList.get(position);
    }

    public static int getCurrentEntityPosition(Entity entity) {
        float xPosition = Math.round(entity.x / Constants.DEFAULT_TILE_WIDTH);
        float yPosition = Math.round(entity.y / Constants.DEFAULT_TILE_HEIGHT);
        float position = yPosition + (xPosition * worldHeight);
        return (int) position;
    }

    public static Tile getTargetDirectionTile(Entity entity, int direction) {
        Tile tile;
        int currentEntityPosition = getCurrentEntityPosition(entity);
        switch (direction) {
            case UP:
                tile = getTileByPosition(currentEntityPosition + 1);
                break;
            case DOWN:
                tile = getTileByPosition(currentEntityPosition - 1);
                break;
            case LEFT:
                tile = getTileByPosition(currentEntityPosition - worldWidth);
                break;
            case RIGHT:
                tile = getTileByPosition(currentEntityPosition + worldHeight);
                break;
            default:
                return null;
        }
        return tile;
    }

    public static boolean isTileOccupied(Tile tile) {
        for (Entity e : entitiesContainer.getAllItems()) {
            if (e.getCurrentTile().equals(tile)) {
                return true;
            }
        }
        return false;
    }

    public static NonStatic getNonStaticFromTile(Tile tile) {
        if (isTileOccupied(tile)) {
            for (Entity e : entitiesContainer.getAllItems()) {
                if (e instanceof NonStatic && e.getCurrentTile().equals(tile)) {
                    return (NonStatic) e;
                }
            }
        }
        return null;
    }

    public static Static getStaticFromTile(Tile tile) {
        if (isTileOccupied(tile)) {
            for (Entity e : entitiesContainer.getAllItems()) {
                if (e instanceof Static && e.getCurrentTile().equals(tile)) {
                    return (Static) e;
                }
            }
        }
        return null;
    }

    public static boolean isAbleToGo(Entity entity, int direction) {
        Tile tile = World.getTargetDirectionTile(entity, direction);
        return tile != null && !tile.isSolid() && !isTileOccupied(tile);
    }

    public static void addEntity(Entity entity) {
        entitiesContainer.addItem(entity);
    }

    public static void removeEntity(Entity entity) {
        entitiesContainer.removeItem(entity);
    }

    public static List<Entity> getEntityList() {
        return entitiesContainer.getAllItems();
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

    public static ItemsManager getItemsManager() {
        return itemsManager;
    }

    public static CameraHandler getCameraHandler() {
        return cameraHandler;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Zone getCurrentZone() {
        return currentZone;
    }

    public static ZoneRenderer getZoneRenderer() {
        return zoneRenderer;
    }

    public static ZoneContainer getZoneContainer() {
        return zoneContainer;
    }

    public static void setCurrentZone(Zone currentZone) {
        World.zoneRenderer.setZone(currentZone);
        World.currentZone = currentZone;
        World.tileList = currentZone.getTileList();
        World.worldWidth = currentZone.getWidth();
        World.worldHeight = currentZone.getHeight();
    }
}
