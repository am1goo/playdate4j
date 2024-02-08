package com.am1goo.playdate4j.example;

import com.am1goo.playdate4j.sdk.*;
import com.am1goo.playdate4j.sdk.Input.PDPeripherals;

public class ExampleGameCycle implements GameCycle {

    private static final int TEXT_WIDTH = 86;
    private static final int TEXT_HEIGHT = 16;

    private Graphics.LCDFont font;
    private Graphics.LCDBitmap playerBitmap;
    private Sprite.LCDSprite player;

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
        Graphics.setDrawMode(Graphics.LCDDrawMode.Copy);
        Input.setPeripheralsEnabled(PDPeripherals.None);
        boolean flipped = Sys.getFlipped();
        Sys.log("start: [Sys] flipped=" + flipped);
        boolean reduceFlashing = Sys.getReduceFlashing();
        Sys.log("start: [Sys] reduceFlashing=" + reduceFlashing);
        float batteryPercentage = Sys.getBatteryPercentage();
        Sys.log("start: [Sys] batteryPercentage=" + batteryPercentage);
        float batteryVoltage = Sys.getBatteryVoltage();
        Sys.log("start: [Sys] batteryVoltage=" + batteryVoltage);
        Sys.clearICache();
        long millis = Sys.getCurrentTimeMilliseconds();
        Sys.log("start: [Sys] millis=" + millis);
        long secondsSinceEpoch = Sys.getSecondsSinceEpoch(millis);
        Sys.log("start: [Sys] secondsSinceEpoch=" + secondsSinceEpoch);
        int timezone = Sys.getTimezoneOffset();
        Sys.log("start: [Sys] timezone=" + timezone);
        float elapsedTime = Sys.getElapsedTime();
        Sys.log("start: [Sys] elapsedTime=" + elapsedTime);
        Sys.resetElapsedTime();
        boolean shouldDisplay24HourTime = Sys.shouldDisplay24HourTime();
        Sys.log("start: [Sys] shouldDisplay24HourTime=" + shouldDisplay24HourTime);

        Sys.setAutoLockDisabled(false);
        Sys.setCrankSoundsDisabled(false);
        int currentTime = Sound.getCurrentTime();
        Sys.log("start: [Sound] currentTime=" + currentTime);

        Graphics.setTextTracking(1);
        int textTracking = Graphics.getTextTracking();
        Sys.log("start: [Graphics] textTracking=" +textTracking);

        font = Graphics.loadFont("/System/Fonts/Asheville-Sans-14-Bold.pft");
        if (font != null) {
            Sys.log("start: font " + font.getPath() + " loaded");
        }

        playerBitmap = Graphics.loadBitmap("images/player");
        player = Sprite.newSprite();
        player.setPosition(lcd_columns / 2, lcd_rows / 2);
        player.setImage(playerBitmap, Graphics.LCDBitmapFlip.Unflipped);
        Sprite.addSprite(player);
    }

    @Override
    public void stop() {
        if (player != null) {
            Sprite.removeSprite(player);
            Sprite.freeSprite(player);
            player = null;
        }
        if (playerBitmap != null) {
            Graphics.freeBitmap(playerBitmap);
            playerBitmap = null;
        }
    }

    @Override
    public void loop() {
        Graphics.clear(Graphics.LCDSolidColor.White);
        int xDir = 0;
        int yDir = 0;
        if (Input.isButton(Input.PDButtons.Up)) {
            yDir--;
        }
        if (Input.isButton(Input.PDButtons.Down)) {
            yDir++;
        }
        if (Input.isButton(Input.PDButtons.Left)) {
            xDir--;
        }
        if (Input.isButton(Input.PDButtons.Right)) {
            xDir++;
        }

        float deltaTime = Game.getDeltaTime();
        float deltaX = 100 * xDir * deltaTime;
        float deltaY = 100 * yDir * deltaTime;
        player.deltaPosition(deltaX, deltaY);

        Sprite.updateAndDrawSprites();

        Graphics.setFont(font);
        Graphics.drawText("dt: " + deltaTime, 0, 20);
        Graphics.drawText("Hello Yoba!", x, y);

        x += dx;
        y += dy;

        if ( x < 0 || x > lcd_columns - TEXT_WIDTH )
            dx = -dx;

        if ( y < 0 || y > lcd_rows - TEXT_HEIGHT )
            dy = -dy;

        Sys.drawFps(0, 0);
    }
}
