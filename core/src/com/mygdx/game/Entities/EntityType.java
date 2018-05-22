package com.mygdx.game.Entities;
import com.mygdx.game.Entities.NonStatics.Enemy;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Entities.Statics.Door;
import com.mygdx.game.Entities.Statics.Fountain;
import com.mygdx.game.Entities.Statics.Chest;

public enum EntityType {
	NIGGA(Enemy.class),
	GOBLIN(Enemy.class),
	TESTER(Enemy.class),
	PLAYER(Player.class),
	DOOR(Door.class),
	FOUNTAIN(Fountain.class),
	CHEST(Chest.class);

    private final Class<? extends Entity> cls;

    private EntityType(Class<? extends Entity> cls) {
        this.cls = cls;
    }

    public Class<? extends Entity> getCls() {
        return cls;
    }
}