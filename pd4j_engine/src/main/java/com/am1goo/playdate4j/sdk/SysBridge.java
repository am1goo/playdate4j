package com.am1goo.playdate4j.sdk;

public class SysBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    public native void logToConsole(String log);
    public native void error(String error);
    public native void drawFps(int x, int y);
    public native void setAutoLockDisabled(boolean disable);
    public native boolean setCrankSoundsDisabled(boolean disable);
}
