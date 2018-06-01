package com.mygdx.game.Skills;

import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.List;

public class SpellBook {

    private Skill[] bindedSkills;

    List<Skill> spells;

    public SpellBook() {
        spells = new ArrayList<>();
        bindedSkills = new Skill[3];
    }

    public void temporaryInit() {
        spells.add(World.getSkillsManager().create(SkillType.POWER_STRIKE));
        spells.add(World.getSkillsManager().create(SkillType.GREAT_THROW));
        spells.add(World.getSkillsManager().create(SkillType.SHOCK));
        spells.add(World.getSkillsManager().create(SkillType.PSYCHIC_HAMMER));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_FIV));
        spells.add(World.getSkillsManager().create(SkillType.HEAL));

        spells.add(World.getSkillsManager().create(SkillType.CHARGE));
        spells.add(World.getSkillsManager().create(SkillType.PULL));
        spells.add(World.getSkillsManager().create(SkillType.LIGHTING));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_FOR));
        spells.add(World.getSkillsManager().create(SkillType.WARP));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_SIX));

        spells.add(World.getSkillsManager().create(SkillType.CLEAVE));
        spells.add(World.getSkillsManager().create(SkillType.QUICKNESS));
        spells.add(World.getSkillsManager().create(SkillType.FIRE_NOVA));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_FOR));
        spells.add(World.getSkillsManager().create(SkillType.EXPANSIVE_MIND));
        spells.add(World.getSkillsManager().create(SkillType.ENDURANCE));

        spells.add(World.getSkillsManager().create(SkillType.HARDINESS));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_TWO));
        spells.add(World.getSkillsManager().create(SkillType.EARTHQUAKE));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_FOR));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_FIV));
        spells.add(World.getSkillsManager().create(SkillType.HEALTH_REGENERATION));

        spells.add(World.getSkillsManager().create(SkillType.BASH));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_TWO));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_THR));
        spells.add(World.getSkillsManager().create(SkillType.STAR_FALL));
        spells.add(World.getSkillsManager().create(SkillType.MANA_REGENERATION));
        spells.add(World.getSkillsManager().create(SkillType.REJUVENATION));

        spells.add(World.getSkillsManager().create(SkillType.WHIRLIND));
        spells.add(World.getSkillsManager().create(SkillType.AMBUSH));
        spells.add(World.getSkillsManager().create(SkillType.ROCK_THROW));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_SIX));
        spells.add(World.getSkillsManager().create(SkillType.MOON_LIGHT));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_SIX));

    }

    public void bindSkill(Skill skill, int slot) {
        bindedSkills[slot] = skill;
    }

    public Skill getBindedSkill(int slot) {
        return bindedSkills[slot];
    }

    public Skill[] getBindedSkills() {
        return bindedSkills;
    }

    public List<Skill> getSpells() {
        return spells;
    }
}
