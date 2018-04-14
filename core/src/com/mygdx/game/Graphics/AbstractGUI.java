package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Utils.assets.Assets;

public abstract class AbstractGUI {

    protected Texture texture;

    protected float x,y;

    protected int width,height;
    public boolean isEnabled;
    protected Player player;

    protected Assets assets;

    public AbstractGUI(Player player, Assets assets) {
        this.player = player;
        this.assets = assets;
    }

    public void draw(SpriteBatch batch) {
        if(isEnabled) {
            batch.draw(texture, x, y, width, height);
        }
    }

    public void update() {
        this.x = player.x - width / 2 + player.width / 2;
        this.y = player.y - height / 2 + player.height / 2;
    }

}
