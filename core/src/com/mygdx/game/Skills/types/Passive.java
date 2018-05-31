package com.mygdx.game.Skills.types;

import com.mygdx.game.Skills.Skill;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Passive extends Skill {

    public Passive(String name) {
        super(name);
    }

    @Override
    public void use() {

    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add(getName());
        return description;
    }
}
