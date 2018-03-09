package com.mygdx.game.Tiles;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Assets;
import java.awt.Color;

public class WallTile extends Tile {

    public WallTile(Assets assets) {
        this.texture = assets.manager.get("sciana.png", Texture.class);
        this.id = Color.RED;
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
