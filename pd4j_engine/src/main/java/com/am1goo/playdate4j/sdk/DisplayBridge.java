package com.am1goo.playdate4j.sdk;

public class DisplayBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    public native void setRefreshRate(int rate);
}