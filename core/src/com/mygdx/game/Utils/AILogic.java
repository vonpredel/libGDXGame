package com.mygdx.game.Utils;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class AILogic {

    private AILogic() {

    }

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

    }

    private static void spamAttack(NonStatic nonStatic) {
        if (!getTargetsFromDirection(nonStatic, World.UP).isEmpty()) {
            nonStatic.attackUp();
            return;
        }
        if (!getTargetsFromDirection(nonStatic, World.DOWN).isEmpty()) {
            nonStatic.attackDown();
            return;
        }
        if (!getTargetsFromDirection(nonStatic, World.LEFT).isEmpty()) {
            nonStatic.attackLeft();
            return;
        }
        if (!getTargetsFromDirection(nonStatic, World.RIGHT).isEmpty()) {
            nonStatic.attackRight();
        }
    }

    private static List<NonStatic> getTargetsFromDirection(NonStatic nonStatic, int direction) {
        return World.getTargetDirectionTiles(nonStatic, direction, nonStatic.getRange())
                .stream().filter(World::isTileOccupied).collect(Collectors.toList())
                .stream().map(World::getNonStaticFromTile)
                .filter(Objects::nonNull).collect(Collectors.toList());


//        return World.getTargetDirectionTiles(nonStatic, direction, nonStatic.getRange())
//                .stream().filter(World::isTileOccupied).collect(Collectors.toList())
//                .stream().map(World::getNonStaticFromTile)
//                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    public enum AIType {
        DEFAULT(AILogic::defaultAi),
        AGGRESSIVE(AILogic::aggressiveAI);

        private Consumer<NonStatic> consumer;

        AIType(Consumer<NonStatic> consumer) {
            this.consumer = consumer;
        }

        public Consumer<NonStatic> getConsumer() {
            return consumer;
        }
    }

}
