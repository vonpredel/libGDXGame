package com.mygdx.game.Zones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Tiles.DirtTile;
import com.mygdx.game.Tiles.GrassTile;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Tiles.WallTile;
import com.mygdx.game.Tiles.WaterTile;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
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

    private static final Color GRASS = Color.GREEN;
    private static final Color DIRT  = Color.BLACK;
    private static final Color WATER  = Color.BLUE;
    private static final Color WALL  = Color.RED;

    private final Assets assets;
    private List<Tile> tileList;
    private int width,height;

    private static Map<Color, Class<? extends Tile>> TILE_MAP;

    static {
        TILE_MAP = new HashMap<>(4);
        TILE_MAP.put(Color.BLACK, DirtTile.class);
        TILE_MAP.put(Color.GREEN, GrassTile.class);
        TILE_MAP.put(Color.BLUE, WaterTile.class);
        TILE_MAP.put(Color.RED, WallTile.class);
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
                array2D[xPixel][height - yPixel - 1] = new Color(color);
            }
        }
        return array2D;
    }

    private String getAssetName(Color[][] colors, Color type, String defaultAsset, int i, int j) {

        Color colorU = j == height - 1 ? null : colors[i][j + 1];
        Color colorD = j == 0 ? null : colors[i][j - 1];
        Color colorL = i == 0 ? null : colors[i - 1][j];
        Color colorR = i == width - 1 ? null : colors[i + 1][j];

        Color colorUL = i == 0 || j == height - 1 ? null : colors[i - 1][j + 1];
        Color colorUR = i == width - 1 || j == height - 1 ? null : colors[i + 1][j + 1];
        Color colorDL = i == 0 || j == 0 ? null : colors[i - 1][j - 1];
        Color colorDR = i == width - 1 || j == 0 ? null : colors[i + 1][j - 1];

        String assetToRender = defaultAsset;
        if (type.equals(colorU)) assetToRender += "U";
        if (type.equals(colorD)) assetToRender += "D";
        if (type.equals(colorL)) assetToRender += "L";
        if (type.equals(colorR)) assetToRender += "R";
        if (assetToRender.length() > defaultAsset.length() + 2 || assetToRender.equals(defaultAsset + "UD") || assetToRender.equals(defaultAsset + "LR")) {
            assetToRender = defaultAsset;
        }
        if (assetToRender.equals(defaultAsset)) {
            if (type.equals(colorUL)) assetToRender += "ULm";
            else if (type.equals(colorUR)) assetToRender += "URm";
            else if (type.equals(colorDL)) assetToRender += "DLm";
            else if (type.equals(colorDR)) assetToRender += "DRm";
        }
        return assetToRender;
    }

    public Zone generateZone(String bmpFileName) throws IOException {
        final FileHandle classpath = Gdx.files.classpath(bmpFileName);
        BufferedImage image = ImageIO.read(classpath.file().toURI().toURL());
        Color[][] colors = getIds(image);

        tileList = new ArrayList<>(width * height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = colors[i][j];
                String assetToRender = "";

                if (color.equals(DIRT)) {
                    assetToRender = AssetsConstants.DIRT_DIR;
                } else if (color.equals(WALL)){
                    assetToRender = AssetsConstants.WALL_DIR;
                } else if (color.equals(WATER)) {
                    assetToRender = getAssetName(colors,GRASS, AssetsConstants.WATER_DIR,i,j);
                } else if(color.equals(GRASS)) {
                    assetToRender = getAssetName(colors,DIRT, AssetsConstants.GRASS_DIR,i,j);
                    if(assetToRender.equals(AssetsConstants.GRASS_DIR)) {
                        assetToRender += MathUtils.random(1,3);
                    }
                }
                final Tile tile = this.createTile(color, assetToRender + ".png");
                tileList.add(tile);
            }
        }
        return new Zone(tileList, width, height);

    }

    private Tile createTile(Color color, String fileName) {
        final Class<? extends Tile> tileClass = TILE_MAP.get(color);
        final Constructor<?>[] constructors = tileClass.getConstructors();

        for (Constructor<?> constructor : constructors) {
            final Optional<Class<?>> optional = Arrays.stream(constructor.getParameterTypes())
                    .filter(Assets.class::equals).findAny();
            if (optional.isPresent()) {
                final Object instance;
                try {
                    instance = constructor.newInstance(this.assets, fileName);
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
