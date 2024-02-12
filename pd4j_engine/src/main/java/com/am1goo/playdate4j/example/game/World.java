package com.am1goo.playdate4j.example.game;

import com.am1goo.playdate4j.example.game.coords.Position;
import com.am1goo.playdate4j.sdk.Graphics;

public class World {

    private Unit pivot;

    private final int screenWidth;
    private final int screenHeight;

    private final Position screenCenter;

    public World() {
        screenWidth = Graphics.getLCDColumns();
        screenHeight = Graphics.getLCDRows();

        screenCenter = new Position(screenWidth, screenHeight);
    }

    public void setPivot(Unit pivot) {
        this.pivot = pivot;
    }

    public void update(Unit unit) {
        screenCenter.set(screenWidth / 2, screenHeight / 2);

        float dx;
        float dy;
        if (unit == pivot) {
            dx = 0;
            dy = 0;
        }
        else {
            Position pivotPos = Position.zero;
            if (pivot != null)
                pivotPos = pivot.position();

            dx = unit.position().x() - pivotPos.x();
            dy = unit.position().y() - pivotPos.y();
        }
        unit.moveTo(screenCenter.x() + dx, screenCenter.y() + dy);
    }
}
