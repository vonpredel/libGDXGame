package com.mygdx.game.Skills.types;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Skills.Skill;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.PathFinding;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MovingSkill extends Skill {

    private int damage;
    private int manaCost;
    private Type type;
    private int range;
    private boolean magic;

    private float storageSpeed;

    public MovingSkill(String name, int damage, int manaCost, Type type, int range, boolean magic) {
        super(name);
        this.damage = damage;
        this.manaCost = manaCost;
        this.type = type;
        this.range = range;
        this.magic = magic;
        this.storageSpeed = World.getPlayer().getMovementSpeed();
    }

    @Override
    public void use() {
        final Player player = World.getPlayer();
        if (player.getCurrentManaPoints() < manaCost || player.isAttacking()) return;
        player.setCurrentManaPoints(player.getCurrentManaPoints() - manaCost);
        final List<Tile> nearbyTiles = World.getNearbyTilesSquare(range, World.getPlayer());
        final Map<Integer, NonStatic> entitiesFromTiles = World.getNonStaticsFromTiles(nearbyTiles);
        if (entitiesFromTiles.isEmpty()) return;
        List<Integer> keys = new ArrayList<>(entitiesFromTiles.keySet());
        final int key = new Random().nextInt(keys.size());
        NonStatic target = entitiesFromTiles.get(keys.get(key));
        int damageToDeal = magic ? damage
                : MathUtils.random(player.getMinDamage(), player.getMaxDamage()) * damage;
        performAction(target, damageToDeal);
    }

    private void performAction(NonStatic nonStatic, int damageToDeal) {
        final int playerPosition = World.getCurrentEntityPosition(World.getPlayer());
        final int targetPosition = World.getCurrentEntityPosition(nonStatic);
        Tile singleTargetDirectionTile;
        int path;
        switch (type) {
            case PULL:
                path = PathFinding.findPath(playerPosition, targetPosition);

                singleTargetDirectionTile = World.getSingleTargetDirectionTile(World.getPlayer(), path);

                if (singleTargetDirectionTile.isSolid()) {
                    break;
                }

                nonStatic.setMovementSpeed(500);
                nonStatic.moveInitToDestinationTile(singleTargetDirectionTile);
                break;
            case PUSH:
                path = PathFinding.findPath(playerPosition, targetPosition);
                final List<Tile> targetDirectionTiles = World.getTargetDirectionTiles(nonStatic, path, 5 - range);
                nonStatic.setMovementSpeed(500);
                for (int i = targetDirectionTiles.size() - 1; i >= 0; i--) {
                    if (targetDirectionTiles.get(i).isSolid()) {
                        continue;
                    }
                    nonStatic.moveInitToDestinationTile(targetDirectionTiles.get(i));
                    break;
                }
                break;
            case WARP:
                path = PathFinding.findPath(targetPosition, playerPosition);
                singleTargetDirectionTile = World.getSingleTargetDirectionTile(nonStatic, path);

                if (singleTargetDirectionTile.isSolid()) {
                    break;
                }

                World.getPlayer().warp((int) singleTargetDirectionTile.x, (int) singleTargetDirectionTile.y);
                break;
            case CHARGE:
                storageSpeed = World.getPlayer().getMovementSpeed();

                path = PathFinding.findPath(targetPosition, playerPosition);
                singleTargetDirectionTile = World.getSingleTargetDirectionTile(nonStatic, path);

                if (singleTargetDirectionTile.isSolid()) {
                    return;
                }

                World.getPlayer().setMovementSpeed(500);
                World.getPlayer().moveInitToDestinationTile(singleTargetDirectionTile);
                break;
        }
        nonStatic.hurt(damageToDeal);
    }

    @Override
    public void update() {
        if (!World.getPlayer().isMoving()) {
            World.getPlayer().setMovementSpeed(storageSpeed);
        }
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add(getName());
        description.add("Mana Cost" + " : " + manaCost);
        String damageStr = magic ? "Damage " : "Damage Multiplier ";
        description.add(damageStr + " : " + damage);
        description.add("Range" + " : " + range);
        description.add("Magic" + " : " + magic);
        description.add("Type" + " : " + type);
        return description;
    }

    public enum Type {
        PULL, PUSH, WARP, CHARGE
    }
}
