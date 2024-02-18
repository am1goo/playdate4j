package com.am1goo.playdate4j.engine.ui.controls;

public class Rect {

    private int x;
    private int y;
    private int width;
    private int height;

    public Rect(int x, int y, int width, int height) {
        set(x, y, width, height);
    }

    public void set(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int xMin() {
        return x;
    }

    public int yMin() {
        return y;
    }

    public int xMax() {
        return xMin() + width;
    }

    public int yMax() {
        return yMin() + height;
    }

    public int xCenter() {
        return xMin() + width / 2;
    }

    public int yCenter() {
        return yMin() + height / 2;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
