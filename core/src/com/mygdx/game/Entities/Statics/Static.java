package com.mygdx.game.Entities.Statics;

import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Utils.Assets;

public abstract class Static extends Entity {

    @Override
    public boolean isSolid() {
        return false;
    }

}
