package com.mygdx.game.Skills;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Skills.types.AoeDamage;
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
        this.creators.put(AoeDamage.class, values -> {
            final AoeDamage aoeDamage = new AoeDamage(values[0],Boolean.parseBoolean(values[2]),Integer.parseInt(values[3]),
                    Integer.parseInt(values[4]),Integer.parseInt(values[5]),Integer.parseInt(values[6]));
            aoeDamage.texture = this.assets.manager.get(values[1], Texture.class);
            return aoeDamage;
        });
        this.creators.put(Passive.class, values -> {
            final Passive passive = new Passive(values[0]);
            passive.texture = this.assets.manager.get(values[1], Texture.class);
            return passive;
        });

        this.loadFile("dataSkills/aoeDamage.csv", values -> values[0]);
        this.loadFile("dataSkills/passive.csv", values -> values[0]);

    }
}
