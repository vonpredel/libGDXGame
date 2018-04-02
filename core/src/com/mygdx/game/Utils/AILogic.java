package com.mygdx.game.Utils;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.World.World;
import java.util.function.Consumer;

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

    }

    private static void spamAttack(NonStatic nonStatic) {
        if (!World.getTargetsFromDirection(nonStatic, World.UP).isEmpty()) {
            nonStatic.performAttack(World.UP);
            return;
        }
        if (!World.getTargetsFromDirection(nonStatic, World.DOWN).isEmpty()) {
            nonStatic.performAttack(World.DOWN);
            return;
        }
        if (!World.getTargetsFromDirection(nonStatic, World.LEFT).isEmpty()) {
            nonStatic.performAttack(World.LEFT);
            return;
        }
        if (!World.getTargetsFromDirection(nonStatic, World.RIGHT).isEmpty()) {
            nonStatic.performAttack(World.RIGHT);
        }
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
