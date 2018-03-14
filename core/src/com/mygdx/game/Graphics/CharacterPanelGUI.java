package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entities.NonStatics.Characters.Player;
import com.mygdx.game.Utils.assets.AssetsConstants;

public class CharacterPanelGUI extends AbstractGUI {

    public CharacterPanelGUI(Player player) {
        this.texture = new Texture(AssetsConstants.DAMAGE);
        this.x = 0;
        this.y = 0;
        this.width = texture.getWidth()*20;
        this.height = texture.getHeight()*20;
        this.player = player;
    }
}
