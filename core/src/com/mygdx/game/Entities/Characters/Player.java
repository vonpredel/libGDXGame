package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Assets;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;

public class Player extends Character {

    public Player(Assets assets) {
        this.texture = assets.manager.get("badlogic.jpg", Texture.class);
        this.width = Constants.DEFAULT_CHARACTER_WIDTH;
        this.height = Constants.DEFAULT_CHARACTER_HEIGHT;
        this.movementSpeed = Constants.DEFAULT_MOVEMENT_SPEED;
        this.attackSpeed= 1f;
        this.x = 512;
        this.y = 512;
        this.currentHealthPoints = 5;
        this.maxHealthPoints = 5;
        this.damage = 1;
    }

    @Override
    public void attackUp() {
        Tile targetAttackTile = World.getTargetMovementTile(this, World.UP);
        if (!isAttacking && !isMoving && World.isTileOccupied(targetAttackTile)) {
            isAttacking = true;
            Character characterFromTile = World.getCharacterFromTile(targetAttackTile);
            characterFromTile.hurt(1);
            characterFromTile.isDamaged = true;
            attackTimeHelper = 0;
        }
    }

    @Override
    public void attackDown() {

    }

    @Override
    public void attackLeft() {

    }

    @Override
    public void attackRight() {

    }
}
