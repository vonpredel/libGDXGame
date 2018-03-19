package com.mygdx.game.Utils;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import java.util.function.Consumer;

public final class AILogic {

    private AILogic() {

    }

    private static void defaultAi(NonStatic nonStatic) {
        spamAttack(nonStatic);
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
        nonStatic.attackUp();
        nonStatic.attackDown();
        nonStatic.attackLeft();
        nonStatic.attackRight();
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
