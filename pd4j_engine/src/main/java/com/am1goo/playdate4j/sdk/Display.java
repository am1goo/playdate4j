package com.am1goo.playdate4j.sdk;

public class Display {

    private static final DisplayBridge bridge = new DisplayBridge();

    public static void setRefreshRate(int rate) {
        bridge.setRefreshRate(rate);
    }
}
