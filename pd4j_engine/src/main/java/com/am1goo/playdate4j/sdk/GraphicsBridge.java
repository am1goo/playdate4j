package com.am1goo.playdate4j.sdk;

import com.am1goo.playdate4j.sdk.Graphics.PDStringEncoding;

public class GraphicsBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    /* graphics */
    public native int getLCDColumns();
    public native int getLCDRows();
    public native int getLCDRowSize();

    public native void pushContext(long bitmapPtr);
    public native void popContext();
    public native void setStencil(long bitmapPtr);
    public native void setStencilImage(long bitmapPtr, int tile);
    public native void setDrawMode(int mode);
    public native void setClipRect(int x, int y, int width, int height);
    public native void setScreenClipRect(int x, int y, int width, int height);
    public native void clearClipRect();
    public native void setLineCapStyle(int endCapStyle);

    /* bitmaps */
    public native void clearBitmap(long bitmapPtr, int color);
    public native long copyBitmap(long bitmapPtr);
    
    public native boolean checkMaskCollision(long bitmapPtr1, int x1, int y1, int flip1, long bitmapPtr2, int x2, int y2, int flip2, int rectLeft, int rectRight, int rectTop, int rectBottom);

    public native void drawBitmap(long bitmapPtr, int x, int y, int flip);
    public native void drawScaledBitmap(long bitmapPtr, int x, int y, float xScale, float yScale);
    public native void drawRotatedBitmap(long bitmapPtr, int x, int y, float degrees, float xCenter, float yCenter, float xScale, float yScale);

    public native void freeBitmap(long bitmapPtr);
    public native long loadBitmap(String path);
    public native boolean loadIntoBitmap(String path, long bitmapPtr);
    public native long newBitmap(int width, int height, int color);
    public native void tileBitmap(long bitmapPtr, int x, int y, int width, int height, int flip);
    public native long rotatedBitmap(long bitmapPtr, float rotation, float xScale, float yScale);
    public native boolean setBitmapMask(long bitmapPtr, long maskPtr);
    public native long getBitmapMask(long bitmapPtr);
    
    /* bitmap tables */
    public native long newBitmapTable(int count, int width, int height);
    public native void freeBitmapTable(long tablePtr);
    public native long getTableBitmap(long tablePtr, int idx);
    public native long loadBitmapTable(String path);
    public native void loadIntoBitmapTable(String path, long tablePtr);

    /* fonts & text */
    public native void setFont(long fontPtr);
    public native void setTextTracking(int tracking);
    public native int getTextTracking();
    public native void setTextLeading(int leading);
    public native void drawText(String text, int len, int encoding, int x, int y);
    public native short getFontHeight(long fontPtr);
    public native long getFontPage(long fontPtr, long c);
    public native long getPageGlyph(long pagePtr, long c);
    public native int getGlyphKerning(long glyphPtr, long c1, long c2);
    public native int getTextWidth(long fontPtr, String text, int len, int encoding, int tracking);
    public native long loadFont(String path);

    /* geometry */
    public native void drawEllipse(int x, int y, int width, int height, int lineWidth, float startAngle, float endAngle, int color);
    public native void fillEllipse(int x, int y, int width, int height, float startAngle, float endAngle, int color);
    public native void drawLine(int x1, int y1, int x2, int y2, int width, int color);
    public native void drawRect(int x, int y, int width, int height, int color);
    public native void fillRect(int x, int y, int width, int height, int color);
    public native void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int color);
    public native void fillPolygon(int nPoints, int[] points, int color, int fillrule);
    
    /* miscellaneous */
    public native void clear(int mode);
    public native void setBackgroundColor(int color);
    public native void display();
    public native long getDebugBitmap();
    public native long getDisplayBufferBitmap();
    public native long copyFrameBufferBitmap();
    public native void markUpdatedRows(int start, int end);
    public native void setDrawOffset(int dx, int dy);
}
