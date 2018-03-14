package com.mygdx.game.Tiles;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.assets.Assets;
import java.awt.Color;

public class WaterTile extends Tile {

    public WaterTile(Assets assets, String fileName) {
        this.texture = assets.manager.get(fileName, Texture.class);
        this.id = Color.BLUE;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
