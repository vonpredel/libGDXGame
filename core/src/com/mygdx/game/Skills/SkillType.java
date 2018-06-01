package com.mygdx.game.Skills;
import com.mygdx.game.Skills.types.AoeDamage;
import com.mygdx.game.Skills.types.Passive;
import com.mygdx.game.Skills.types.Heal;
import com.mygdx.game.Skills.types.TargetDamage;

public enum SkillType {
	FIRE_NOVA(AoeDamage.class),
	WHIRLIND(AoeDamage.class),
	TEST_SPELL_ONE(AoeDamage.class),
	TEST_SPELL_TWO(AoeDamage.class),
	TEST_SPELL_THR(AoeDamage.class),
	TEST_SPELL_FOR(AoeDamage.class),
	TEST_SPELL_FIV(AoeDamage.class),
	TEST_SPELL_SIX(AoeDamage.class),
	MANA_REGENERATION(Passive.class),
	HEALTH_REGENERATION(Passive.class),
	HARDINESS(Passive.class),
	QUICKNESS(Passive.class),
	ENDURANCE(Passive.class),
	EXPANSIVE_MIND(Passive.class),
	HEAL(Heal.class),
	REJUVENATION(Heal.class),
	MOON_LIGHT(TargetDamage.class),
	STAR_FALL(TargetDamage.class),
	SHOCK(TargetDamage.class),
	LIGHTING(TargetDamage.class),
	EARTHQUAKE(TargetDamage.class);

    private final Class<? extends Skill> cls;

    private SkillType(Class<? extends Skill> cls) {
        this.cls = cls;
    }

    public Class<? extends Skill> getCls() {
        return cls;
    }
}