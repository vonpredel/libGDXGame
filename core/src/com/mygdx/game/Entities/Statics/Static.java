package com.mygdx.game.Entities.Statics;

import com.mygdx.game.Entities.Entity;

public class Static extends Entity {

    private boolean isOpen = false;

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void update() {

    }

    public void open() {
        isOpen = true;
    }
}
