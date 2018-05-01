package com.mygdx.game.Skills;
import com.mygdx.game.Skills.types.AoeDamage;
import com.mygdx.game.Skills.types.Passive;

public enum SkillType {
	FIRE_NOVA(AoeDamage.class),
	WHIRLIND(AoeDamage.class),
	TEST_SPELL_ONE(AoeDamage.class),
	TEST_SPELL_TWO(AoeDamage.class),
	TEST_SPELL_THR(AoeDamage.class),
	TEST_SPELL_FOR(AoeDamage.class),
	TEST_SPELL_FIV(AoeDamage.class),
	TEST_SPELL_SIX(AoeDamage.class),
	PASSIVE_SKILL(Passive.class);

    private final Class<? extends Skill> cls;

    private SkillType(Class<? extends Skill> cls) {
        this.cls = cls;
    }

    public Class<? extends Skill> getCls() {
        return cls;
    }
}