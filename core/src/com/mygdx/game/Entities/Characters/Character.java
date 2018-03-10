package com.mygdx.game.Entities.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.MyMathUtils;
import com.mygdx.game.World.World;

public abstract class Character extends Entity {

    protected int maxHealthPoints, currentHealthPoints;
    protected boolean isMoving;
    protected boolean isAttacking;
    protected float movementSpeed;

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
            isAttacking = true;
            Character characterFromTile = World.getCharacterFromTile(tile);
            int damage;
            if(!(MathUtils.random(1,100) <= this.getInventory().getEquipedWeapon().getAccuracy())) damage = 0;
            else {
                damage = MathUtils.random(this.getInventory().getEquipedWeapon().getMinDamage(),
                        this.getInventory().getEquipedWeapon().getMaxDamage());
                damage = (MathUtils.random(1,100) <= this.getInventory().getEquipedWeapon().getCritChance() ? damage*2 : damage)
                        - characterFromTile.getInventory().getEquipedArmor().getDefence();
                damage = (damage<0) ? 0 : damage;
            }

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
            if (attackTimeHelper > this.getInventory().getEquipedWeapon().getSpeed()) {
                this.isAttacking = false;
                attackTimeHelper = 0;
        }
    }

    private void moveUpdate() {
        if (!this.isMoving || this.destination == null) return;

        if (MyMathUtils.areEqual(this.x, destination.x) && MyMathUtils.areEqual(this.y, destination.y)) {
            this.destination = null;
            this.isMoving = false;
            return;
        }

        this.setX(MyMathUtils.moveTowards(this.lastX, this.destination.x, movementSpeed));
        this.setY(MyMathUtils.moveTowards(this.lastY, this.destination.y, movementSpeed));
        this.lastX = this.getX();
        this.lastY = this.getY();
    }

}
