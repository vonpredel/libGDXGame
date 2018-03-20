package com.mygdx.game.Entities.NonStatics;

import com.mygdx.game.Utils.AILogic;

public class Enemy extends NonStatic {

    private AILogic.AIType aiType;

    @Override
    public void ai() {
        if (this.aiType != null) {
            this.aiType.getConsumer().accept(this);
        }
    }

    public void setAiType(AILogic.AIType aiType) {
        this.aiType = aiType;
    }
}
