package com.mygdx.game.Skills;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Skills.types.Active;
import com.mygdx.game.Skills.types.Passive;
import com.mygdx.game.Utils.BaseManager;
import com.mygdx.game.Utils.assets.Assets;

public class SkillsManager extends BaseManager<Skill, SkillType, SkillsContainer> {

    public SkillsManager(Assets assets, SkillsContainer container) {
        super(assets, container);
    }

    @Override
    protected Class<? extends Skill> enumToClass(SkillType typeEnum) {
        return typeEnum.getCls();
    }

    @Override
    public void loadDefinitions() {
        this.creators.put(Active.class, values -> {
            final Active active = new Active(values[0],Boolean.parseBoolean(values[2]),Integer.parseInt(values[3]),
                    Integer.parseInt(values[4]),Integer.parseInt(values[5]));
            active.texture = this.assets.manager.get(values[1], Texture.class);
            return active;
        });
        this.creators.put(Passive.class, values -> {
            final Passive passive = new Passive(values[0]);
            passive.texture = this.assets.manager.get(values[1], Texture.class);
            return passive;
        });

        this.loadFile("dataSkills/active.csv", values -> values[0]);
        this.loadFile("dataSkills/passive.csv", values -> values[0]);

    }
}
