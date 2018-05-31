package com.mygdx.game.Skills;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Updateable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Skill implements Updateable {

    private String name;
    protected Texture texture;

    public Skill(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Texture getTexture() {
        return texture;
    }

    public abstract void use();

    public abstract List<String> getDescription();

    @Override
    public void update() {

    }
}
