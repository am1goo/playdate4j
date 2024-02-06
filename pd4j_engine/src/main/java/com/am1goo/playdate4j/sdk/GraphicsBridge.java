package com.am1goo.playdate4j.sdk;

public class GraphicsBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    public native void setDrawMode(int mode);
}
