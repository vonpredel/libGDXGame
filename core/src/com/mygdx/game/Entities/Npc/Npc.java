package com.mygdx.game.Entities.Npc;

import com.mygdx.game.Entities.Entity;

public abstract class Npc extends Entity {

    public abstract void performAction();

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public void update() {

    }


}
