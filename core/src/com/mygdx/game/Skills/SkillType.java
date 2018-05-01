package com.mygdx.game.Skills;
import com.mygdx.game.Skills.types.Active;
import com.mygdx.game.Skills.types.Passive;

public enum SkillType {
	FIRE_NOVA(Active.class),
	WHIRLIND(Active.class),
	TEST_SPELL_ONE(Active.class),
	TEST_SPELL_TWO(Active.class),
	TEST_SPELL_THR(Active.class),
	TEST_SPELL_FOR(Active.class),
	TEST_SPELL_FIV(Active.class),
	TEST_SPELL_SIX(Active.class),
	PASSIVE_SKILL(Passive.class);

    private final Class<? extends Skill> cls;

    private SkillType(Class<? extends Skill> cls) {
        this.cls = cls;
    }

    public Class<? extends Skill> getCls() {
        return cls;
    }
}