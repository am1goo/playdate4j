package com.am1goo.playdate4j.sdk;

public class SoundBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

	public native int getCurrentTime();
}
