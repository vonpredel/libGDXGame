package com.mygdx.game.quests;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Updateable;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.World.World;
import java.util.List;

public abstract class Quest implements Updateable {

    private String name;
    private Texture texture;
    private String description;
    private List<String> dialogues;
    private List<Item> rewards;
    private int expReward;
    private int goldReward;

    private Status status = Status.NONTAKEN;
    private Grade grade;

    @Override
    public void update() {

    }

    public abstract boolean checkIsQuestCompleted();

    public abstract String getProgressStatus();

    public void beginQuest() {
        // TODO
        World.getPlayer().getQuestHandler().addQuest(this);
        status = Status.TAKEN;
    }

    public void completeQuest() {
        final Player player = World.getPlayer();
        player.setExperience(player.getExperience() + expReward);
        final Tile tile = World.getTileByPosition(World.getCurrentEntityPosition(player));
        rewards.forEach(r -> r.generateOnMap(tile.x,tile.y));
        rewards.clear();
        status = Status.DONE;
        player.setGold(player.getGold() + goldReward);
        player.getQuestHandler().getQuestList().remove(this);

    }

    protected void assignTexture() {
        final AssetManager manager = World.getAssets().manager;
        switch (grade) {
            case BRONZE:
                texture = manager.get(AssetsConstants.BRONZE_QUEST);
                break;
            case SILVER:
                texture = manager.get(AssetsConstants.SILVER_QUEST);
                break;
            case GOLD:
                texture = manager.get(AssetsConstants.GOLD_QUEST);
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getDialogues() {
        return dialogues;
    }

    public void setDialogues(List<String> dialogues) {
        this.dialogues = dialogues;
    }

    public List<Item> getRewards() {
        return rewards;
    }

    public void setRewards(List<Item> rewards) {
        this.rewards = rewards;
    }

    public int getExpReward() {
        return expReward;
    }

    public void setExpReward(int expReward) {
        this.expReward = expReward;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public void setGoldReward(int goldReward) {
        this.goldReward = goldReward;
    }

    public enum Status {
        NONTAKEN, TAKEN, DONE
    }

    public enum Grade {
        GOLD, SILVER, BRONZE
    }
}
