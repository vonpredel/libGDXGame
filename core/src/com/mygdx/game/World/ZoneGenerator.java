package com.mygdx.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.Tiles.GrassTile;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Tiles.WallTile;
import com.mygdx.game.Utils.Assets;
import com.mygdx.game.World.Zones.Zone;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.imageio.ImageIO;

public class ZoneGenerator {

    private final Assets assets;
    private List<Tile> tileList;
    private int width,height;

    private static Map<Color, Class<? extends Tile>> TILE_MAP;

    static {
        TILE_MAP = new HashMap<>(2);
        TILE_MAP.put(Color.BLACK, WallTile.class);
        TILE_MAP.put(Color.GREEN, GrassTile.class);
    }

    public ZoneGenerator(Assets assets) {
        this.assets = assets;
    }

    private Color[][] getIds(BufferedImage image) throws IOException {
        height = image.getHeight();
        width = image.getWidth();

        Color[][] array2D = new Color[width][height];

        for (int xPixel = 0; xPixel < array2D.length; xPixel++) {
            for (int yPixel = 0; yPixel < array2D[xPixel].length; yPixel++) {
                int color = image.getRGB(xPixel, yPixel);
                array2D[xPixel][yPixel] = new Color(color);
            }
        }
        return array2D;
    }

    public Zone generateZone(String bmpFileName) throws IOException {
        final FileHandle classpath = Gdx.files.classpath(bmpFileName);
        BufferedImage image = ImageIO.read(classpath.file().toURI().toURL());
        Color[][] colors = getIds(image);

        tileList = new ArrayList<>(width * height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = colors[i][j];
                final Tile tile = this.createTile(color);
                tileList.add(tile);
//                tile.setTileIndex(tileList.size() - 1);
            }
        }
        return new Zone(tileList, width, height);

    }

    private Tile createTile(Color color) {
        final Class<? extends Tile> tileClass = TILE_MAP.get(color);
        final Constructor<?>[] constructors = tileClass.getConstructors();

        for (Constructor<?> constructor : constructors) {
            final Optional<Class<?>> optional = Arrays.stream(constructor.getParameterTypes())
                    .filter(Assets.class::equals).findAny();
            if (optional.isPresent()) {
                final Object instance;
                try {
                    instance = constructor.newInstance(this.assets);
                    return (Tile) instance;
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<Tile> getTileList() {
        return tileList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
