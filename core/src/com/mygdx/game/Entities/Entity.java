package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.AssetsConstants;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;
import com.mygdx.game.inventory.Inventory;

public abstract class Entity extends Rectangle {

    protected Texture texture;
    public boolean isDamaged = false;
    public float cleanDamageTimerHelper = 0f;
    public int damageGot = 0;

    protected Inventory inventory;

    public Entity() {
        this.x = Constants.DEFAULT_START_POSITION_X;
        this.y = Constants.DEFAULT_START_POSITION_Y;
        this.height = Constants.DEFAULT_CHARACTER_HEIGHT;
        this.width = Constants.DEFAULT_CHARACTER_WIDTH;
    }

    public void draw(SpriteBatch batch, BitmapFont font) {
        batch.draw(texture, x, y, width, height);
        if (isDamaged) {
            batch.draw(new Texture(AssetsConstants.DAMAGE), x, y, width, height);
            font.setColor(Color.WHITE);
            font.draw(batch, String.valueOf(damageGot), x + width *0.4f, y + height *0.6f);
            cleanDamageTimerHelper += Gdx.graphics.getDeltaTime();
            if (cleanDamageTimerHelper > 0.8f) {
                isDamaged = false;
                cleanDamageTimerHelper = 0f;
            }
        }
    }

    protected void die() {
        World.removeEntity(this);
        dropEquipment();
    }

//    protected void drop() {
//        final List<Item> items = inventory.getItems();
//    }

    protected void dropEquipment() {
        Tile tile = getCurrentTile();
        getInventory().getItems().forEach(i -> i.generateOnMap(tile.x,tile.y));
    }

    protected void dropAllItems() {
        getInventory().getItems().forEach(this::dropItem);
    }

    public void dropItem(Item item) {
        Tile tile = getCurrentTile();
        item.generateOnMap(tile.x,tile.y);
        getInventory().getItems().remove(item);
    }

    public Texture getTexture() {
        return texture;
    }

    public abstract boolean isSolid();

    public Tile getCurrentTile() {
        return World.getTileByPosition(World.getCurrentEntityPosition(this));
    }

    public abstract void update();

    public Inventory getInventory() {
        return inventory;
    }
}
