package com.mygdx.game.Skills.types;

import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Skills.Skill;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Heal extends Skill{

    private int amount;
    private int manaCost;

    public Heal(String name,int amount,int manaCost) {
        super(name);
        this.amount = amount;
        this.manaCost = manaCost;
    }

    @Override
    public void use() {
        final Player player = World.getPlayer();
        if (player.getCurrentManaPoints() < manaCost) return;
        player.setCurrentManaPoints(player.getCurrentManaPoints() - manaCost);
        player.setCurrentHealthPoints(player.getCurrentHealthPoints() + amount);
        if (player.getCurrentHealthPoints() > player.getMaxHealthPoints())
            player.setCurrentHealthPoints(player.getMaxHealthPoints());
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add(getName());
        description.add("Mana Cost" + " : " + manaCost);
        description.add("Amount" + " : " + amount);
        return description;
    }
}
