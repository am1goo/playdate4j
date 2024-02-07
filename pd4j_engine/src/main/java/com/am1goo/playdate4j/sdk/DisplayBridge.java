package com.am1goo.playdate4j.sdk;

public class DisplayBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    public native int getHeight();
    public native int getWidth();
    public native void setInverted(boolean value);
    public native void setMosaic(int x, int y);
    public native void setFlipped(int x, int y);
    public native void setRefreshRate(int rate);
    public native void setScale(int value);
    public native void setOffset(int dx, int dy);
}