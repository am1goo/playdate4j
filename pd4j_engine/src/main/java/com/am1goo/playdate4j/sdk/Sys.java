package com.am1goo.playdate4j.sdk;

public class Sys {

    private static final SysBridge bridge = new SysBridge();

    public static void logToConsole(String log) {
        bridge.logToConsole(log);
    }

    public static void error(String error) {
        bridge.error(error);
    }

    public static void drawFps(int x, int y) {
        bridge.drawFps(x, y);
    }
}
