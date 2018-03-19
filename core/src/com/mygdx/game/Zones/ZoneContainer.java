package com.mygdx.game.Zones;

import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZoneContainer {

    private static final String [] assetsConstantsPaths = {AssetsConstants.MAP1,AssetsConstants.MAP2
            ,AssetsConstants.MAP3, AssetsConstants.MAP4,AssetsConstants.MAP5
        };

    private List<Zone> zoneList;
    private Map<String, Zone> zoneMap;
    private ZoneGenerator zoneGenerator;

    public ZoneContainer(Assets assets) {
        zoneList = new ArrayList<>();
        zoneMap = new HashMap<>();
        zoneGenerator = new ZoneGenerator(assets);

        generateZones();
    }

    private void generateZones() {
        for (String assetsConstantsPath : assetsConstantsPaths) {
            try {
                zoneList.add(zoneGenerator.generateZone(assetsConstantsPath));
                zoneMap.put(assetsConstantsPath,zoneGenerator.generateZone(assetsConstantsPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Zone> getZoneList() {
        return zoneList;
    }

    public Map<String, Zone> getZoneMap() {
        return zoneMap;
    }
}
