package com.mygdx.game.Tiles;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.assets.Assets;
import java.awt.Color;

public class GrassDarkTile extends Tile {

    public GrassDarkTile(Assets assets, String fileName) {
        this.texture = assets.manager.get(fileName, Texture.class);
        this.id = new Color(0,100,0);
        setSolid(false);
    }
}
