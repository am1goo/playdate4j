package com.am1goo.playdate4j.sdk;

public class DisplayBridge {

    static {
        System.loadLibrary(Settings.LIBRARY_NAME);
    }

    public native void setRefreshRate(int rate);
}