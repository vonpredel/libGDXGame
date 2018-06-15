package com.mygdx.game.Entities.NonStatics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Npc.Npc;
import com.mygdx.game.Entities.Statics.Static;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.OffHand;
import com.mygdx.game.Items.types.Weapon;
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
    private int skillPoints = 20;
    private int pointsToSpend;

    private int strength;
    private int dexterity;
    private int vitality;
    private int energy;

    private int currentManaPoints;
    private int maxManaPoints;
    private int currentStaminaPoints;
    private int maxStaminaPoints;

    private int gold;

    private SpellBook spellBook;
    private QuestHandler questHandler;

    public Player(float movementSpeed) {
        this.spellBook = new SpellBook();
        this.questHandler = new QuestHandler();
        this.setMovementSpeed(movementSpeed);
        defaultMovementSpeed = movementSpeed;
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
        if (item instanceof OffHand || item instanceof  Armor) {
            if (inventory.getArmors().size() + inventory.getOffHands().size() < 15) {
                item.moveToInventory(inventory);
            }
        } else if (inventory.getSpecifiedItems(item.getClass()).size() < 15) {
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

    public int getDefence() {
        final Armor equipedArmor = this.getInventory().getEquipedArmor();
        final OffHand equipedOffHand = this.getInventory().getEquipedOffHand();
        final Armor equipedHelmet = this.getInventory().getEquipedHelmet();
        final Armor equipedGloves = this.getInventory().getEquipedGloves();
        final Armor equipedBoots = this.getInventory().getEquipedBoots();
        return (equipedArmor == null ? 0 : equipedArmor.getDefence())
                + (equipedOffHand == null || !equipedOffHand.getOffHandType().equals(OffHand.OffHandType.SHIELD)
                    ? 0 : (int) equipedOffHand.getValue())
                + (equipedHelmet == null ? 0 : equipedHelmet.getDefence())
                + (equipedGloves == null ? 0 : equipedGloves.getDefence())
                + (equipedBoots == null ? 0 : equipedBoots.getDefence())
                + (vitality/10);
    }

    public float getMovementSpeed() {
        final Armor equipedBoots = this.getInventory().getEquipedBoots();
        return Math.min(movementSpeed
                + (equipedBoots == null ? 0.0f : equipedBoots.getAmount())
                + (dexterity*0.02f),10f);
    }

    public int getMinDamage() {
        return this.getWeapon().map(Weapon::getMinDamage).orElse(0)
                + (strength);
    }

    public int getMaxDamage() {
        return this.getWeapon().map(Weapon::getMaxDamage).orElse(1)
                + (strength);
    }

    public float getAttackSpeed() {
        final OffHand offHand = this.getInventory().getEquipedOffHand();
        float value = offHand == null ? 0.0f
                : offHand.getOffHandType().equals(OffHand.OffHandType.QUIVER)
                ? offHand.getValue() : 0.0f;
        return Math.max(this.getWeapon().map(Weapon::getSpeed).orElse(1.0f)
                - value
                - (dexterity * 0.02f),0.1f);
    }

    public int getCritChance() {
        final Armor equipedGloves = this.getInventory().getEquipedGloves();
        return Math.min(this.getWeapon().map(Weapon::getCritChance).orElse(0)
                + (equipedGloves == null ? 0 : (int) equipedGloves.getAmount())
                + (dexterity/10),100);
    }

    public int getAccuracy() {
        final Armor equipedHelmet = this.getInventory().getEquipedHelmet();
        return Math.min(this.getWeapon().map(Weapon::getAccuracy).orElse(50)
                + (equipedHelmet == null ? 0 : (int) equipedHelmet.getAmount())
                + (dexterity/5),100);
    }

    public int getRange() {
        return this.getWeapon().map(Weapon::getRange).orElse(1);
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
        int diff = this.maxStaminaPoints - this.currentStaminaPoints;
        this.maxStaminaPoints = maxStaminaPoints;
        this.currentStaminaPoints = maxStaminaPoints - diff;
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
        int diff = this.maxManaPoints - this.currentManaPoints;
        this.maxManaPoints = maxManaPoints;
        this.currentManaPoints = maxManaPoints - diff;
    }
}
