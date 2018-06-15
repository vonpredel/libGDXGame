package com.mygdx.game.Zones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Tiles.DirtTile;
import com.mygdx.game.Tiles.GrassDarkTile;
import com.mygdx.game.Tiles.GrassTile;
import com.mygdx.game.Tiles.StairsTile;
import com.mygdx.game.Tiles.StoneTile;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Tiles.WallTile;
import com.mygdx.game.Tiles.WaterDirtTile;
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
    private static final Color WATER_DIRT  = Color.WHITE;
    private static final Color DARK_GRASS  = new Color(0,100,0);

    private static final Color STONE_UP  = new Color(100,50,0);
    private static final Color STONE_DOWN  = new Color(150,50,0);
    private static final Color STONE_CORNEL  = new Color(100,100,0);

    private static final Color STAIRS  = new Color(200,200,0);

    private final Assets assets;
    private List<Tile> tileList;
    private int width,height;

    private static Map<Color, Class<? extends Tile>> TILE_MAP;

    static {
        TILE_MAP = new HashMap<>(10);
        TILE_MAP.put(Color.BLACK, DirtTile.class);
        TILE_MAP.put(Color.GREEN, GrassTile.class);
        TILE_MAP.put(Color.BLUE, WaterTile.class);
        TILE_MAP.put(Color.RED, WallTile.class);
        TILE_MAP.put(Color.WHITE, WaterDirtTile.class);
        TILE_MAP.put(new Color(0,100,0), GrassDarkTile.class);
        TILE_MAP.put(new Color(100,50,0), StoneTile.class);
        TILE_MAP.put(new Color(150,50,0), StoneTile.class);
        TILE_MAP.put(new Color(100,100,0), StoneTile.class);
        TILE_MAP.put(new Color(200,200,0), StairsTile.class);
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

    private String getAssetNameForStairs(Color[][] colors, String defaultAsset, int i, int j) {

        Color colorU = j == height - 1 ? null : colors[i][j + 1];
        Color colorD = j == 0 ? null : colors[i][j - 1];
        Color colorL = i == 0 ? null : colors[i - 1][j];
        Color colorR = i == width - 1 ? null : colors[i + 1][j];

        Color colorUL = i == 0 || j == height - 1 ? null : colors[i - 1][j + 1];
        Color colorUR = i == width - 1 || j == height - 1 ? null : colors[i + 1][j + 1];
        Color colorDL = i == 0 || j == 0 ? null : colors[i - 1][j - 1];
        Color colorDR = i == width - 1 || j == 0 ? null : colors[i + 1][j - 1];

        String assetToRender = defaultAsset;

        if (STONE_UP.equals(colorL)) assetToRender += "UL";
        if (STONE_UP.equals(colorR)) assetToRender += "UR";
        if (STONE_DOWN.equals(colorL)) assetToRender += "DL";
        if (STONE_DOWN.equals(colorR)) assetToRender += "DR";
        if (STAIRS.equals(colorL) && STAIRS.equals(colorR)) {
            if (GRASS.equals(colorU)) assetToRender += "U";
            if (GRASS.equals(colorD)) assetToRender += "D";
        }
        return assetToRender;
    }

    private String getAssetNameForStone(Color[][] colors, String defaultAsset, int i, int j) {
        Color colorU = j == height - 1 ? null : colors[i][j + 1];
        Color colorD = j == 0 ? null : colors[i][j - 1];
        Color colorL = i == 0 ? null : colors[i - 1][j];
        Color colorR = i == width - 1 ? null : colors[i + 1][j];

        Color colorUL = i == 0 || j == height - 1 ? null : colors[i - 1][j + 1];
        Color colorUR = i == width - 1 || j == height - 1 ? null : colors[i + 1][j + 1];
        Color colorDL = i == 0 || j == 0 ? null : colors[i - 1][j - 1];
        Color colorDR = i == width - 1 || j == 0 ? null : colors[i + 1][j - 1];

        String assetToRender = defaultAsset;

        // V2 TEST BEGIN

//        if(GRASS.equals(colorU)) assetToRender += "U";
//        if(GRASS.equals(colorD)) assetToRender += "D";
//        if(GRASS.equals(colorL)) assetToRender += "L";
//        if(GRASS.equals(colorR)) assetToRender += "R";
//
//        if (STONE_UP.equals(colorL) && STONE_UP.equals(colorR)
//                || STONE_UP.equals(colorU) && STONE_UP.equals(colorD)) {
//            assetToRender += "Q";
//        } else if (STONE_UP.equals(colorL) && STONE_UP.equals(colorR)
//                || STONE_UP.equals(colorU) && STONE_UP.equals(colorD)) {
//            assetToRender += "X";
//        }
//
//        if(STONE_CORNEL.equals(colorUL)) {
//            if (STONE_UP.equals(colorL)) assetToRender += "X";
//            else assetToRender += "Q";
//        }
//        else if(STONE_CORNEL.equals(colorUR)) {
//            if (STONE_UP.equals(colorR)) assetToRender += "X";
//            else assetToRender += "Q";
//        }
//        else if(STONE_CORNEL.equals(colorDL)) {
//            if (STONE_UP.equals(colorL)) assetToRender += "X";
//            else assetToRender += "Q";
//        }
//        else if(STONE_CORNEL.equals(colorDR)) {
//            if (STONE_UP.equals(colorR)) assetToRender += "X";
//            else assetToRender += "Q";
//        }



        // V2 TEST END

        if (STONE_CORNEL.equals(colorL) && GRASS.equals(colorU) && STONE_UP.equals(colorR)) assetToRender += "UQL";
        if (STONE_CORNEL.equals(colorL) && GRASS.equals(colorU) && STONE_DOWN.equals(colorR)) assetToRender += "UXL";
        if (STONE_CORNEL.equals(colorL) && GRASS.equals(colorD) && STONE_UP.equals(colorR)) assetToRender += "DQL";
        if (STONE_CORNEL.equals(colorL) && GRASS.equals(colorD) && STONE_DOWN.equals(colorR)) assetToRender += "DXL";

        if (STONE_CORNEL.equals(colorR) && GRASS.equals(colorU) && STONE_UP.equals(colorL)) assetToRender += "UQR";
        if (STONE_CORNEL.equals(colorR) && GRASS.equals(colorU) && STONE_DOWN.equals(colorL)) assetToRender += "UXR";
        if (STONE_CORNEL.equals(colorR) && GRASS.equals(colorD) && STONE_UP.equals(colorL)) assetToRender += "DQR";
        if (STONE_CORNEL.equals(colorR) && GRASS.equals(colorD) && STONE_DOWN.equals(colorL)) assetToRender += "DXR";

        if(STONE_CORNEL.equals(colorU) && GRASS.equals(colorL) && STONE_UP.equals(colorD)) assetToRender += "LQU";
        if(STONE_CORNEL.equals(colorU) && GRASS.equals(colorL) && STONE_DOWN.equals(colorD)) assetToRender += "LXU";
        if(STONE_CORNEL.equals(colorU) && GRASS.equals(colorR) && STONE_UP.equals(colorD)) assetToRender += "RQU";
        if(STONE_CORNEL.equals(colorU) && GRASS.equals(colorR) && STONE_DOWN.equals(colorD)) assetToRender += "RXU";

        if(STONE_CORNEL.equals(colorD) && GRASS.equals(colorL) && STONE_UP.equals(colorU)) assetToRender += "LQD";
        if(STONE_CORNEL.equals(colorD) && GRASS.equals(colorL) && STONE_DOWN.equals(colorU)) assetToRender += "LXD";
        if(STONE_CORNEL.equals(colorD) && GRASS.equals(colorR) && STONE_UP.equals(colorU)) assetToRender += "RQD";
        if(STONE_CORNEL.equals(colorD) && GRASS.equals(colorR) && STONE_DOWN.equals(colorU)) assetToRender += "RXD";

        if (GRASS.equals(colorU) && STONE_UP.equals(colorL) && STONE_UP.equals(colorR) && STONE_DOWN.equals(colorD)) assetToRender += "U";
        if (GRASS.equals(colorD) && STONE_UP.equals(colorL) && STONE_UP.equals(colorR) && STONE_DOWN.equals(colorU)) assetToRender += "D";
        if (GRASS.equals(colorL) && STONE_UP.equals(colorU) && STONE_UP.equals(colorD) && STONE_DOWN.equals(colorR)) assetToRender += "L";
        if (GRASS.equals(colorR) && STONE_UP.equals(colorU) && STONE_UP.equals(colorD) && STONE_DOWN.equals(colorL)) assetToRender += "R";

        if (GRASS.equals(colorU) && STONE_DOWN.equals(colorL) && STONE_DOWN.equals(colorR) && STONE_UP.equals(colorD)) assetToRender += "UX";
        if (GRASS.equals(colorD) && STONE_DOWN.equals(colorL) && STONE_DOWN.equals(colorR) && STONE_UP.equals(colorU)) assetToRender += "DX";
        if (GRASS.equals(colorL) && STONE_DOWN.equals(colorU) && STONE_DOWN.equals(colorD) && STONE_UP.equals(colorR)) assetToRender += "LX";
        if (GRASS.equals(colorR) && STONE_DOWN.equals(colorU) && STONE_DOWN.equals(colorD) && STONE_UP.equals(colorL)) assetToRender += "RX";

        if(STONE_DOWN.equals(colorR) && STONE_DOWN.equals(colorD) && STONE_UP.equals(colorL) && STONE_UP.equals(colorU) && GRASS.equals(colorUL)) assetToRender += "ULm";
        if(STONE_DOWN.equals(colorR) && STONE_DOWN.equals(colorU) && STONE_UP.equals(colorL) && STONE_UP.equals(colorD) && GRASS.equals(colorDL)) assetToRender += "DLm";
        if(STONE_DOWN.equals(colorL) && STONE_DOWN.equals(colorD) && STONE_UP.equals(colorR) && STONE_UP.equals(colorU) && GRASS.equals(colorUR)) assetToRender += "URm";
        if(STONE_DOWN.equals(colorL) && STONE_DOWN.equals(colorU) && STONE_UP.equals(colorR) && STONE_UP.equals(colorD) && GRASS.equals(colorDR)) assetToRender += "DRm";

        if(STONE_DOWN.equals(colorR) && STONE_DOWN.equals(colorD) && STONE_UP.equals(colorL) && STONE_UP.equals(colorU) && GRASS.equals(colorDR)) assetToRender += "DRmQ";
        if(STONE_DOWN.equals(colorR) && STONE_DOWN.equals(colorU) && STONE_UP.equals(colorL) && STONE_UP.equals(colorD) && GRASS.equals(colorUR)) assetToRender += "URmQ";
        if(STONE_DOWN.equals(colorL) && STONE_DOWN.equals(colorD) && STONE_UP.equals(colorR) && STONE_UP.equals(colorU) && GRASS.equals(colorDL)) assetToRender += "DLmQ";
        if(STONE_DOWN.equals(colorL) && STONE_DOWN.equals(colorU) && STONE_UP.equals(colorR) && STONE_UP.equals(colorD) && GRASS.equals(colorUL)) assetToRender += "ULmQ";

        if(STONE_DOWN.equals(colorR) && STONE_DOWN.equals(colorD) && GRASS.equals(colorL) && GRASS.equals(colorU)) assetToRender += "ULX";
        if(STONE_DOWN.equals(colorR) && STONE_DOWN.equals(colorU) && GRASS.equals(colorL) && GRASS.equals(colorD)) assetToRender += "DLX";
        if(STONE_DOWN.equals(colorL) && STONE_DOWN.equals(colorD) && GRASS.equals(colorR) && GRASS.equals(colorU)) assetToRender += "URX";
        if(STONE_DOWN.equals(colorL) && STONE_DOWN.equals(colorU) && GRASS.equals(colorR) && GRASS.equals(colorD)) assetToRender += "DRX";

        if(STONE_UP.equals(colorR) && STONE_UP.equals(colorD) && GRASS.equals(colorL) && GRASS.equals(colorU)) assetToRender += "ULQ";
        if(STONE_UP.equals(colorR) && STONE_UP.equals(colorU) && GRASS.equals(colorL) && GRASS.equals(colorD)) assetToRender += "DLQ";
        if(STONE_UP.equals(colorL) && STONE_UP.equals(colorD) && GRASS.equals(colorR) && GRASS.equals(colorU)) assetToRender += "URQ";
        if(STONE_UP.equals(colorL) && STONE_UP.equals(colorU) && GRASS.equals(colorR) && GRASS.equals(colorD)) assetToRender += "DRQ";

        if(STAIRS.equals(colorL) && GRASS.equals(colorD)) assetToRender += "DX";
        if(STAIRS.equals(colorL) && GRASS.equals(colorU)) assetToRender += "U";
        if(STAIRS.equals(colorR) && GRASS.equals(colorD)) assetToRender += "DX";
        if(STAIRS.equals(colorR) && GRASS.equals(colorU)) assetToRender += "U";

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

                if (color.equals(GRASS)) {
                    assetToRender = AssetsConstants.GRASS_DIR_BETA;
                    assetToRender = randomTile(assetToRender,15);
                } else if (color.equals(DARK_GRASS)) {
                    assetToRender = getAssetName(colors,GRASS, AssetsConstants.DARK_GRASS_DIR_BETA,i,j);
                    if(assetToRender.equals(AssetsConstants.DARK_GRASS_DIR_BETA)) {
                        assetToRender = randomTile(assetToRender,15);
                    }
                } else if (color.equals(STAIRS)) {
                    assetToRender = getAssetNameForStairs(colors, AssetsConstants.STAIRS_DIR,i,j);
                } else if (color.equals(WALL)){
                    assetToRender = AssetsConstants.WALL_DIR;
                } else if (color.equals(STONE_UP) || color.equals(STONE_DOWN) || color.equals(STONE_CORNEL)) {
                    assetToRender = getAssetNameForStone(colors, AssetsConstants.STONE_DIR_BETA,i,j);
                    if(assetToRender.length() == AssetsConstants.STONE_DIR_BETA.length() + 1 ||
                            assetToRender.length() == AssetsConstants.STONE_DIR_BETA.length() + 2) {
                        assetToRender = randomTile(assetToRender,5);
                    }
                } else if (color.equals(WATER)) {
                    assetToRender = getAssetName(colors,GRASS, AssetsConstants.WATER_GRASS_DIR_BETA,i,j);
                    if(assetToRender.length() == AssetsConstants.WATER_GRASS_DIR_BETA.length() + 1) {
                        assetToRender = randomTile(assetToRender,5);
                    }
                    if(assetToRender.equals(AssetsConstants.WATER_GRASS_DIR_BETA)) {
                        assetToRender = randomTile(assetToRender,15);
                    }
                } else if(color.equals(WATER_DIRT)) {
                    assetToRender = getAssetName(colors,DIRT, AssetsConstants.WATER_DIRT_DIR_BETA,i,j);
                    if(assetToRender.length() == AssetsConstants.WATER_DIRT_DIR_BETA.length() + 1) {
                        assetToRender = randomTile(assetToRender,5);
                    }
                    if(assetToRender.equals(AssetsConstants.WATER_DIRT_DIR_BETA)) {
                        assetToRender = randomTile(assetToRender,15);
                    }

                } else if(color.equals(DIRT)) {
                    assetToRender = getAssetName(colors,GRASS, AssetsConstants.DIRT_DIR_BETA,i,j);
                    if(assetToRender.length() == AssetsConstants.DIRT_DIR_BETA.length() + 1) {
                        assetToRender = randomTile(assetToRender,10);
                    }
                    if(assetToRender.equals(AssetsConstants.DIRT_DIR_BETA)) {
                        assetToRender = randomTile(assetToRender,15);
                    }
                }
                final Tile tile = this.createTile(color, assetToRender + ".png");
                if (assetToRender.equals("stairs/stairsU") || assetToRender.equals("stairs/stairsD")) tile.setSolid(false);
                tileList.add(tile);
            }
        }
        return new Zone(tileList, width, height);

    }

    private String randomTile(String assetToRender,int range) {
        switch (MathUtils.random(1, range)) {
            case 5:
                assetToRender += 2;
                break;
            case 10:
                assetToRender += 3;
                break;
            case 15:
                assetToRender += 4;
                break;
            default:
                assetToRender += 1;
                break;
        }
        return assetToRender;
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
