package com.mygdx.game.Zones;

import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.List;

public class ZoneContainer {

    private List<Zone> zoneList;

    public ZoneContainer() {
        zoneList = new ArrayList<>();
    }

    public List<Zone> getZoneList() {
        return zoneList;
    }

    public Zone getCurrentZone() {
        final int currentEntityPosition = World.getCurrentEntityPosition(World.getPlayer());
        final int zoneHeight = World.getCurrentZoneHeight();
        final int zoneWidth = World.getCurrentZoneWidth();
        final int worldWidth = World.getWorldWidth();

        final int yPosition = (currentEntityPosition % (zoneHeight * worldWidth));
        final int xSize = worldWidth * zoneWidth * zoneHeight;

        int y = yPosition / zoneHeight;
        int x = (currentEntityPosition - yPosition) / xSize;

        Zone zone = null;
        for (Zone z : zoneList) {
            if (x == z.getX() && y == z.getY()) {
                zone = z;
            }
        }
        return zone;
    }
}
