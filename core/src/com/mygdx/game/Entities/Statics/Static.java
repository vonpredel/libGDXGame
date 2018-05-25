package com.mygdx.game.Entities.Statics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.assets.AssetChopper;
import com.mygdx.game.World.World;
import java.util.List;

public abstract class Static extends Entity {

    private boolean isPlayerNearby = false;

    private State state;

    private boolean solid = true;

    private TextureRegion textureToRender;
    private TextureRegion[] animations;

    public abstract void performAction();

    @Override
    public void update() {
        hotKeyUpdate();
        textureToRenderUpdate();
    }

    private void hotKeyUpdate() {
        if (this instanceof Fountain || this instanceof Chest) {
            if (state == State.USED || state == State.OPEN) {
                isPlayerNearby = false;
                return;
            }
        }
        final List<Tile> nearbyTilesCross = World.getNearbyTilesCross(1, this);
        final long count = World.getNonStaticsFromTiles(nearbyTilesCross)
                .values().stream()
                .filter(ns -> ns instanceof Player).count();
        if (count==0) {
            isPlayerNearby = false;
            return;
        }
        isPlayerNearby = true;
    }

    private void textureToRenderUpdate() {
        if (state == State.UNUSED || state ==  State.CLOSED) {
            textureToRender = animations[0];
        } else if (state == State.USED || state ==  State.OPEN){
            textureToRender = animations[1];
        }
    }

    @Override
    public boolean isSolid() {
        return solid;
    }

    @Override
    public void draw(SpriteBatch batch, BitmapFont font) {
        batch.draw(textureToRender, x, y, width, height);
        if (isPlayerNearby) {
            font.draw(batch,"E",x+ width/2,y + height/2);
        }
    }

    @Override
    public void setTexture(Texture texture) {
        super.setTexture(texture);
        animations = AssetChopper.crop(texture);
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    protected enum State {
        OPEN,CLOSED,USED,UNUSED
    }
}
