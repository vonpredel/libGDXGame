package com.mygdx.game.Entities.NonStatics.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.World.World;
import com.mygdx.game.inventory.Inventory;

public class Player extends Character {

    private int strength;
    private int dexterity;
    private int vitality;
    private int energy;

    private int currentManaPoints;
    private int maxManaPoints;
    private int currentStaminaPoints;
    private int maxStaminaPoints;

    private boolean isDead = false;

    public Player(Assets assets) {
        setTexture(assets.manager.get(AssetsConstants.PLAYER_SHEET, Texture.class));
        this.width = Constants.DEFAULT_CHARACTER_WIDTH;
        this.height = Constants.DEFAULT_CHARACTER_HEIGHT;
        this.movementSpeed = Constants.DEFAULT_MOVEMENT_SPEED;
        this.x = 512;
        this.y = 512;
        this.maxHealthPoints = Constants.DEFAULT_MAX_HEALTH_POINTS;
        this.currentHealthPoints = maxHealthPoints;
        this.strength = 1;
        this.dexterity = 1;
        this.vitality = 1;
        this.energy = 1;
        this.maxManaPoints = 10;
        this.currentManaPoints = maxManaPoints;
        this.maxStaminaPoints = 100;
        this.currentStaminaPoints = maxStaminaPoints;
    }

    @Override
    public void initializeInventory() {
        this.inventory = new Inventory();
        // TEMP EQUIP
        this.inventory.startingEquipment();
    }

    @Override
    protected void ai() {
        // Do Nothing.
    }

    public void pickUpItems() {
        World.getItemsContainer().getAllItems().forEach(this::pickUpItem);
    }

    public void pickUpItem(Item item) {
        if(item.isDropped()) {
            if(item.x == x && item.y == y) {
                if (inventory.getSpecifiedItems(item.getClass()).size()>14) {
                    return;
                } else {
                    item.moveToInventory(this.inventory);
                }
            }
        }
    }

    @Override
    protected void die() {
        World.getCameraHandler().zoomIn(50);
        this.currentHealthPoints = (int) 1e6;
        isDead = true;
    }

    @Override
    public void draw(SpriteBatch batch, BitmapFont font) {
        if (!isDead) super.draw(batch, font);
        else batch.draw(new Texture(AssetsConstants.DEAD_PLAYER), x, y, width, height);
    }

    @Override
    public void update() {
        if (!isDead) super.update();
        else World.getCameraHandler().rotateCameraRight();
    }

    public int getCurrentManaPoints() {
        return currentManaPoints;
    }

    public int getMaxManaPoints() {
        return maxManaPoints;
    }

    public int getCurrentStaminaPoints() {
        return currentStaminaPoints;
    }

    public int getMaxStaminaPoints() {
        return maxStaminaPoints;
    }

    public void setCurrentManaPoints(int currentManaPoints) {
        this.currentManaPoints = currentManaPoints;
    }

    public void setMaxManaPoints(int maxManaPoints) {
        this.maxManaPoints = maxManaPoints;
    }

    public void setCurrentStaminaPoints(int currentStaminaPoints) {
        this.currentStaminaPoints = currentStaminaPoints;
    }

    public void setMaxStaminaPoints(int maxStaminaPoints) {
        this.maxStaminaPoints = maxStaminaPoints;
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
}
