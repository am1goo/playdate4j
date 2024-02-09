package com.am1goo.playdate4j.sdk;

public class SysBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    public native void logToConsole(String log);
    public native void error(String error);
    public native void drawFps(int x, int y);
    public native boolean getFlipped();
    public native boolean getReduceFlashing();
    public native void setMenuImage(long bitmapPtr, int xOffset);
    public native float getBatteryPercentage();
    public native float getBatteryVoltage();
    public native void clearICache();

    public native long getCurrentTimeMilliseconds();
    public native long getSecondsSinceEpoch(long milliseconds);
    public native void resetElapsedTime();
    public native float getElapsedTime();
    public native int getTimezoneOffset();
    public native boolean shouldDisplay24HourTime();

    public native void setAutoLockDisabled(boolean disable);
    public native boolean setCrankSoundsDisabled(boolean disable);
}
