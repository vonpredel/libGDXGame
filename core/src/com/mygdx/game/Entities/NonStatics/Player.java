package com.mygdx.game.Entities.NonStatics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;
import com.mygdx.game.inventory.Inventory;

public class Player extends NonStatic {

    private boolean isDead = false;

    @Override
    protected void ai() {
        // Do Nothing.
    }

    public void pickUpItems() {
        World.getItemsManager().getContainer().getAllItems().forEach(this::pickUpItem);
    }

    public void pickUpItem(Item item) {
        if ((!item.isDropped()) || (item.x != x || item.y != y)) {
            return;
        }
        final Inventory inventory = this.getInventory();
        if (inventory.getSpecifiedItems(item.getClass()).size() < 15) {
            item.moveToInventory(inventory);
        }
    }

    @Override
    protected void die() {
        World.getCameraHandler().zoomIn(50);
        this.setCurrentHealthPoints((int) 1e6);
        isDead = true;
    }

    @Override
    public void draw(SpriteBatch batch, BitmapFont font) {
        if (!isDead) super.draw(batch, font);
        else batch.draw(new Texture(AssetsConstants.DEAD_PLAYER), x, y, width, height);
    }

    @Override
    public void update() {
        if (!isDead) super.update();
        else World.getCameraHandler().rotateCameraRight();
    }
}
