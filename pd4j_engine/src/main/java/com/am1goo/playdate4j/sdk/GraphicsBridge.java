package com.am1goo.playdate4j.sdk;

public class GraphicsBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    public native int getLCDColumns();
    public native int getLCDRows();
    public native int getLCDRowSize();

    public native void clear(int mode);

    public native void pushContext(long bitmapPtr);
    public native void popContext();
    public native void setStencil(long bitmapPtr);
    public native void setStencilImage(long bitmapPtr, int tile);
    public native void setDrawMode(int mode);
    public native void setClipRect(int x, int y, int width, int height);
    public native void setScreenClipRect(int x, int y, int width, int height);
    public native void clearClipRect();
    public native void setLineCapStyle(int endCapStyle);

    public native void drawText(String text, int x, int y);

    public native long loadFont(String path);
    public native void setFont(long fontPtr);
    public native void setTextTracking(int tracking);
    public native int getTextTracking();

    public native void clearBitmap(long bitmapPtr, int color);
    public native long copyBitmap(long bitmapPtr);

    public native void drawBitmap(long bitmapPtr, int x, int y, int flip);
    public native void drawScaledBitmap(long bitmapPtr, int x, int y, float xScale, float yScale);
    public native void drawRotatedBitmap(long bitmapPtr, int x, int y, float degrees, float xCenter, float yCenter, float xScale, float yScale);

    public native void freeBitmap(long bitmapPtr);
    public native long loadBitmap(String path);
}
