package com.am1goo.playdate4j;

import com.am1goo.playdate4j.sdk.Display;
import com.am1goo.playdate4j.sdk.Graphics;
import com.am1goo.playdate4j.sdk.Sys;

public class Main {

    public static void main(String[] args) {
        Display.setRefreshRate(50);
        Graphics.setDrawMode(Graphics.DrawMode.BlackTransparent);
        Sys.logToConsole("test log");
        Sys.error("test error");
        Sys.drawFps(0, 0);
    }
}