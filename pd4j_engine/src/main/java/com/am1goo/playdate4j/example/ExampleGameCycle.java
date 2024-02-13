package com.am1goo.playdate4j.example;

import com.am1goo.playdate4j.example.game.Ship;
import com.am1goo.playdate4j.example.game.World;
import com.am1goo.playdate4j.sdk.*;
import com.am1goo.playdate4j.sdk.Graphics.PDStringEncoding;
import com.am1goo.playdate4j.sdk.Input.PDPeripherals;

public class ExampleGameCycle implements GameCycle {

    private static final int TEXT_WIDTH = 86;
    private static final int TEXT_HEIGHT = 16;

    private World world;
    private Ship player;
    private Ship enemy;

    private Graphics.LCDBitmap menuBackground;
    private Graphics.LCDFont font;

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

        Sound.SoundChannel channel = Sound.newChannel();
        if (channel != null) {
            Sound.addChannel(channel);
            float volume = channel.getVolume();
            Sys.log("start: [Sound.SoundChannel] volume=" + volume);
            channel.setVolume(volume * 0.5f);
            Sound.removeChannel(channel);
            channel.free();
            channel = null;
        }

        Sound.FilePlayer filePlayer = Sound.newFilePlayer();
        if (filePlayer != null) {
            filePlayer.play(0);
            boolean isPlaying = filePlayer.isPlaying();
            Sys.log("start: [Sound.FilePlayer] isPlaying=" + isPlaying);
            filePlayer.pause();
            isPlaying = filePlayer.isPlaying();
            Sys.log("start: [Sound.FilePlayer] isPlaying=" + isPlaying);
            float length = filePlayer.getLength();
            Sys.log("start: [Sound.FilePlayer] length=" + length);
            filePlayer.free();
            filePlayer = null;
        }

        Sound.AudioSample audioSample = Sound.newSampleBuffer(10);
        if (audioSample != null) {
            float length = audioSample.getLength();
            Sys.log("start: [Sound.AudioSample] length=" + length);
            audioSample.free();
            audioSample = null;
        }

        Sound.SamplePlayer samplePlayer = Sound.newSamplePlayer();
        if (samplePlayer != null) {
            float length = samplePlayer.getLength();
            Sys.log("start: [Sound.SamplePlayer] length=" + length);
            samplePlayer.free();
            samplePlayer = null;
        }

        Sound.PDSynthEnvelope envelope = Sound.newEnvelope(0, 0, 0, 0);
        if (envelope != null) {
            float value = envelope.getValue();
            Sys.log("start: [Sound.PDSynthEnvelop] value=" + value);
            envelope.free();
            envelope = null;
        }

        Sound.PDSynthLFO lfo = Sound.newLFO(Sound.LFOType.Square);
        if (lfo != null) {
            float value = lfo.getValue();
            Sys.log("start: [Sound.PDSynthLFO] value=" + value);
            lfo.free();
            lfo = null;
        }

        Sound.PDSynth synth = Sound.newSynth();
        if (synth != null) {
            int parameterCount = synth.getParameterCount();
            Sys.log("start: [Sound.PDSynth] parameterCount=" + parameterCount);
            synth.free();
            synth = null;
        }

        Video.LCDVideoPlayer video = Video.loadVideo("test_path");
        if (video != null) {
            VideoBridge.PDVideoInfo info = video.getInfo();
            Sys.log("start: [Video.LCDVideoPlayer] width=" + info.width() + ", height=" + info.height());
            video.free();
            video = null;
        }
    }

    @Override
    public void stop() {
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
        Graphics.drawText("dt: " + Game.getDeltaTime(), PDStringEncoding.ASCIIEncoding, 0, 20);
        Graphics.drawText("Hello Yoba!", PDStringEncoding.ASCIIEncoding, x, y);

        x += dx;
        y += dy;

        if ( x < 0 || x > lcd_columns - TEXT_WIDTH )
            dx = -dx;

        if ( y < 0 || y > lcd_rows - TEXT_HEIGHT )
            dy = -dy;

        Sys.drawFps(0, 0);
    }
}
