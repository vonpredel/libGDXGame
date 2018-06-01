package com.mygdx.game.Skills.types;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Skills.Skill;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Passive extends Skill {

    private boolean active = false;
    private Type type;
    private int amount;
    private float timeHelper;

    public Passive(String name, Type type, int amount) {
        super(name);
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void use() {
        // do nothing
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add(getName());
        if (type.equals(Type.HEALTH) || type.equals(Type.MANA)) {
            description.add("Regenerates " + amount + " " + String.valueOf(type).toLowerCase() + " per Second");
        } else  {
            description.add("Increase your " + String.valueOf(type).toLowerCase() + " by " + amount);
        }
        return description;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void update() {
        if (!active) {
            return;
        }
        final Player player = World.getPlayer();
        switch (type) {
            case HEALTH:
                if (player.getCurrentHealthPoints() == player.getMaxHealthPoints()) {
                    break;
                }
                timeHelper += Gdx.graphics.getDeltaTime();
                if (timeHelper > 3) {
                    player.setCurrentHealthPoints(player.getCurrentHealthPoints() + amount);
                    timeHelper = 0;
                }
                break;
            case MANA:
                if (player.getCurrentManaPoints() == player.getMaxManaPoints()) {
                    break;
                }
                timeHelper += Gdx.graphics.getDeltaTime();
                if (timeHelper > 5) {
                    player.setCurrentManaPoints(player.getCurrentManaPoints() + amount);
                    timeHelper = 0;
                }
                break;
        }
    }

    public Type getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public enum Type {
        HEALTH,MANA,STRENGTH,DEXTERITY,VITALITY,ENERGY
    }
}
