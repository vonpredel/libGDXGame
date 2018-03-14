package com.mygdx.game.Entities.NonStatics.Creatures;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.NonStatics.Characters.Character;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;

public abstract class Creature extends NonStatic {

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public void attack(Tile tile) {

    }

    @Override
    protected void die() {
        super.die();
    }

    @Override
    public void update() {

    }

    @Override
    public void attackUpdate() {

    }

    private void dropLoot() {

    }
}
