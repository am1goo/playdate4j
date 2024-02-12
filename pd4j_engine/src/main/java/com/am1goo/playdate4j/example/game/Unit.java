package com.am1goo.playdate4j.example.game;

import com.am1goo.playdate4j.example.game.coords.Position;

public abstract class Unit {

    private final Position position;

    protected Unit() {
        position = new Position();
    }

    public Position position() {
        return position;
    }

    public abstract void moveTo(float x, float y);
}
