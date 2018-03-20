package com.mygdx.game.Zones;

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
}
