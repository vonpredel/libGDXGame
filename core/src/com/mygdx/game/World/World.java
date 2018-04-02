package com.mygdx.game.World;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler;
import com.mygdx.game.Entities.EntitiesContainer;
import com.mygdx.game.Entities.EntitiesManager;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Entities.Statics.Static;
import com.mygdx.game.Items.ItemsContainer;
import com.mygdx.game.Items.ItemsManager;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.CameraHandler;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Zones.Zone;
import com.mygdx.game.Zones.ZoneContainer;
import com.mygdx.game.Zones.ZoneRenderer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private static int currentZoneWidth;
    private static int currentZoneHeight;
    private static int worldWidth;
    private static int worldHeight;
    private static SpriteBatch batch;
    private static Assets assets;
    private static CameraHandler cameraHandler;
    private static ItemsManager itemsManager;
    private static ItemsContainer itemsContainer;
    private static EntitiesManager entitiesManager;
    private static EntitiesContainer entitiesContainer;
    private static ControlsAndGUIsHandler controlsAndGUIsHandler;
    private static Player player;

    private World() {

    }

    public static void init(SpriteBatch batch,
                            Assets assets, ItemsManager itemsManager,
                            EntitiesManager entitiesManager) {
        World.batch = batch;
        World.zoneRenderer = new ZoneRenderer(batch);
        World.zoneContainer = new ZoneContainer();
        World.assets = assets;
        World.cameraHandler = new CameraHandler(batch);
        World.itemsManager = itemsManager;
        World.itemsContainer = itemsManager.getContainer();
        World.entitiesManager = entitiesManager;
        World.entitiesContainer = entitiesManager.getContainer();
        World.controlsAndGUIsHandler = new ControlsAndGUIsHandler(assets);

        tileList = new ArrayList<>();

        World.loadExternals();
    }

    private static void loadExternals() {
        World.itemsManager.loadDefinitions();
        World.entitiesManager.loadDefinitions();
    }

    public static void configureCameraAndGUI() {
        World.setPlayer((Player) World.getEntitiesContainer().getAllItems().get(0));
        World.getCameraHandler().focusOn(World.getPlayer());
        World.getControlsAndGUIsHandler().initGUIs(World.getPlayer());
    }

    public static void sortTilesList() {
        World.getTileList().sort(Comparator.comparing(Tile::getX).thenComparing(Tile::getY));
    }

    public static void setPlayer(Player player) {
        World.player = player;
    }

    public static Tile getTileByPosition(int position) {
        return tileList.get(position);
    }

    public static int getCurrentEntityPosition(Entity entity) {
        float xPosition = Math.round(entity.x / Constants.DEFAULT_TILE_WIDTH);
        float yPosition = Math.round(entity.y / Constants.DEFAULT_TILE_HEIGHT);
        float position = yPosition + (xPosition * currentZoneHeight*worldHeight);
        return (int) position;
    }

    public static Tile getSingleTargetDirectionTile(Entity entity, int direction) {
        return getTargetDirectionTiles(entity,direction,1).get(0);
    }

    public static List<Tile> getTargetDirectionTiles(Entity entity, int direction, int range) {
        List<Tile> tiles = new ArrayList<>(range);
        int currentEntityPosition = getCurrentEntityPosition(entity);
        try {
            switch (direction) {
                case UP:
                    for (int i = 1; i <= range; i++) tiles.add(getTileByPosition(currentEntityPosition + i));
                    break;
                case DOWN:
                    for (int i = 1; i <= range; i++) tiles.add(getTileByPosition(currentEntityPosition - i));
                    break;
                case LEFT:
                    for (int i = 1; i <= range; i++)
                        tiles.add(getTileByPosition(currentEntityPosition - (i * currentZoneWidth * worldWidth)));
                    break;
                case RIGHT:
                    for (int i = 1; i <= range; i++)
                        tiles.add(getTileByPosition(currentEntityPosition + (i * currentZoneWidth * worldWidth)));
                    break;
                default:
                    return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of bounds !! Skipped adding tile to tileList");
        }
        return tiles;
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

    public static List<NonStatic> getTargetsFromDirection(NonStatic nonStatic, int direction) {
        return World.getTargetDirectionTiles(nonStatic, direction, nonStatic.getRange())
                .stream().filter(World::isTileOccupied).map(World::getNonStaticFromTile)
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static boolean isAbleToGo(Entity entity, int direction) {
        Tile tile = World.getSingleTargetDirectionTile(entity, direction);
        return tile != null && !tile.isSolid() && !isTileOccupied(tile);
    }

    public static void updateCurrentZone() {
        final Zone currentZone = zoneContainer.getCurrentPlayerZone();
        if (!currentZone.equals(World.currentZone)) {
            setCurrentZone(currentZone);
        }
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static Assets getAssets() {
        return assets;
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

    public static ItemsManager getItemsManager() {
        return itemsManager;
    }

    public static ItemsContainer getItemsContainer() {
        return itemsContainer;
    }

    public static ZoneRenderer getZoneRenderer() {
        return zoneRenderer;
    }

    public static ZoneContainer getZoneContainer() {
        return zoneContainer;
    }

    public static EntitiesManager getEntitiesManager() {
        return entitiesManager;
    }

    public static EntitiesContainer getEntitiesContainer() {
        return entitiesContainer;
    }

    public static ControlsAndGUIsHandler getControlsAndGUIsHandler() {
        return controlsAndGUIsHandler;
    }

    public static int getCurrentZoneWidth() {
        return currentZoneWidth;
    }

    public static int getCurrentZoneHeight() {
        return currentZoneHeight;
    }

    public static int getWorldWidth() {
        return worldWidth;
    }

    public static int getWorldHeight() {
        return worldHeight;
    }

    public static void setCurrentZone(Zone currentZone) {
        World.zoneRenderer.setZone(currentZone);
        World.currentZone = currentZone;
        World.currentZoneWidth = currentZone.getWidth();
        World.currentZoneHeight = currentZone.getHeight();
    }

    public static void setWorldDimensions(int w, int h) {
        World.worldWidth = w;
        World.worldHeight = h;
    }

    public static List<Tile> getTileList() {
        return tileList;
    }
}
