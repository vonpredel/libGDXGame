package com.mygdx.game.Zones;

import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WorldGenerator {

    private RouteGenerator routeGenerator;
    private ZoneGenerator zoneGenerator;

    public WorldGenerator() {
        routeGenerator = new RouteGenerator();
        zoneGenerator = new ZoneGenerator(World.getAssets());
    }

    public void generate(int width, int height) {
        final RouteGenerator.Route[][] routes = routeGenerator.generateRoute(width, height);
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[0].length; j++) {
                final RouteGenerator.Route route = routes[i][height - 1 - j];
                String textureName = "zones/";
                if (route.UP) {
                    textureName += "U";
                }
                if (route.DOWN) {
                    textureName += "D";
                }
                if (route.LEFT) {
                    textureName += "L";
                }
                if (route.RIGHT) {
                    textureName += "R";
                }
                textureName += ".bmp";

                try {
                    final Zone zone = zoneGenerator.generateZone(textureName);
                    zone.setTexture(World.getAssets().manager.get(textureName));
                    World.getZoneContainer().getZoneList().add(zone);
                    World.getTileList().addAll(zone.getTileList());
                    zone.setIndex(World.getZoneContainer().getZoneList().size() -1);
                    zone.setCoordinates(i,j);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
