package com.am1goo.playdate4j.engine.utilities;

public class Mathx {

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }
}
