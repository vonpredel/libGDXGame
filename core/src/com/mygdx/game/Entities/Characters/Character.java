package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.MathUtils;
import com.mygdx.game.World.World;
import com.mygdx.game.inventory.Inventory;

public abstract class Character extends Entity {

    protected int maxHealthPoints, currentHealthPoints;
    protected boolean isMoving;
    protected boolean isAttacking;
    protected float movementSpeed;
    protected float attackSpeed;
    protected int damage;

    protected float attackTimeHelper = 0;

    protected Tile destination;
    private float lastX,lastY;

    @Override
    public boolean isSolid() {
        return true;
    }

    public void moveUp() {
        if (!isMoving && World.isAbleToGo(this, World.UP)) {
            this.isMoving = true;
            moveInit(World.UP);
        }
    }

    public void moveLeft() {
        if (!isMoving && World.isAbleToGo(this, World.LEFT)) {
            this.isMoving = true;
            moveInit(World.LEFT);
        }
    }

    public void moveDown() {
        if (!isMoving && World.isAbleToGo(this, World.DOWN)) {
            this.isMoving = true;
            moveInit(World.DOWN);
        }
    }

    public void moveRight() {
        if (!isMoving && World.isAbleToGo(this, World.RIGHT)) {
            this.isMoving = true;
            moveInit(World.RIGHT);
        }
    }

    public void hurt(int dmgAmt) {
        currentHealthPoints -= dmgAmt;
        if(currentHealthPoints <= 0) {
            die();
        }
    }

    private void die() {
        World.removeEntity(this);
    }

    public void attackUp() {
        attack(World.getTargetDirectionTile(this, World.UP));
    }

    public void attackDown() {
        attack(World.getTargetDirectionTile(this, World.DOWN));
    }

    public void attackLeft() {
        attack(World.getTargetDirectionTile(this, World.LEFT));
    }

    public void attackRight() {
        attack(World.getTargetDirectionTile(this, World.RIGHT));
    }

    private void attack(Tile tile) {
        if (!isAttacking && World.isTileOccupied(tile)) {
            if(this instanceof Player) {

            }
            isAttacking = true;
            Character characterFromTile = World.getCharacterFromTile(tile);
            characterFromTile.hurt(damage);
            characterFromTile.isDamaged = true;
            characterFromTile.damageGot = damage;
            characterFromTile.cleanDamageTimerHelper = 0f;
            attackTimeHelper = 0;
        }
    }

    private void moveInit(int direction) {
        this.lastX = this.getCurrentTile().x;
        this.lastY = this.getCurrentTile().y;
        this.destination = World.getTargetDirectionTile(this, direction);
    }

    @Override
    public void update() {
        moveUpdate();
        attackUpdate();
    }

    private void attackUpdate() {
            attackTimeHelper += Gdx.graphics.getDeltaTime();
            if (attackTimeHelper > attackSpeed) {
                this.isAttacking = false;
                attackTimeHelper = 0;
        }
    }

    private void moveUpdate() {
        if (!this.isMoving || this.destination == null) return;

        if (MathUtils.areEqual(this.x, destination.x) && MathUtils.areEqual(this.y, destination.y)) {
            this.destination = null;
            this.isMoving = false;
            return;
        }

        this.setX(MathUtils.moveTowards(this.lastX, this.destination.x, movementSpeed));
        this.setY(MathUtils.moveTowards(this.lastY, this.destination.y, movementSpeed));
        this.lastX = this.getX();
        this.lastY = this.getY();
    }
}
