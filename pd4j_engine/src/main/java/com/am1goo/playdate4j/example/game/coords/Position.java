package com.am1goo.playdate4j.example.game.coords;

public class Position {

    public static final Position zero = new Position();

    private float x;
    private float y;

    public Position() {
        this(0, 0);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(float dx, float dy) {
        set(x + dx, y + dy);
    }
}
