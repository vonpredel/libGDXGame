package com.mygdx.game.Entities.NonStatics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.MyMathUtils;
import com.mygdx.game.Utils.assets.AssetChopper;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.World.World;
import com.mygdx.game.inventory.Inventory;
import java.util.Optional;

public abstract class NonStatic extends Entity {

    private boolean isMoving;
    private boolean isAttacking;

    private float attackTimeHelper;
    private float animationTimeHelper;
    private float idleTimeHelper;
    private float cleanDamageTimerHelper;

    private Tile destination;
    private float lastX, lastY;
    private int movementDirection;

    private boolean isDamaged;
    private String damageGot;

    private TextureRegion textureToRender;
    private TextureRegion[] animations;
    private int animationFrame;

    private int strength;
    private int dexterity;
    private int vitality;
    private int energy;

    private float movementSpeed;

    private int maxHealthPoints;
    private int currentHealthPoints;
    private int currentManaPoints;
    private int maxManaPoints;
    private int currentStaminaPoints;
    private int maxStaminaPoints;

    private Inventory inventory;

    public NonStatic() {
        this.isDamaged = false;
        this.isAttacking = true;
        this.damageGot = "0";
        this.inventory = new Inventory();
        this.animationTimeHelper = this.attackTimeHelper = this.idleTimeHelper = this.cleanDamageTimerHelper = 0.0f;
    }

    //<editor-fold desc="Inventory" defaultstate="collapsed">

    protected void dropEquipment() {
        Tile tile = getCurrentTile();
        if (inventory.getEquipedWeapon() != null) inventory.getEquipedWeapon().generateOnMap(tile.x, tile.y);
        if (inventory.getEquipedArmor() != null) inventory.getEquipedArmor().generateOnMap(tile.x, tile.y);
        if (inventory.getEquipedShield() != null) inventory.getEquipedShield().generateOnMap(tile.x, tile.y);
        if (inventory.getEquipedHelmet() != null) inventory.getEquipedHelmet().generateOnMap(tile.x, tile.y);
        inventory.getItems().forEach(i -> i.generateOnMap(tile.x, tile.y));
        inventory.removeWholeInventory();
    }

    public void dropItem(Item item) {
        Tile tile = getCurrentTile();
        item.generateOnMap(tile.x, tile.y);
        inventory.getItems().remove(item);
    }

//    </editor-fold>

    //<editor-fold desc="getters for statistics" defaultstate="collapsed">

    public int getDefence() {
        final Armor equipedArmor = this.getInventory().getEquipedArmor();
        final Armor equipedShield = this.getInventory().getEquipedShield();
        final Armor equipedHelmet = this.getInventory().getEquipedHelmet();
        return (equipedArmor == null ? 0 : equipedArmor.getDefence())
                + (equipedShield == null ? 0 : equipedShield.getDefence())
                + (equipedHelmet == null ? 0 : equipedHelmet.getDefence())
                + (vitality/10);
    }

    public float getMovementSpeed() {
        return movementSpeed - ((this.getInventory().getEquipedArmor() == null ? 0
                : this.getInventory().getEquipedArmor().getMovementSpeedReduction())
                + (this.getInventory().getEquipedShield() == null ? 0
                : this.getInventory().getEquipedShield().getMovementSpeedReduction())
                + (this.getInventory().getEquipedHelmet() == null ? 0
                : this.getInventory().getEquipedHelmet().getMovementSpeedReduction()))
                + (dexterity*0.02f);
    }

    public int getMinDamage() {
        return this.getWeapon().map(Weapon::getMinDamage).orElse(0)
                + (strength/5);
    }

    public int getMaxDamage() {
        return this.getWeapon().map(Weapon::getMaxDamage).orElse(1)
                + (strength/5);
    }

    public float getAttackSpeed() {
        return this.getWeapon().map(Weapon::getSpeed).orElse(1.0f)
                + (dexterity * 0.02f);
    }

    public int getCritChance() {
        return this.getWeapon().map(Weapon::getCritChance).orElse(0)
                + (dexterity/10);
    }

    public int getAccuracy() {
        return this.getWeapon().map(Weapon::getAccuracy).orElse(50)
                + (dexterity/5);
    }

    public int getRange() {
        return this.getWeapon().map(Weapon::getRange).orElse(1);
    }

//    </editor-fold>

    //<editor-fold desc="Moving" defaultstate="collapsed">

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
        this.movementDirection = direction;
        this.lastX = this.getCurrentTile().x;
        this.lastY = this.getCurrentTile().y;
        this.destination = World.getSingleTargetDirectionTile(this, direction);
    }

    private void moveUpdate() {
        if (!this.isMoving || this.destination == null) return;

        if (MyMathUtils.areEqual(this.x, destination.x) && MyMathUtils.areEqual(this.y, destination.y)) {
            this.destination = null;
            this.isMoving = false;
            this.idleTimeHelper = 0.0f;
            return;
        }

        this.setX(MyMathUtils.moveTowards(this.lastX, this.destination.x, getMovementSpeed()));
        this.setY(MyMathUtils.moveTowards(this.lastY, this.destination.y, getMovementSpeed()));
        this.lastX = this.getX();
        this.lastY = this.getY();
    }

    //</editor-fold>

    //<editor-fold desc="Attacking" defaultstate="collapsed">

    public void performAttack(int direction) {
        if(!isAttacking) {
            for (Tile tile : World.getTargetDirectionTiles(this, direction, this.getRange())) {
                tile.setHitted();
                if(tile.isSolid()) break;
                final NonStatic nonStaticFromTile = World.getNonStaticFromTile(tile);
                if (nonStaticFromTile != null) {
                    attack(nonStaticFromTile);
                    if (!getInventory().getEquipedWeapon().isPiercing()) break;
                }
            }
            isAttacking = true;
        }
    }

    private void attack(NonStatic nonStatic) {
        int damage = 0;
        if(MathUtils.random(1,100) <= getAccuracy()) {
            damage = MathUtils.random(getMinDamage(), getMaxDamage());
            damage = (MathUtils.random(1, 100) <= getCritChance() ? damage * 2 : damage);
            damage -= nonStatic.getDefence();
            damage = Math.max(0, damage);
        }
        nonStatic.hurt(damage);
        attackTimeHelper = 0;
    }

    private void attackUpdate() {
        if(isAttacking()) {
            attackTimeHelper += Gdx.graphics.getDeltaTime();
            if (attackTimeHelper > getAttackSpeed()) {
                this.isAttacking = false;
                attackTimeHelper = 0;
            }
        }
    }

    //</editor-fold>

    //<editor-fold desc="Internal" defaultstate="collapsed">

    @Override
    public void update() {
        moveUpdate();
        attackUpdate();
        cleanDamageUpdate();
        textureToRenderUpdate();
        ai();
    }

    private void textureToRenderUpdate() {
        animationTimeHelper += Gdx.graphics.getDeltaTime();
        idleTimeHelper += Gdx.graphics.getDeltaTime();
        if (animationTimeHelper > 1f - (this.getMovementSpeed() / 10)) {
            if (animationFrame == 2) animationFrame = 0;
            else animationFrame++;
            animationTimeHelper = 0;
        }

        switch (movementDirection) {
            case World.DOWN:
                textureToRender = animations[animationFrame];
                break;
            case World.LEFT:
                textureToRender = animations[animationFrame + 3];
                break;
            case World.RIGHT:
                textureToRender = animations[animationFrame + 6];
                break;
            case World.UP:
                textureToRender = animations[animationFrame + 9];
                break;
            default:
                textureToRender = animations[1];
                break;
        }

        if (!isMoving && idleTimeHelper >= 0.1f) {
            textureToRender = animations[1];
        }
    }

    @Override
    public void draw(SpriteBatch batch, BitmapFont font) {
        batch.draw(textureToRender, x, y, width, height);
        if (isDamaged) {
            batch.draw(new Texture(AssetsConstants.DAMAGE), x, y, width, height);
            font.setColor(Color.WHITE);
            font.draw(batch, String.valueOf(damageGot), x + width * 0.4f, y + height * 0.6f);
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

    ///</editor-fold>

    @Override
    protected void die() {
        super.die();
        dropEquipment();
    }

    public void hurt(int dmgAmt) {
        currentHealthPoints -= dmgAmt;
        if (currentHealthPoints <= 0) {
            die();
        }
        damageGot = (dmgAmt == 0) ? "MISS" : String.valueOf(dmgAmt);
        isDamaged = true;
        cleanDamageTimerHelper = 0f;
    }

    protected abstract void ai();

    @Override
    public boolean isSolid() {
        return true;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public Inventory getInventory() {
        return inventory;
    }

    protected Optional<Weapon> getWeapon() {
        return Optional.ofNullable(this.getInventory().getEquipedWeapon());
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    @Override
    public void setTexture(Texture texture) {
        super.setTexture(texture);
        animations = AssetChopper.crop(texture);
    }

    public int getCurrentManaPoints() {
        return currentManaPoints;
    }

    public void setCurrentManaPoints(int currentManaPoints) {
        this.currentManaPoints = currentManaPoints;
    }

    public int getMaxManaPoints() {
        return maxManaPoints;
    }

    public void setMaxManaPoints(int maxManaPoints) {
        this.maxManaPoints = maxManaPoints;
        this.currentManaPoints = maxManaPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
        this.currentHealthPoints = maxHealthPoints;
    }

    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public void setCurrentHealthPoints(int currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
    }

    public int getCurrentStaminaPoints() {
        return currentStaminaPoints;
    }

    public void setCurrentStaminaPoints(int currentStaminaPoints) {
        this.currentStaminaPoints = currentStaminaPoints;
    }

    public int getMaxStaminaPoints() {
        return maxStaminaPoints;
    }

    public void setMaxStaminaPoints(int maxStaminaPoints) {
        this.maxStaminaPoints = maxStaminaPoints;
        this.currentStaminaPoints = maxStaminaPoints;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }
}
