package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Utils.BaseContainer;

public class EntitiesContainer extends BaseContainer<Entity> {

    private final BitmapFont bitmapFont;

    public EntitiesContainer(BitmapFont bitmapFont) {
        this.bitmapFont = bitmapFont;
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.getAllItems().forEach(e -> e.draw(batch, this.bitmapFont));
    }

}
