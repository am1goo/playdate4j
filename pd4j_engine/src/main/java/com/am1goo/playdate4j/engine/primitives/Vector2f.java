package com.am1goo.playdate4j.engine.primitives;

public class Vector2f {

    public float x;
    public float y;

    public Vector2f(float x, float y) {
        set(x, y);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
