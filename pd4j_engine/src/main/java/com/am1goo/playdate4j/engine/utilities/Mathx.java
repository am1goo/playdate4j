package com.am1goo.playdate4j.engine.utilities;

public class Mathx {

    public static int clamp01(int value) {
        return clamp(value, 0, 1);
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }

    public static float clamp01(float value) {
        return clamp(value, 0, 1);
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    public static boolean inside(float value, float min, float max) {
        return min <= value && value <= max;
    }

    public static boolean outside(float value, float min, float max) {
        return !inside(value, min, max);
    }
}
