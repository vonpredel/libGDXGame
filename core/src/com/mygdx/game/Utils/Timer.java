package com.mygdx.game.Utils;

import com.mygdx.game.Entities.Characters.Character;
import com.mygdx.game.Entities.Entity;
import java.util.List;

public class Timer {

    public void update(List<Entity> list) {
        movementUpdate(list);
    }

    private void movementUpdate(List<Entity> list) {
        list.forEach(e -> {
            if(e instanceof Character) ((Character) e).releaseBooleans();
        });
    }

    private void attackCooldownUpdate(List<Entity> list) {
        System.out.println("Temp");
    }
}
