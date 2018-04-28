package com.mygdx.game.Utils;

import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;

public final class PathFinding {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static int findPath(int position, int target) {
        int odds = position - target;

        int xAxis = Math.abs(odds / 500);
        int yAxis = Math.abs(odds % 500);

        if (yAxis >= 50) yAxis = 500 - yAxis;

        return yAxis < xAxis ? odds > 0 ? LEFT : RIGHT
                : position % 500 > target % 500 ? DOWN : UP;
    }

    public static void test(NonStatic nonStatic) {
        World.getNearbyTiles(4, nonStatic).forEach(Tile::setHitted);
    }

    private PathFinding() {
    }

}
