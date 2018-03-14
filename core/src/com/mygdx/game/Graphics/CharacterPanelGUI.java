package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Entities.NonStatics.Characters.Player;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;

public class CharacterPanelGUI extends AbstractGUI {

    private BitmapFont font;

    public CharacterPanelGUI(Player player, Assets assets) {
        this.texture = assets.manager.get(AssetsConstants.DAMAGE);
        this.width = texture.getWidth()*20;
        this.height = texture.getHeight()*20;
        this.player = player;
        font = new BitmapFont();
        this.assets = assets;
    }
}
