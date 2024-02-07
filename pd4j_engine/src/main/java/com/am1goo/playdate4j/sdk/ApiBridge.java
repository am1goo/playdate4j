package com.am1goo.playdate4j.sdk;

public class ApiBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    public native boolean isApiAvailable();
}
