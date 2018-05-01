package com.mygdx.game.Skills;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Utils.Updateable;

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

    public void use() {

    }

    @Override
    public void update() {

    }
}
