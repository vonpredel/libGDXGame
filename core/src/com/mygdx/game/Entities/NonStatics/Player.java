package com.mygdx.game.Entities.NonStatics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.World.World;
import com.mygdx.game.inventory.Inventory;

public class Player extends NonStatic {

    private boolean isDead = false;

    private int experience;
    private int experienceToNextLevel = 10;
    private int level = 1;
    private int pointsToSpend;

    @Override
    protected void ai() {
        // Do Nothing.
    }

    public void pickUpItems() {
        World.getItemsManager().getContainer().getAllItems().forEach(this::pickUpItem);
    }

    public void pickUpItem(Item item) {
        if ((!item.isDropped()) || (item.x != x || item.y != y)) {
            return;
        }
        final Inventory inventory = this.getInventory();
        if (inventory.getSpecifiedItems(item.getClass()).size() < 15) {
            item.moveToInventory(inventory);
        }
    }

    @Override
    protected void die() {
        World.getCameraHandler().zoomIn(50);
        this.setCurrentHealthPoints((int) 1e6);
        isDead = true;
    }

    @Override
    public void draw(SpriteBatch batch, BitmapFont font) {
        if (!isDead) super.draw(batch, font);
        else batch.draw(new Texture(AssetsConstants.DEAD_PLAYER), x, y, width, height);
    }

    @Override
    public void update() {
        if (!isDead) {
            super.update();
            levelUpdate();
        }
        else World.getCameraHandler().rotateCameraRight();
    }

    private void levelUpdate() {
        if (experience >= experienceToNextLevel) {
            experience -= experienceToNextLevel;
            experienceToNextLevel += 10f;
            experienceToNextLevel *= 1.1f;
            level++;
            pointsToSpend+=5;
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPointsToSpend() {
        return pointsToSpend;
    }

    public void setPointsToSpend(int pointsToSpend) {
        this.pointsToSpend = pointsToSpend;
    }

    public int getExperienceToNextLevel() {
        return experienceToNextLevel;
    }

    public void setExperienceToNextLevel(int experienceToNextLevel) {
        this.experienceToNextLevel = experienceToNextLevel;
    }
}
