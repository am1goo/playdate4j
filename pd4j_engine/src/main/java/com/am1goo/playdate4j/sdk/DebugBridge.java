package com.am1goo.playdate4j.sdk;

public class DebugBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    public native boolean isApiAvailable();
}
