package com.mygdx.game.Entities.NonStatics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.MyMathUtils;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.World.World;

public abstract class NonStatic extends Entity {

    protected int maxHealthPoints, currentHealthPoints;
    protected boolean isMoving;
    protected boolean isAttacking;
    protected float movementSpeed;

    protected float attackTimeHelper = 0;

    protected Tile destination;
    private float lastX,lastY;

    public boolean isDamaged = false;
    public float cleanDamageTimerHelper = 0f;
    public int damageGot = 0;

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

    public abstract void attack(Tile tile);

    private void moveInit(int direction) {
        this.lastX = this.getCurrentTile().x;
        this.lastY = this.getCurrentTile().y;
        this.destination = World.getTargetDirectionTile(this, direction);
    }

    protected void die() {
        super.die();
    }

    public void update() {
        moveUpdate();
        attackUpdate();
        ai();
    }
    protected abstract void ai();

    public abstract void attackUpdate();

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

    @Override
    public void draw(SpriteBatch batch, BitmapFont font) {
        super.draw(batch, font);
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

    @Override
    public boolean isSolid() {
        return true;
    }
}
