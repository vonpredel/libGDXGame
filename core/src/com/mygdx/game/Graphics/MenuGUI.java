package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Entities.NonStatics.Characters.Player;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;

public class MenuGUI extends AbstractGUI {

    private BitmapFont font;

    public MenuGUI(Player player, Assets assets) {
        this.texture = assets.manager.get(AssetsConstants.DAMAGE);
        this.x = 0;
        this.y = 0;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.player = player;
        font = new BitmapFont();
        this.assets = assets;
    }

}
