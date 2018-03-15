package com.mygdx.game.Entities.NonStatics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.NonStatics.Characters.Character;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.MyMathUtils;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.World.World;
import java.util.Optional;

public abstract class NonStatic extends Entity {

    protected int maxHealthPoints, currentHealthPoints;
    protected boolean isMoving;
    private boolean isAttacking = true;
    protected float movementSpeed;

    private float attackTimeHelper = 0;

    private Tile destination;
    private float lastX,lastY;

    private boolean isDamaged = false;
    private float cleanDamageTimerHelper = 0f;
    private String damageGot = "0";

    protected Optional<Weapon> getWeapon() {
        return Optional.empty();
    }

    public abstract int getDefence();
    public abstract int getMinDamage();
    public abstract int getMaxDamage() ;
    public abstract float getAttackSpeed();
    public abstract int getCritChance();
    public abstract int getAccuracy();

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
        damageGot = (dmgAmt==0) ? "MISS" : String.valueOf(dmgAmt);
        isDamaged = true;
        cleanDamageTimerHelper = 0f;
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

    public void attack(Tile tile) {
        if (!isAttacking && World.isTileOccupied(tile)) {
            NonStatic targetNonStatic = World.getNonStaticFromTile(tile);
            if (!(targetNonStatic instanceof NonStatic)) return;
            isAttacking = true;
            int damage;
            if(!(MathUtils.random(1,100) <= getAccuracy())) damage = 0;
            else {
                int reducedDamage = targetNonStatic.getDefence();

                damage = MathUtils.random(getMinDamage(), getMaxDamage());
                damage = (MathUtils.random(1,100) <= getCritChance() ? damage*2 : damage)
                        - reducedDamage;
                damage = Math.max(0,damage);
            }
            targetNonStatic.hurt(damage);
            attackTimeHelper = 0;
        }
    }

    public void attackUpdate() {
        attackTimeHelper += Gdx.graphics.getDeltaTime();
        if (attackTimeHelper > getAttackSpeed()) {
            this.isAttacking = false;
            attackTimeHelper = 0;
        }
    }

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
        cleanDamageUpdate();
        ai();
    }
    protected abstract void ai();

    protected void defaultAi() {
        spamAttack();
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

    private void spamAttack() {
        attackUp();
        attackDown();
        attackLeft();
        attackRight();
    }

    private void moveUpdate() {
        if (!this.isMoving || this.destination == null) return;

        if (MyMathUtils.areEqual(this.x, destination.x) && MyMathUtils.areEqual(this.y, destination.y)) {
            this.destination = null;
            this.isMoving = false;
            return;
        }

        float exitMovementSpeed = movementSpeed;
        if(this instanceof Character && ((Character) this).getInventory().getEquipedArmor() != null) {
            exitMovementSpeed = this instanceof Character ? movementSpeed-((Character) this).getInventory().getEquipedArmor().getMovementSpeedReduction() : movementSpeed;
        }

        this.setX(MyMathUtils.moveTowards(this.lastX, this.destination.x, exitMovementSpeed));
        this.setY(MyMathUtils.moveTowards(this.lastY, this.destination.y, exitMovementSpeed));
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
        }
    }

    private void cleanDamageUpdate() {
        if (isDamaged) {
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

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public void setCurrentHealthPoints(int currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
    }
}
