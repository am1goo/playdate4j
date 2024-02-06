package com.am1goo.playdate4j.sdk;

public class GraphicsBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    public native void clear(int mode);
    public native void setDrawMode(int mode);
    public native void drawText(String text, int x, int y);
}
