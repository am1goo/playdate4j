package com.am1goo.playdate4j.example;

import com.am1goo.playdate4j.example.game.Ship;
import com.am1goo.playdate4j.example.game.World;
import com.am1goo.playdate4j.sdk.*;
import com.am1goo.playdate4j.sdk.Input.PDPeripherals;

public class ExampleGameCycle implements GameCycle {

    private static final int TEXT_WIDTH = 86;
    private static final int TEXT_HEIGHT = 16;

    private World world;
    private Ship player;
    private Ship enemy;

    private Graphics.LCDBitmap menuBackground;
    private Graphics.LCDFont font;
    private Sound.SoundChannel soundChannel;
    private Sound.FilePlayer soundFilePlayer;
    private Sound.AudioSample soundAudioSample;

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
        Display.setRefreshRate(50);
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
            Sys.log("start: [Graphics] font " + font.getPath() + " loaded");
        }

        menuBackground = Graphics.loadBitmap("images/background");
        Sys.setMenuImage(menuBackground, 0);

        world = new World();

        player = new Ship("us_hurricane");
        player.load();
        player.position().set(0, 0);

        enemy = new Ship("us_hurricane");
        enemy.load();
        enemy.position().set(70, 80);

        world.setPivot(player);

        soundChannel = Sound.newChannel();
        Sound.addChannel(soundChannel);
        if (soundChannel != null) {
            float soundChannelVolume = soundChannel.getVolume();
            Sys.log("start: [Sound.SoundChannel] volume=" + soundChannelVolume);
            soundChannel.setVolume(soundChannelVolume * 0.5f);
        }

        soundFilePlayer = Sound.newPlayer();
        if (soundFilePlayer != null) {
            soundFilePlayer.play(0);
            boolean soundFilePlayerIsPlaying = soundFilePlayer.isPlaying();
            Sys.log("start: [Sound.FilePlayer] isPlaying=" + soundFilePlayerIsPlaying);
            soundFilePlayer.pause();
            soundFilePlayerIsPlaying = soundFilePlayer.isPlaying();
            Sys.log("start: [Sound.FilePlayer] isPlaying=" + soundFilePlayerIsPlaying);
            float soundFilePlayerLength = soundFilePlayer.getLength();
            Sys.log("start: [Sound.FilePlayer] length=" + soundFilePlayerLength);
        }

        soundAudioSample = Sound.newSampleBuffer(10);
        if (soundAudioSample != null) {
            float soundAudioSampleLength = soundAudioSample.getLength();
            Sys.log("start: [Sound.AudioSample] length=" + soundAudioSampleLength);
        }
    }

    @Override
    public void stop() {
        if (soundChannel != null) {
            Sound.removeChannel(soundChannel);
            soundChannel.free();
            soundChannel = null;
        }

        if (soundFilePlayer != null) {
            soundFilePlayer.free();
            soundFilePlayer = null;
        }

        if (soundAudioSample != null) {
            soundAudioSample.free();
            soundAudioSample = null;
        }

        if (player != null) {
            player.free();
            player = null;
        }

        if (enemy != null) {
            enemy.free();
            enemy = null;
        }

        Sys.setMenuImage(null, 0);
        if (menuBackground != null) {
            menuBackground.free();
            menuBackground = null;
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

        player.addInput(xDir, yDir);
        world.update(player);
        world.update(enemy);

        Sprite.updateAndDrawSprites();

        Graphics.setFont(font);
        Graphics.drawText("dt: " + Game.getDeltaTime(), 0, 20);
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
