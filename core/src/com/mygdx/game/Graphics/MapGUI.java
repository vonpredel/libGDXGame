package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.World.World;
import com.mygdx.game.Zones.Zone;
import java.util.List;

public class MapGUI extends AbstractGUI {

    private List<Zone> zoneList;
    private int worldWidth;
    private int worldHeight;
    private int zoneWidth;
    private int zoneHeight;

    public MapGUI(Player player, Assets assets) {
        super(player, assets);
        this.texture = assets.manager.get(AssetsConstants.MAP_BACK);
        this.width = (int) (texture.getWidth()*1.5f);
        this.height = (int) (texture.getHeight()*1.5f);
        this.zoneList = World.getZoneContainer().getZoneList();
        this.worldWidth = World.getWorldWidth();
        this.worldHeight = World.getWorldHeight();
        this.zoneWidth = World.getCurrentZoneWidth();
        this.zoneHeight = World.getCurrentZoneHeight();
        // TODO TEMP
        zoneList.get(0).setVisited(true);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (!isEnabled) {
            return;
        }
        for (int i = 0; i < worldWidth; i++) {
            for (int j = 0; j < worldHeight; j++) {
                Zone zone = zoneList.get(j + (i * worldHeight));
                if (!zone.isVisited()) {
                    continue;
                }
                batch.draw(zone.getMapTexture(), (x + 150) + (i * zoneWidth * 1.8f), (y + 150) + (j * zoneHeight * 1.8f),
                        zone.getMapTexture().getWidth() * 1.6f, zone.getMapTexture().getHeight() * 1.6f);
            }
        }
    }
}
