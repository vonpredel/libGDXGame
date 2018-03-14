package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entities.NonStatics.Characters.Player;
import com.mygdx.game.Utils.assets.AssetsConstants;

public class MenuGUI extends AbstractGUI {

    public MenuGUI(Player player) {
        this.texture = new Texture(AssetsConstants.DAMAGE);
        this.x = 0;
        this.y = 0;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.player = player;
    }

}
