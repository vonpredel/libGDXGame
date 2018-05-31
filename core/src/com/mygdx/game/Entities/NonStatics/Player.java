package com.mygdx.game.Entities.NonStatics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Npc.Npc;
import com.mygdx.game.Entities.Statics.Static;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Skills.SpellBook;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.World.World;
import com.mygdx.game.inventory.Inventory;
import com.mygdx.game.quests.QuestHandler;
import java.util.Objects;
import java.util.Optional;

public class Player extends NonStatic {

    private boolean isDead = false;

    private int experience;
    private int experienceToNextLevel = 10;
    private int level = 1;
    private int skillPoints = 3;
    private int pointsToSpend;

    private int gold;

    private SpellBook spellBook;
    private QuestHandler questHandler;

    public Player() {
        this.spellBook = new SpellBook();
        this.questHandler = new QuestHandler();
    }

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
            skillPoints+=1;
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

    public QuestHandler getQuestHandler() {
        return questHandler;
    }

    public SpellBook getSpellBook() {
        return spellBook;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void actionOnStatic() {
        final Optional<Static> first = World.getNearbyTilesCross(1, this)
                .stream()
                .filter(World::isTileOccupied)
                .map(World::getStaticFromTile)
                .filter(Objects::nonNull)
                .findAny();
        if (!first.isPresent()) {
            return;
        }
        first.orElse(null).performAction();
    }

    public void actionOnNpc() {
        final Optional<Npc> first = World.getNearbyTilesCross(1,this)
                .stream()
                .filter(World::isTileOccupied)
                .map(World::getNpcFromTile)
                .filter(Objects::nonNull)
                .findAny();
        if (!first.isPresent()) {
            return;
        }
        first.orElse(null).performAction();
    }
}
