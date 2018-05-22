package com.mygdx.game.Skills.types;

import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Skills.Skill;
import com.mygdx.game.World.World;
import java.util.HashMap;
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
    public Map<String, String> getDescription() {
        Map<String, String> map = new HashMap<>();
        map.put("Name",getName());
        map.put("Mana Cost", String.valueOf(manaCost));
        map.put("Amount", String.valueOf(amount));
        return map;
    }
}
