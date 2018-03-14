package com.mygdx.game.Entities.NonStatics.Creatures;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Items.ItemType;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.World.World;

public class Wolf extends Creature {

    public Wolf(Assets assets) {
        this.texture = assets.manager.get(AssetsConstants.TEMP_PLAYER, Texture.class);
        this.width = Constants.DEFAULT_CHARACTER_WIDTH;
        this.height = Constants.DEFAULT_CHARACTER_HEIGHT;
        this.movementSpeed = Constants.DEFAULT_MOVEMENT_SPEED/3;
        this.x = 2048;
        this.y = 1024;
        this.maxHealthPoints = 40;
        this.currentHealthPoints = maxHealthPoints;

        this.minDamage = 3;
        this.maxDamage = 7;
        this.critChance = 30;
        this.accuracy = 90;
        this.defence = 2;
        this.attackSpeed = 2f;
    }

    @Override
    protected void dropLoot() {
        createItem(World.getItemsManager().create(ItemType.MISCE_MISCE));
    }

    @Override
    protected void ai() {
        defaultAi();
    }
}
