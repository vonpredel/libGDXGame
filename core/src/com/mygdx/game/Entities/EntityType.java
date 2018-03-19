package com.mygdx.game.Entities;
import com.mygdx.game.Entities.NonStatics.Enemy;

public enum EntityType {
	NIGGA(Enemy.class);

    private final Class<? extends Entity> cls;

    private EntityType(Class<? extends Entity> cls) {
        this.cls = cls;
    }

    public Class<? extends Entity> getCls() {
        return cls;
    }
}