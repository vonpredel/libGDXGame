package com.mygdx.game.Utils;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class AILogic {

    private AILogic() {

    }

    /**
     * Random moving and attack any if able;
     * @param nonStatic
     */
    private static void defaultAi(NonStatic nonStatic) {
        if(!nonStatic.isAttacking()) spamAttack(nonStatic);
        if(!nonStatic.isMoving()) {
            int i = MathUtils.random(1,4);
            switch (i) {
                case 1:
                    nonStatic.moveUp();
                    break;
                case 2:
                    nonStatic.moveDown();
                    break;
                case 3:
                    nonStatic.moveLeft();
                    break;
                case 4:
                    nonStatic.moveRight();
                    break;
            }
        }
    }

    private static void aggressiveAI(final NonStatic nonStatic) {
        if (!nonStatic.isAttacking()) spamAttack(nonStatic);
        if (!nonStatic.isMoving()) {
            final List<Tile> nearbyTiles = World.getNearbyTilesSquare(5, nonStatic);
            final Map<Integer, NonStatic> entitiesFromTiles = World.getEntitiesFromTiles(nearbyTiles);
            final int[] ints = World.checkIsPlayerOnEntitiesList(entitiesFromTiles);
            if(ints[0] == 1) {
                final int path = PathFinding.findPath(World.getCurrentEntityPosition(nonStatic), ints[1]);
                switch (path) {
                    case PathFinding.UP:
                        nonStatic.moveUp();
                        break;
                    case PathFinding.DOWN:
                        nonStatic.moveDown();
                        break;
                    case PathFinding.LEFT:
                        nonStatic.moveLeft();
                        break;
                    case PathFinding.RIGHT:
                        nonStatic.moveRight();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void defenderAI(NonStatic nonStatic) {
        if (!nonStatic.isAttacking()) spamAttack(nonStatic);
    }

    private static void spamAttack(NonStatic nonStatic) {
        if (!World.getTargetsFromDirection(nonStatic, World.UP)
                .stream().filter(e -> e instanceof Player).collect(Collectors.toList()).isEmpty()) {
            nonStatic.performAttack(World.UP);
            return;
        }
        if (!World.getTargetsFromDirection(nonStatic, World.DOWN)
                .stream().filter(e -> e instanceof Player).collect(Collectors.toList()).isEmpty()) {
            nonStatic.performAttack(World.DOWN);
            return;
        }
        if (!World.getTargetsFromDirection(nonStatic, World.LEFT)
                .stream().filter(e -> e instanceof Player).collect(Collectors.toList()).isEmpty()) {
            nonStatic.performAttack(World.LEFT);
            return;
        }
        if (!World.getTargetsFromDirection(nonStatic, World.RIGHT)
                .stream().filter(e -> e instanceof Player).collect(Collectors.toList()).isEmpty()) {
            nonStatic.performAttack(World.RIGHT);
        }
    }

    public enum AIType {
        DEFAULT(AILogic::defaultAi),
        AGGRESSIVE(AILogic::aggressiveAI),
        DEFENDER(AILogic::defenderAI);

        private Consumer<NonStatic> consumer;

        AIType(Consumer<NonStatic> consumer) {
            this.consumer = consumer;
        }

        public Consumer<NonStatic> getConsumer() {
            return consumer;
        }
    }

}
