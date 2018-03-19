package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Utils.BaseContainer;
import com.mygdx.game.Utils.Constants;

public class ItemsContainer extends BaseContainer<Item> {

    @Override
    public void draw(SpriteBatch batch) {
        this.getAllItems().forEach(i -> {
            if (i.isDropped()) {
                batch.draw(i.getTexture(), i.x, i.y, Constants.DEFAULT_ITEM_WIDTH, Constants.DEFAULT_ITEM_HEIGHT);
            }
        });
    }



}
