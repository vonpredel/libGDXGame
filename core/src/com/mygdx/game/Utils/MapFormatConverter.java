package com.mygdx.game.Utils;

import com.mygdx.game.Utils.textFile.TextFile;
import com.mygdx.game.World.World;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class MapFormatConverter {

    private static final Color GRASS = Color.GREEN;
    private static final Color DIRT  = Color.BLACK;
    private static final Color WATER  = Color.BLUE;
    private static final Color WALL  = Color.RED;
    private static final Color WATERDIRT  = Color.WHITE;
    private static final Color DARKGRASS  = new Color(0,100,0);
    private static final Color UP  = new Color(100,50,0);
    private static final Color DOWN  = new Color(150,50,0);
    private static final Color CORNEL  = new Color(100,100,0);
    private static final Color STAIRS  = new Color(200,200,0);

    private MapFormatConverter() {

    }

    public static void loadRawFiles() {
        File file = new File("zonesToPrep/");
        final File[] files = file.listFiles();
        for (File f : files) {
            String sourceName = f.getName();
            String outputName = sourceName.replace("csv","bmp");
            initConvert(sourceName,outputName);
        }
    }

    private static void initConvert(String sourceFileName, String descFileName) {
        TextFile textFile = World.getAssets().manager.get("zonesToPrep/" + sourceFileName);
        convertAndSave(textFile, descFileName);

    }

    private static void convertAndSave(TextFile textFile, String nameToSave) {
        Color[][] color = new Color[50][50];
        final String[] lines = getLines(textFile);
        for (int i = 0; i < lines.length; i++) {
            final String[] values = getValues(lines[i]);
            for (int j = 0; j < values.length; j++) {
                switch (values[j]) {
                    case "grass":
                        color[i][j] = GRASS;
                        break;
                    case "dirt":
                        color[i][j] = DIRT;
                        break;
                    case "up":
                        color[i][j] = UP;
                        break;
                    case "down":
                        color[i][j] = DOWN;
                        break;
                    case "cornel":
                        color[i][j] = CORNEL;
                        break;
                    case "stairs":
                        color[i][j] = STAIRS;
                        break;
                    case "water":
                        color[i][j] = WATER;
                        break;
                    case "waterDirt":
                        color[i][j] = WATERDIRT;
                        break;
                    case "darkGrass":
                        color[i][j] = DARKGRASS;
                        break;
                    case "wall":
                        color[i][j] = WALL;
                        break;
                }
            }
        }
        BufferedImage img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < 50; x++){
            for(int y = 0; y < 50; y++){
                final int rgb = color[x][y].getRGB();
                img.setRGB(y, x, rgb);
            }
        }
        File output = new File("zones/" + nameToSave);
        try {
            ImageIO.write(img, "bmp", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] getLines(final TextFile textFile) {
        return textFile.getContent().split("\\n");
    }

    public static String[] getValues(final String line) {
        return line.split(",");
    }
}
