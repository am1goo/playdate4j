package com.am1goo.playdate4j.engine.ui.controls;

public class Rect {

    public int x;
    public int y;
    public int width;
    public int height;

    public Rect(int x, int y, int width, int height) {
        set(x, y, width, height);
    }

    public void set(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int xCenter() {
        return x + width / 2;
    }

    public int yCenter() {
        return y + height / 2;
    }
}
