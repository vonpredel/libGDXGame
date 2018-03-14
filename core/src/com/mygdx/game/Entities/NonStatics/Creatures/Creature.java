package com.mygdx.game.Entities.NonStatics.Creatures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.NonStatics.Characters.Character;
import com.mygdx.game.Entities.NonStatics.NonStatic;

public abstract class Creature extends NonStatic {

    protected int minDamage,maxDamage;
    protected int accuracy;
    protected int defence;
    protected int critChance;
    protected float attackSpeed;

    @Override
    public int countDamage(NonStatic targetNonStatic) {
        int damage;
        if(!(MathUtils.random(1,100) <= getAccuracy())) return 0;
        else {
            int reducedDamage = targetNonStatic instanceof Character
                    ? ((Character) targetNonStatic).getInventory().getEquipedArmor().getDefence()
                    : ((Creature) targetNonStatic).getDefence();

            damage = MathUtils.random(getMinDamage(), getMaxDamage());
            damage = (MathUtils.random(1,100) <= getCritChance() ? damage*2 : damage)
                    - reducedDamage;
            return Math.max(0,damage);
        }
    }

    @Override
    protected void die() {
        super.die();
        dropLoot();
    }

    @Override
    public void attackUpdate() {
        attackTimeHelper += Gdx.graphics.getDeltaTime();
        if (attackTimeHelper > attackSpeed) {
            this.isAttacking = false;
            attackTimeHelper = 0;
        }
    }

    protected abstract void dropLoot();

    public int getDefence() {
        return defence;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getCritChance() {
        return critChance;
    }
}
