package com.mygdx.game.Utils;

import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;

public final class PathFinding {

    private static final int TOTAL_WIDTH = World.getWorldWidth() * World.getCurrentZoneWidth();

    public static final int UP = World.UP;
    public static final int DOWN = World.DOWN;
    public static final int LEFT = World.LEFT;
    public static final int RIGHT = World.RIGHT;

    public static int findPath(int position, int target) {
        int odds = position - target;

        int xAxis = Math.abs(odds / TOTAL_WIDTH);
        int yAxis = Math.abs(odds % TOTAL_WIDTH);

        if (yAxis >= World.getCurrentZoneWidth()) yAxis = TOTAL_WIDTH - yAxis;

        return yAxis < xAxis ? odds > 0 ? LEFT : RIGHT
                : position % TOTAL_WIDTH > target % TOTAL_WIDTH ? DOWN : UP;
    }

    public static void test(NonStatic nonStatic) {
        World.getNearbyTilesSquare(4, nonStatic).forEach(Tile::setHitted);
    }

    private PathFinding() {
    }

}
