package com.mygdx.game.Tiles;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Assets;
import java.awt.Color;

public class WallTile extends Tile {

    public WallTile(Assets assets, String fileName) {
        this.texture = assets.manager.get(fileName, Texture.class);
        this.id = Color.RED;
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
