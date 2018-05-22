package com.mygdx.game.Entities.Npc;

import com.mygdx.game.Entities.Entity;

public class Npc extends Entity {

    private NpcType npcType;

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public void update() {

    }

    public enum NpcType {
        MERCHANT,QUEST,NORMAL;
    }
}
