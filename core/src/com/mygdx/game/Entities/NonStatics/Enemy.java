package com.mygdx.game.Entities.NonStatics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Utils.AILogic;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.inventory.Inventory;

public class Enemy extends NonStatic {

    private AILogic.AIType aiType;

    public Enemy() {
    }

    @Override
    public void initializeInventory() {
        super.initializeInventory();
        // TEST EQ
        this.getInventory().startingEquipment();
    }

    @Override
    public void ai() {
        if (this.aiType != null) {
            this.aiType.getConsumer().accept(this);
        }
    }

    public AILogic.AIType getAiType() {
        return aiType;
    }

    public void setAiType(AILogic.AIType aiType) {
        this.aiType = aiType;
    }
}
