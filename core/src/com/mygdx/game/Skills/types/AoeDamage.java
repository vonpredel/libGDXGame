package com.mygdx.game.Skills.types;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Skills.Skill;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AoeDamage extends Skill {

    private boolean magic;
    private int damage;
    private int manaCost;
    private int range;
    private Shape shape;

    public AoeDamage(String name, boolean magic, int damage, int manaCost, int range, Shape shape) {
        super(name);
        this.magic = magic;
        this.damage = damage;
        this.manaCost = manaCost;
        this.range = range;
        this.shape = shape;
    }

    @Override
    public void use() {
        final Player player = World.getPlayer();
        if (player.getCurrentManaPoints() < manaCost || player.isAttacking()) return;
        player.setCurrentManaPoints(player.getCurrentManaPoints() - manaCost);
        if (!magic) {
            damage = MathUtils.random(player.getMinDamage(), player.getMaxDamage());
            range = player.getRange();
        }
        final List<Tile> nearbyTiles = determinateTiles(shape);
        nearbyTiles.forEach(Tile::setHitted);
        World.getNonStaticsFromTiles(nearbyTiles).forEach((integer, nonStatic) -> nonStatic.hurt(damage));
    }

    private List<Tile> determinateTiles(Shape shape) {
        switch (shape) {
            case SQUARE:
                return World.getNearbyTilesSquare(range,World.getPlayer());
            case CROSS:
                return World.getNearbyTilesCross(range,World.getPlayer());
            case CIRCLE:
                return World.getNearbyTilesCircle(range,World.getPlayer());
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public Map<String, String> getDescription() {
        Map<String, String> map = new HashMap<>();
        map.put("Name",getName());
        map.put("Mana Cost", String.valueOf(manaCost));
        map.put("Damage", String.valueOf(damage));
        map.put("Range", String.valueOf(range));
        map.put("Magic", String.valueOf(magic));
        map.put("Shape", String.valueOf(shape));
        return map;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getRange() {
        return range;
    }

    public enum Shape {
        SQUARE,CROSS,CIRCLE;
    }
}
