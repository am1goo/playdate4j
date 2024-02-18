package com.am1goo.playdate4j.engine.render;

import com.am1goo.playdate4j.sdk.Graphics;
import com.am1goo.playdate4j.sdk.Sprite;
import com.am1goo.playdate4j.sdk.Sys;

public class JRenderManager {

    public static void start() {
        Graphics.setBackgroundColor(Graphics.LCDSolidColor.Clear);
    }

    public static void stop() {
        //do nothing
    }

    public static void beforeLoop() {
        Graphics.clear(Graphics.LCDSolidColor.White);
    }

    public static void afterLoop() {
        Sprite.drawSprites();
        Sys.drawFps(0, 0);
    }
}
