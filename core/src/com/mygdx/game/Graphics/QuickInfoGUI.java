package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entities.Characters.Player;
import com.mygdx.game.Utils.AssetsConstants;
import com.mygdx.game.Utils.Constants;

public class QuickInfoGUI extends AbstractGUI {

    public QuickInfoGUI(Player player) {
        this.texture = new Texture(AssetsConstants.DAMAGE);
        this.x = 0;
        this.y = 0;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.player = player;
    }

    @Override
    public void update() {
        this.x = player.x - Constants.DEFAULT_RESOLUTION_WIDTH/2;
        this.y = player.y + Constants.DEFAULT_RESOLUTION_HEIGHT/2;
    }
}
