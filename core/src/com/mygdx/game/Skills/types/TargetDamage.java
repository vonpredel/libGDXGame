package com.mygdx.game.Skills.types;

import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Skills.Skill;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TargetDamage extends Skill {
    private int targets;

    private int damage;
    private int manaCost;
    private int range;
    private TargetType targetType;

    private Random random;

    public TargetDamage(String name, int targets, int damage, int manaCost, int range, TargetType targetType) {
        super(name);
        this.targets = targets;
        this.damage = damage;
        this.manaCost = manaCost;
        this.range = range;
        this.targetType = targetType;
        this.random = new Random();
    }

    @Override
    public void use() {
        final Player player = World.getPlayer();
        if (player.getCurrentManaPoints() < manaCost || player.isAttacking()) return;
        player.setCurrentManaPoints(player.getCurrentManaPoints() - manaCost);
        final List<Tile> nearbyTiles = World.getNearbyTilesSquare(range, World.getPlayer());
        final Map<Integer, NonStatic> entitiesFromTiles = World.getNonStaticsFromTiles(nearbyTiles);
        if (entitiesFromTiles.isEmpty()) return;
        determinateTargets(entitiesFromTiles).forEach(ns -> ns.hurt(damage));
    }

    private Collection<NonStatic> determinateTargets(Map<Integer, NonStatic> entitiesFromTiles) {

        final List<Integer> keys = new ArrayList<>(entitiesFromTiles.keySet());
        if (targetType.equals(TargetType.CLOSEST) && entitiesFromTiles.size() > targets) {
            final int width = World.getCurrentZoneWidth() * World.getWorldWidth();
            final int playerPosition = World.getCurrentEntityPosition(World.getPlayer());
            int playerX = playerPosition % width;
            int playerY = playerPosition / width;
            keys.sort((o1, o2) -> {
                int o1x = Math.abs(playerX - (o1 % width));
                int o1y = Math.abs(playerY - (o1 / width));
                int o1diff = o1x + o1y;
                int o2x = Math.abs(playerX - (o2 % width));
                int o2y = Math.abs(playerY - (o2 / width));
                int o2diff = o2x + o2y;
                return Integer.compare(o1diff, o2diff);
            });
            while (entitiesFromTiles.size() > targets) {
                final Integer index = keys.get(keys.size() - 1);
                entitiesFromTiles.remove(index);
                keys.remove(index);
            }
        }
        if (TargetType.RANDOM.equals(targetType)) {
            while (entitiesFromTiles.size() > targets) {
                final int i = random.nextInt(keys.size());
                int randomKey = keys.get(i);
                entitiesFromTiles.remove(randomKey);
                keys.remove(i);
            }
        }
        return entitiesFromTiles.values();
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add(getName());
        description.add("Mana Cost" + " : " + manaCost);
        description.add("Damage" + " : " + damage);
        description.add("Range" + " : " + range);
        description.add("Targets" + " : " + targets);
        description.add("Target Type" + " : " + targetType);
        return description;
    }

    public enum TargetType {
        CLOSEST, RANDOM;
    }
}
