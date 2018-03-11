package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Assets;
import com.mygdx.game.Utils.AssetsConstants;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;
import com.mygdx.game.inventory.Inventory;

public class Player extends Character {

    public Player(Assets assets) {
        this.texture = assets.manager.get(AssetsConstants.TEMP_PLAYER, Texture.class);
        this.width = Constants.DEFAULT_CHARACTER_WIDTH;
        this.height = Constants.DEFAULT_CHARACTER_HEIGHT;
        this.movementSpeed = Constants.DEFAULT_MOVEMENT_SPEED;
        this.x = 512;
        this.y = 512;
        this.maxHealthPoints = Constants.DEFAULT_MAX_HEALT_POINTS;
        this.currentHealthPoints = maxHealthPoints;
        this.inventory = new Inventory();
        // TEMP EQUIP
        this.getInventory().startingEquipment();
        this.getInventory().equipWeapon(this.getInventory().getWeapons().get(0));
    }

    @Override
    protected void ai() {

    }
}
