package com.mygdx.game.Skills.types;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Skills.Skill;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;
import java.util.List;

public class Active extends Skill {

    private boolean magic;
    private int damage;
    private int manaCost;
    private int range;

    public Active(String name, boolean magic, int damage, int manaCost, int range) {
        super(name);
        this.magic = magic;
        this.damage = damage;
        this.manaCost = manaCost;
        this.range = range;
    }

    @Override
    public void use() {
        final Player player = World.getPlayer();
        if (player.getCurrentManaPoints() < manaCost || player.isAttacking()) return;
        player.setCurrentManaPoints(player.getCurrentManaPoints() - manaCost);
        if (!magic) damage = MathUtils.random(player.getMinDamage(), player.getMaxDamage());
        final List<Tile> nearbyTiles = World.getNearbyTiles(range, player);
        nearbyTiles.forEach(Tile::setHitted);
        World.getEntitiesFromTiles(nearbyTiles).forEach((integer, nonStatic) -> nonStatic.hurt(damage));
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
}
