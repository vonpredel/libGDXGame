package com.mygdx.game.Utils;

import com.mygdx.game.Entities.Entity;
import java.util.List;

public class Updater {

    public void update(List<Entity> list) {
        list.forEach(Entity::update);
    }
}
