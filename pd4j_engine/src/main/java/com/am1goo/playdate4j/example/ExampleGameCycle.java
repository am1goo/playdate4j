package com.am1goo.playdate4j.example;

import com.am1goo.playdate4j.sdk.*;
import com.am1goo.playdate4j.sdk.Input.Peripherals;

public class ExampleGameCycle implements GameCycle {

    private static final int TEXT_WIDTH = 86;
    private static final int TEXT_HEIGHT = 16;

    int x = (400 - TEXT_WIDTH) / 2;
    int y = (240 - TEXT_HEIGHT) / 2;
    int dx = 1;
    int dy = 2;

    int lcd_columns;
    int lcd_rows;

    @Override
    public void start() {
        lcd_columns = Graphics.getLCDColumns();
        lcd_rows = Graphics.getLCDRows();
        Display.setRefreshRate(35);
        Graphics.setDrawMode(Graphics.DrawMode.Copy);
        Input.setPeripheralsEnabled(Peripherals.None);
        Sys.setAutoLockDisabled(false);
        Sys.setCrankSoundsDisabled(false);
        Sys.setCrankSoundsDisabled(false);
    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        Graphics.clear(Graphics.SolidColor.White);
        Graphics.drawText("Hello Yoba!", x, y);

        x += dx;
        y += dy;

        if ( x < 0 || x > lcd_columns - TEXT_WIDTH )
            dx = -dx;

        if ( y < 0 || y > lcd_rows - TEXT_HEIGHT )
            dy = -dy;

        Sys.drawFps(20, 0);
    }
}
