package com.am1goo.playdate4j.sdk;

public class SysBridge {

    static {
        Settings.loadLibraries();
    }

    public native void logToConsole(String log);
    public native void error(String error);
    public native void drawFps(int x, int y);
}
