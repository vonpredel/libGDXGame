package com.mygdx.game.Entities.NonStatics.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;
import com.mygdx.game.inventory.Inventory;

public class Player extends Character {

    private int strength;
    private int dexterity;
    private int vitality;
    private int energy;

    public Player(Assets assets) {
        this.texture = assets.manager.get(AssetsConstants.TEMP_PLAYER, Texture.class);
        this.width = Constants.DEFAULT_CHARACTER_WIDTH;
        this.height = Constants.DEFAULT_CHARACTER_HEIGHT;
        this.movementSpeed = Constants.DEFAULT_MOVEMENT_SPEED;
        this.x = 512;
        this.y = 512;
        this.maxHealthPoints = Constants.DEFAULT_MAX_HEALTH_POINTS;
        this.currentHealthPoints = maxHealthPoints;
        this.strength = 1;
        this.dexterity = 1;
        this.vitality = 1;
        this.energy = 1;
    }

    @Override
    public void initializeInventory() {
        this.inventory = new Inventory();
        // TEMP EQUIP
        this.inventory.startingEquipment();
    }

    @Override
    protected void ai() {
        // Do Nothing.
    }

    public void pickUpItems() {
        World.getItemsContainer().getAllItems().forEach(item -> {
            if(item.isDropped()) {
                if(item.x == x && item.y == y) {
                    item.moveToInventory(this.inventory);
                }
            }
        });
    }

    @Override
    protected void die() {
        World.getCameraHandler().zoomIn(50);
        System.out.println("YOU'RE DEAD");
    }
}
