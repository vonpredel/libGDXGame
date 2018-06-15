package com.mygdx.game.Tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.Utils.assets.AssetsConstants;
import java.awt.Color;

public abstract class Tile extends Rectangle {

    protected Texture texture;
    protected Color id;
    private boolean solid;

    private boolean isHitted = false;
    private float cleanHittedTimerHelper = 0f;

    public Tile() {
        this.x = Constants.DEFAULT_START_POSITION_X;
        this.y = Constants.DEFAULT_START_POSITION_Y;
        this.height = Constants.DEFAULT_TILE_HEIGHT;
        this.width = Constants.DEFAULT_TILE_WIDTH;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
        if (isHitted) {
            batch.draw(new Texture(AssetsConstants.HITTED), x, y, width, height);
        }
    }

    public void update() {
        cleanDamageUpdate();
    }

    private void cleanDamageUpdate() {
        if (isHitted) {
            cleanHittedTimerHelper += Gdx.graphics.getDeltaTime();
            if (cleanHittedTimerHelper > 0.2f) {
                isHitted = false;
                cleanHittedTimerHelper = 0f;
            }
        }
    }

    public void setHitted() {
        isHitted = true;
    }

    public Texture getTexture() {
        return texture;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
}
