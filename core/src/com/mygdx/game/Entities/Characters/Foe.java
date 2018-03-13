package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Utils.Assets;
import com.mygdx.game.Utils.AssetsConstants;
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
        this.getInventory().startingEquipment();
    }

    @Override
    public void ai() {
        if(!isMoving) {
            int i = MathUtils.random(1,4);
            switch (i) {
                case 1:
                    moveUp();
                    break;
                case 2:
                    moveDown();
                    break;
                case 3:
                    moveLeft();
                    break;
                case 4:
                    moveRight();
                    break;
            }
        }
    }
}
