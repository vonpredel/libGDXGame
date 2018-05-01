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
        spells.add(World.getSkillsManager().create(SkillType.FIRE_NOVA));
        spells.add(World.getSkillsManager().create(SkillType.WHIRLIND));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_ONE));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_TWO));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_THR));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_FOR));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_FIV));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_SIX));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_ONE));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_TWO));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_THR));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_FOR));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_FIV));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_SIX));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_ONE));
        spells.add(World.getSkillsManager().create(SkillType.TEST_SPELL_TWO));
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
