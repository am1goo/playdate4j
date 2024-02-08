package com.am1goo.playdate4j.sdk;

public class FilesystemBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

	public native String getError();
}