package com.mygdx.game.Entities.NonStatics.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.inventory.Inventory;

public class Foe extends Character {

    public Foe(Assets assets) {
        this.texture = assets.manager.get(AssetsConstants.TEMP_FOE, Texture.class);
        this.width = texture.getWidth()/4;
        this.height = texture.getHeight()/4;
        this.movementSpeed = Constants.DEFAULT_MOVEMENT_SPEED/5;
        this.x = 1024;
        this.y = 1024;
        this.maxHealthPoints = 20;
        this.currentHealthPoints = maxHealthPoints;
    }

    @Override
    public void initializeInventory() {
        this.inventory = new Inventory();
        // TEST EQ
        this.inventory.startingEquipment();
    }

    @Override
    public void ai() {
        defaultAi();
    }
}
