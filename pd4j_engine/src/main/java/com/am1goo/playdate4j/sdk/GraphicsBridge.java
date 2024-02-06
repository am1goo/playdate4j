package com.am1goo.playdate4j.sdk;

public class GraphicsBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    public native int getLCDColumns();
    public native int getLCDRows();
    public native int getLCDRowSize();

    public native void clear(int mode);
    public native void setDrawMode(int mode);
    public native void drawText(String text, int x, int y);
}
