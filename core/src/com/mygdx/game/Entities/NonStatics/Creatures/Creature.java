package com.mygdx.game.Entities.NonStatics.Creatures;

import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Tiles.Tile;

public abstract class Creature extends NonStatic {

    @Override
    public void attack(Tile tile) {

    }

    @Override
    protected void die() {
        super.die();
        dropLoot();
    }

    @Override
    public void attackUpdate() {

    }

    protected abstract void dropLoot();
}
