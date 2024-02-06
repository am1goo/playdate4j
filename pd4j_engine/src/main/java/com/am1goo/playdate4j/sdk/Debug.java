package com.am1goo.playdate4j.sdk;

public class Debug {

    private static final DebugBridge bridge = new DebugBridge();

    public static boolean isApiAvailable() {
        return bridge.isApiAvailable();
    }
}
