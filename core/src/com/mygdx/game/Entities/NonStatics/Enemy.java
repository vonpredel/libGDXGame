package com.mygdx.game.Entities.NonStatics;

import com.mygdx.game.Utils.AILogic;
import com.mygdx.game.World.World;

public class Enemy extends NonStatic {

    private AILogic.AIType aiType;
    private int experience;

    private float defaultMovementSpeed;

    public Enemy(float movementSpeed) {
        this.setMovementSpeed(movementSpeed);
        defaultMovementSpeed = getMovementSpeed();
    }

    @Override
    public void ai() {
        if (this.aiType != null) {
            this.aiType.getConsumer().accept(this);
        }
    }

    public void setAiType(AILogic.AIType aiType) {
        this.aiType = aiType;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public float getDefaultMovementSpeed() {
        return defaultMovementSpeed;
    }

    @Override
    protected void die() {
        super.die();
        final Player player = World.getPlayer();
        player.setExperience(player.getExperience() + this.getExperience());
    }
}
