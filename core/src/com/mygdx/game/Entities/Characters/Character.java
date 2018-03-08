package com.mygdx.game.Entities.Characters;

import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.MathUtils;
import com.mygdx.game.World.World;

public abstract class Character extends Entity {

    protected boolean isMoving;
    protected float movementSpeed;

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

    private void moveInit(int direction) {
        this.lastX = this.getCurrentTile().x;
        this.lastY = this.getCurrentTile().y;
        this.destination = World.getTargetMovementTile(this, direction);
    }

    @Override
    public void update() {
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
