package com.mygdx.game.Utils;

public final class MyMathUtils {

    public static float EPSILON = 0.0001f;

    private MyMathUtils() {

    }

    public static float lerp(float v0, float v1, float t) {
        return v0 + (v1 - v0) * t;
    }

    public static float sign(float a) {
        return a >= 0.0f ? 1.0f : -1.0f;
    }

    public static float moveTowards(float current, float target, float maxDelta) {
        if (Math.abs(target - current) <= maxDelta)
        {
            return target;
        }
        return current + MyMathUtils.sign(target - current) * maxDelta;
    }

    public static boolean areEqual(float a, float b) {
        return Math.abs(a - b) < EPSILON;
    }
}
