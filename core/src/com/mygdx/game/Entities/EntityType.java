package com.mygdx.game.Entities;
import com.mygdx.game.Entities.NonStatics.Enemy;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Entities.Statics.Static;

public enum EntityType {
	NIGGA(Enemy.class),
	PLAYER(Player.class),
	CHEST(Static.class);

    private final Class<? extends Entity> cls;

    private EntityType(Class<? extends Entity> cls) {
        this.cls = cls;
    }

    public Class<? extends Entity> getCls() {
        return cls;
    }
}