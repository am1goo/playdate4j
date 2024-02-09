package com.am1goo.playdate4j.sdk;

import java.util.ArrayList;
import java.util.List;

public class Graphics {

    private static final GraphicsBridge bridge = new GraphicsBridge();

	private static final List<LCDBitmap> bitmaps = new ArrayList<>();

    private static int lcdColumns = -1;
    private static int lcdRows = -1;
    private static int lcdRowSize = -1;

    public static int getLCDColumns() {
        if (lcdColumns < 0)
            lcdColumns = bridge.getLCDColumns();
        return lcdColumns;
    }

    public static int getLCDRows() {
        if (lcdRows < 0)
            lcdRows = bridge.getLCDRows();
        return lcdRows;
    }

    public static int getLCDRowSize() {
        if (lcdRowSize < 0)
            lcdRowSize = bridge.getLCDRowSize();
        return lcdRowSize;
    }

    public static void clear(LCDSolidColor color) {
        bridge.clear(color.getValue());
    }

    public static void setDrawMode(LCDDrawMode mode) {
        bridge.setDrawMode(mode.getValue());
    }

    public static void pushContext(LCDBitmap bitmap) {
        if (bitmap == null)
            return;

        bridge.pushContext(bitmap.ptr.getValue());
    }

    public static void popContext() {
        bridge.popContext();
    }

    public static void setStencil(LCDBitmap bitmap) {
        if (bitmap == null)
            return;

        bridge.setStencil(bitmap.ptr.getValue());
    }

    public static void setStencilImage(LCDBitmap bitmap, int tile) {
        if (bitmap == null)
            return;

        bridge.setStencilImage(bitmap.ptr.getValue(), tile);
    }

    public static void drawText(String text, int x, int y) {
        bridge.drawText(text, x, y);
    }

    public static void setClipRect(int x, int y, int width, int height) {
        bridge.setClipRect(x, y, width, height);
    }

    public static void setScreenClipRect(int x, int y, int width, int height) {
        bridge.setScreenClipRect(x, y, width, height);
    }

    public static void clearClipRect() {
        bridge.clearClipRect();
    }

    public static void setLineCapStyle(LCDLineCapStyle endCapStyle) {
        bridge.setLineCapStyle(endCapStyle.getValue());
    }

    public static LCDFont loadFont(String path) {
        long ptr = bridge.loadFont(path);
        Api.Pointer pointer = new Api.Pointer(ptr);
        if (pointer.invalid())
            return null;

        return new LCDFont(pointer, path);
    }

    public static void setFont(LCDFont font) {
        if (font == null)
            return;

        bridge.setFont(font.ptr.getValue());
    }

    public static void setTextTracking(int tracking) {
        bridge.setTextTracking(tracking);
    }

    public static int getTextTracking() {
        return bridge.getTextTracking();
    }

    public static void clearBitmap(LCDBitmap bitmap, LCDSolidColor color) {
        if (bitmap == null)
            return;

        bridge.clearBitmap(bitmap.ptr.getValue(), color.getValue());
    }

    public static LCDBitmap copyBitmap(LCDBitmap bitmap) {
        if (bitmap == null)
            return null;

        long copyPtr = bridge.copyBitmap(bitmap.ptr.getValue());
        Api.Pointer copyPointer = new Api.Pointer(copyPtr);
        if (copyPointer.invalid())
            return null;

        LCDBitmap copy = new LCDBitmap(copyPointer, bitmap.getPath());
        bitmaps.add(copy);
        return copy;
    }

    public static void drawBitmap(LCDBitmap bitmap, int x, int y, LCDBitmapFlip flip) {
        if (bitmap == null)
            return;

        bridge.drawBitmap(bitmap.ptr.getValue(), x, y, flip.getValue());
    }

    public static void drawScaledBitmap(LCDBitmap bitmap, int x, int y, float xScale, float yScale) {
        if (bitmap == null)
            return;

        bridge.drawScaledBitmap(bitmap.ptr.getValue(), x, y, xScale, yScale);
    }

    public static void drawRotatedBitmap(LCDBitmap bitmap, int x, int y, float degrees, float xCenter, float yCenter, float xScale, float yScale) {
        if (bitmap == null)
            return;

        bridge.drawRotatedBitmap(bitmap.ptr.getValue(), x, y, degrees, xCenter, yCenter, xScale, yScale);
    }

    public static LCDBitmap freeBitmap(LCDBitmap bitmap) {
        bridge.freeBitmap(bitmap.ptr.getValue());
        bitmap.ptr.invalidate();
        bitmaps.remove(bitmap);
        return null;
    }

    public static LCDBitmap loadBitmap(String path) {
        long ptr = bridge.loadBitmap(path);
        Api.Pointer pointer = new Api.Pointer(ptr);
        if (pointer.invalid())
            return null;

        LCDBitmap bitmap = new LCDBitmap(pointer, path);
        bitmaps.add(bitmap);
        return bitmap;
    }
        
    public static LCDBitmap findBitmap(long ptr) {
    	for (LCDBitmap bitmap : bitmaps) {
    		if (bitmap.ptr.invalid())
    			continue;
    		
    		if (bitmap.ptr.getValue() != ptr)
    			continue;
    		
    		return bitmap;
    	}
    	return null;
    }

    public enum LCDSolidColor {
        Black(0),
        White(1),
        Clear(2),
        XOR(3);

        final int value;

        LCDSolidColor (int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum LCDDrawMode {
        Copy(0),
        WhiteTransparent(1),
        BlackTransparent(2),
        FillWhite(3),
        FillBlack(4),
        XOR(5),
        NXOR(6),
        Inverted(7);

        final int value;

        LCDDrawMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
	public enum LCDBitmapFlip {
		Unflipped(0),
		FlippedX(1),
		FlippedY(2),
		FlippedXY(3);
		
		int value;
		
		LCDBitmapFlip(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
		public static LCDBitmapFlip valueOf(int value) {
			for (LCDBitmapFlip flip : values()) {
				if (flip.getValue() == value) {
					return flip;
				}
			}
			return null;
		}
	}

    public static class LCDFont {

        private Api.Pointer ptr;
        private String path;

        public LCDFont(Api.Pointer ptr, String path) {
            this.ptr = ptr;
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    public static class LCDBitmap {

        private final Api.Pointer ptr;
        private final String path;

        public LCDBitmap(Api.Pointer ptr, String path) {
            this.ptr = ptr;
            this.path = path;
        }

        public Api.Pointer getPointer() {
            return ptr;
        }

        public String getPath() {
            return path;
        }

        public void free() {
            if (ptr.invalid())
                return;

            Graphics.freeBitmap(this);
        }
    }

    public enum LCDLineCapStyle {
        Butt(0),
        Square(1),
        Round(2);

        final int value;

        LCDLineCapStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
	
	public class LCDRect {

		private int left;
		private int right;
		private int top;
		private int bottom;
		
		public int left() {
			return left;
		}
		
		public int right() {
			return right;
		}
		
		public int top() {
			return top;
		}
		
		public int bottom() {
			return bottom;
		}
	}
}
