package com.mygdx.game.Entities.Statics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Utils.assets.AssetChopper;

public abstract class Static extends Entity {

    private State state;

    private boolean solid = true;

    private TextureRegion textureToRender;
    private TextureRegion[] animations;

    public abstract void performAction();

    @Override
    public void update() {
        textureToRenderUpdate();
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
