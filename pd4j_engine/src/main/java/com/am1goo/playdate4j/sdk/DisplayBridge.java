package com.am1goo.playdate4j.sdk;

public class DisplayBridge {

    static {
        Settings.loadLibraries();
    }

    public native void setRefreshRate(int rate);
}