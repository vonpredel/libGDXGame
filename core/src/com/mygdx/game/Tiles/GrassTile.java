package com.mygdx.game.Tiles;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Assets;
import java.awt.Color;

public class GrassTile extends Tile {

    public GrassTile(Assets assets) {
        this.texture = assets.manager.get("trawa.png", Texture.class);
        this.id = Color.GREEN;
    }

}
