package com.am1goo.playdate4j.engine.utilities;

public class Mathx {

    public static float floor(float value) {
        return (float) Math.floor(value);
    }

    public static int floorToInt(float value) {
        return Math.round(floor(value));
    }

    public static float ceil(float value) {
        return (float) Math.ceil(value);
    }

    public static int ceilToInt(float value) {
        return Math.round(value);
    }

    public static float round(float value) {
        return Math.round(value);
    }

    public static int roundToInt(float value) {
        return Math.round(value);
    }

    public static int roundToInt(float value, int mod) {
        int rounded = roundToInt(value);
        int m = rounded % mod;
        return rounded - m;
    }

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

    public static boolean between(int value, int min, int max) {
        return min < value && value < max;
    }

    public static boolean between(float value, float min, float max) {
        return min < value && value < max;
    }

    public static boolean inside(int value, int min, int max) {
        return min <= value && value <= max;
    }

    public static boolean inside(float value, float min, float max) {
        return min <= value && value <= max;
    }

    public static boolean outside(int value, int min, int max) {
        return !inside(value, min, max);
    }

    public static boolean outside(float value, float min, float max) {
        return !inside(value, min, max);
    }

    public static float repeat(float t, float length) {
        return clamp(t - floor(t / length) * length, 0f, length);
    }

    public static float pingPong(float t, float length) {
        t = repeat(t, length * 2f);
        return length - Math.abs(t - length);
    }

    public static float lerp(float from, float to, float t) {
        return lerp(from, to, t, Easings.linear, Easings.Formula.In);
    }

    public static float lerp(float from, float to, float t, Easing easing, Easings.Formula formula) {
        return from + (to - from) * Easings.formula(t, easing, formula);
    }
}
