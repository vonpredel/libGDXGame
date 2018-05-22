package com.mygdx.game.Entities.Statics;

import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.World.World;

public class Fountain extends Static {

    private FountainType fountainType;
    private int amount;

    public Fountain(FountainType fountainType, int amount) {
        this.fountainType = fountainType;
        this.amount = amount;
        setState(State.UNUSED);
    }

    @Override
    public void performAction() {
        if (State.USED == getState()) return;
        setState(State.USED);
        action();
    }

    private void action() {
        final Player player = World.getPlayer();
        switch (fountainType) {
            case HEAL:
                player.setCurrentHealthPoints(player.getMaxHealthPoints());
                break;
            case MANA:
                player.setCurrentManaPoints(player.getMaxManaPoints());
                break;
            case STR:
                player.setStrength(player.getStrength() + amount);
                break;
            case DEX:
                player.setDexterity(player.getDexterity() + amount);
                break;
            case VIT:
                player.setVitality(player.getVitality() + amount);
                break;
            case ENE:
                player.setEnergy(player.getEnergy() + amount);
                break;
            case EXP:
                player.setExperience(player.getExperience() + amount);
                break;
        }
    }

    public enum FountainType {
        HEAL, MANA, EXP, STR, DEX, VIT, ENE
    }
}
