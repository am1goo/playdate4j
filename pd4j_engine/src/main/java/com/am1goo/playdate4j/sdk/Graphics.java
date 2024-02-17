package com.am1goo.playdate4j.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Graphics {

    private static final GraphicsBridge bridge = new GraphicsBridge();

	private static final List<LCDBitmap> bitmaps = new ArrayList<>();
	private static final List<LCDBitmapTable> bitmapTables = new ArrayList<>();
	private static final List<LCDFont> fonts = new ArrayList<>();
    private static LCDFont currentFont = null;
	private static LCDBitmap displayBuffer = null;

    public static final String systemFontsFolder = "/System/Fonts";

    private static int lcdColumns = -1;
    private static int lcdRows = -1;
    private static int lcdRowSize = -1;

    /* graphics */
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

    /* bitmaps */
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
    
    public static boolean checkMaskCollision(LCDBitmap bitmap1, int x1, int y1, LCDBitmapFlip flip1, LCDBitmap bitmap2, int x2, int y2, LCDBitmapFlip flip2, LCDRect rect) {
    	if (bitmap1 == null)
    		return false;
    	
    	if (bitmap2 == null)
    		return false;
    	
    	return bridge.checkMaskCollision(bitmap1.getPointer().getValue(), x1, y1, flip1.getValue(), bitmap2.getPointer().getValue(), x2, y2, flip2.getValue(), rect.left(), rect.right(), rect.top(), rect.bottom());
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
		if (bitmap == null)
			return null;
		
    	if (bitmap.ptr.invalid())
    		return null;
    	
        bridge.freeBitmap(bitmap.ptr.getValue());
        bitmap.ptr.invalidate();
        bitmaps.remove(bitmap);
        return null;
    }

    public static LCDBitmap loadBitmap(String path) {
    	LCDBitmap found = findBitmap(path);
    	if (found != null)
    		return found;
    	
        long ptr = bridge.loadBitmap(path);
        Api.Pointer pointer = new Api.Pointer(ptr);
        if (pointer.invalid())
            return null;

        LCDBitmap bitmap = new LCDBitmap(pointer, path);
        bitmaps.add(bitmap);
        return bitmap;
    }
    
    public static boolean loadIntoBitmap(String path, LCDBitmap bitmap) {
    	boolean loaded = bridge.loadIntoBitmap(path, bitmap.getPointer().getValue());
    	if (loaded) {
    		bitmap.path = path;
    	}
    	return loaded;
    }
    
    public static LCDBitmap newBitmap(int width, int height, LCDSolidColor color) {
    	long ptr = bridge.newBitmap(width, height, color.getValue());
    	LCDBitmap found = findBitmap(ptr);
    	if (found != null)
    		return found;
    	
    	Api.Pointer pointer = new Api.Pointer(ptr);
    	if (pointer.invalid())
    		return null;
    	
    	LCDBitmap bitmap = new LCDBitmap(pointer, null);
    	bitmaps.add(bitmap);
    	return bitmap;
    }
    
    public static void tileBitmap(LCDBitmap bitmap, int x, int y, int width, int height, LCDBitmapFlip flip) {
    	if (bitmap == null)
    		return;
    	
    	bridge.tileBitmap(bitmap.getPointer().getValue(), x, y, width, height, flip.getValue());
    }
    
    public static LCDBitmap rotatedBitmap(LCDBitmap bitmap, float rotation, float xScale, float yScale) {
    	if (bitmap == null)
    		return null;
    	
    	if (bitmap.ptr.invalid())
    		return null;
    	
    	long ptr = bridge.rotatedBitmap(bitmap.ptr.getValue(), rotation, xScale, yScale);
    	Api.Pointer pointer = new Api.Pointer(ptr);
    	if (pointer.invalid())
    		return null;
    	
    	LCDBitmap result = new LCDBitmap(pointer, bitmap.getPath());
    	bitmaps.add(result);
    	return result;
    }
    
    public static boolean setBitmapMask(LCDBitmap bitmap, LCDBitmap mask) {
    	if (bitmap == null)
    		return false;
    	
    	if (mask == null)
    		return false;
    	
    	return bridge.setBitmapMask(bitmap.getPointer().getValue(), mask.getPointer().getValue());
    }
    
    public static LCDBitmap getBitmapMask(LCDBitmap bitmap) {
    	if (bitmap == null)
    		return null;
    	
    	if (bitmap.ptr.invalid())
    		return null;
    	
    	long ptr = bridge.getBitmapMask(bitmap.ptr.getValue());
    	Api.Pointer pointer = new Api.Pointer(ptr);
    	if (pointer.invalid())
    		return null;
    	
    	LCDBitmap mask = new LCDBitmap(pointer, null);
    	bitmaps.add(mask);
    	return mask;
    }
    
    public static boolean addBitmap(LCDBitmap bitmap) {
    	if (bitmap == null)
    		return false;
    	
    	LCDBitmap found = findBitmap(bitmap.ptr.getValue());
    	if (found != null)
    		return false;
    	
    	bitmaps.add(bitmap);
    	return true;
    }
        
    public static LCDBitmap findBitmap(long ptr) {
    	for (LCDBitmap bitmap : bitmaps) {
    		if (bitmap.ptr.getValue() == ptr)
    			return bitmap;
    	}
    	return null;
    }
    
    private static LCDBitmap findBitmap(String path) {
    	for (LCDBitmap bitmap : bitmaps) {
    		if (Objects.equals(bitmap.path, path))
    			return bitmap;
    	}
    	return null;
    }
    
    /* bitmap tables */
    public static LCDBitmapTable newBitmapTable(int count, int width, int height) {
    	long ptr = bridge.newBitmapTable(count, width, height);
    	LCDBitmapTable found = findBitmapTable(ptr);
    	if (found != null)
    		return found;
    	
    	Api.Pointer pointer = new Api.Pointer(ptr);
    	if (pointer.invalid())
    		return null;
    	
    	LCDBitmapTable table = new LCDBitmapTable(pointer);
    	bitmapTables.add(table);
    	return table;
    }

    public static LCDBitmapTable freeBitmapTable(LCDBitmapTable table) {
    	if (table == null)
    		return null;
    	
    	if (table.ptr.invalid())
    		return null;
    	
    	bridge.freeBitmapTable(table.ptr.getValue());
    	table.ptr.invalidate();
    	bitmapTables.remove(table);
    	return null;
    }
    
    public static LCDBitmapTable loadBitmapTable(String path) {
    	long ptr = bridge.loadBitmapTable(path);
    	LCDBitmapTable found = findBitmapTable(ptr);
    	if (found != null)
    		return found;
    	
    	Api.Pointer pointer = new Api.Pointer(ptr);
    	if (pointer.invalid())
    		return null;
    	
    	LCDBitmapTable table = new LCDBitmapTable(pointer);
    	bitmapTables.add(table);
    	return table;
    }
    
    public static void loadIntoBitmapTable(String path, LCDBitmapTable table) {
    	if (table == null)
    		return;
    	
    	if (table.ptr.invalid())
    		return;
    	
    	bridge.loadIntoBitmapTable(path, table.ptr.getValue());
    }
    
    private static LCDBitmapTable findBitmapTable(long ptr) {
    	for (LCDBitmapTable table : bitmapTables) {
    		if (table.ptr.getValue() == ptr)
    			return table;
    	}
    	return null;
    }
    
    /* fonts & text */
    public static LCDFont getFont() {
        return currentFont;
    }

    public static void setFont(LCDFont font) {
        if (font == null)
            return;

        bridge.setFont(font.ptr.getValue());
        currentFont = font;
    }

    public static void setTextTracking(int tracking) {
        bridge.setTextTracking(tracking);
    }

    public static int getTextTracking() {
        return bridge.getTextTracking();
    }

    public static void setTextLeading(int leading) {
        bridge.setTextLeading(leading);
    }

    public static void drawText(LCDFont font, String text, PDStringEncoding encoding, int x, int y) {
        LCDFont prevFont = getFont();
        setFont(font);
        drawText(text, encoding, x, y);
        setFont(prevFont);
    }

    public static void drawText(String text, PDStringEncoding encoding, int x, int y) {
    	if (text == null)
    		return;
    	
        bridge.drawText(text, text.length(), encoding.getValue(), x, y);
    }
    
    public static LCDFont loadFont(String path) {
    	LCDFont found = findFont(path);
    	if (found != null)
    		return null;
    	
        long ptr = bridge.loadFont(path);
        found = findFont(ptr);
        Api.Pointer pointer = new Api.Pointer(ptr);
        if (pointer.invalid())
            return null;

        LCDFont font = new LCDFont(pointer, path);
        fonts.add(font);
        return font;
    }
    
    private static LCDFont findFont(String path) {
    	for (LCDFont font : fonts) {
    		if (Objects.equals(font.getPath(), path))
    			return font;
    	}
    	return null;
    }
    
    private static LCDFont findFont(long ptr) {
    	for (LCDFont font : fonts) {
    		if (font.ptr.getValue() == ptr)
    			return font;
    	}
    	return null;
    }
    
    /* geometry */ 
    public static void drawEllipse(int x, int y, int width, int height, int lineWidth, float startAngle, float endAngle, LCDSolidColor color) {
    	bridge.drawEllipse(x, y, width, height, lineWidth, startAngle, endAngle, color.getValue());
    }
    
    public static void fillEllipse(int x, int y, int width, int height, float startAngle, float endAngle, LCDSolidColor color) {
    	bridge.fillEllipse(x, y, width, height, startAngle, endAngle, color.getValue());
    }
    
    public static void drawLine(int x1, int y1, int x2, int y2, int width, LCDSolidColor color) {
    	bridge.drawLine(x1, y1, x2, y2, width, color.getValue());
    }
    
    public static void drawRect(int x, int y, int width, int height, LCDSolidColor color) {
    	bridge.drawRect(x, y, width, height, color.getValue());
    }
    
    public static void fillRect(int x, int y, int width, int height, LCDSolidColor color) {
    	bridge.fillRect(x, y, width, height, color.getValue());
    }
    
    public static void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, LCDSolidColor color) {
    	bridge.fillTriangle(x1, y1, x2, y2, x3, y3, color.getValue());
    }
    
    public void fillPolygon(int nPoints, int[] points, LCDSolidColor color, LCDPolygonFillRule fillrule) {
    	bridge.fillPolygon(nPoints, points, color.getValue(), fillrule.getValue());
    }
    
    /* miscellaneous */
    public static void clear(LCDSolidColor color) {
        bridge.clear(color.getValue());
    }
    
    public static void setBackgroundColor(LCDSolidColor color) {
    	bridge.setBackgroundColor(color.getValue());
    }
    
    public static void display() {
    	bridge.display();
    }
    
    public static LCDBitmap getDebugBitmap() {
    	long ptr = bridge.getDebugBitmap();
    	LCDBitmap found = findBitmap(ptr);
    	if (found != null)
    		return found;
    	
    	Api.Pointer pointer = new Api.Pointer(ptr);
    	if (pointer.invalid())
    		return null;
    	
    	LCDBitmap bitmap = new LCDBitmap(pointer, null);
    	bitmaps.add(bitmap);
    	return bitmap;
    }
    
    public static LCDBitmap getDisplayBufferBitmap() {
    	if (displayBuffer != null)
    		return displayBuffer;
    		
    	long ptr = bridge.getDisplayBufferBitmap();
    	Api.Pointer pointer = new Api.Pointer(ptr);
    	if (pointer.invalid())
    		return null;
    	
    	displayBuffer = new LCDBitmap(pointer, null);
    	bitmaps.add(displayBuffer);
    	return displayBuffer;
    }
    
    public static LCDBitmap copyFrameBufferBitmap() {
    	long ptr = bridge.copyFrameBufferBitmap();
    	Api.Pointer pointer = new Api.Pointer(ptr);
    	if (pointer.invalid())
    		return null;
    	
    	LCDBitmap bitmap = new LCDBitmap(pointer, null);
    	bitmaps.add(bitmap);
    	return bitmap;
    }

    public void markUpdatedRows(int start, int end) {
        bridge.markUpdatedRows(start, end);
    }

    public void setDrawOffset(int dx, int dy) {
        bridge.setDrawOffset(dx, dy);
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
    
    public enum LCDPolygonFillRule {
    	FillNonZero(0),
    	FillEvenOdd(1);

        final int value;

        LCDPolygonFillRule (int value) {
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

        private final Api.Pointer ptr;
        private final String path;
        
        private final List<LCDFontPage> pages = new ArrayList<LCDFontPage>();

        public LCDFont(Api.Pointer ptr, String path) {
            this.ptr = ptr;
            this.path = path;
        }
        
        public Api.Pointer getPointer() {
        	return ptr;
        }

        public String getPath() {
            return path;
        }
        
        public LCDFontPage getFontPage(long c) {
        	long pagePtr = bridge.getFontPage(ptr.getValue(), c);
        	LCDFontPage found = findPage(pagePtr);
        	if (found != null)
        		return found;
        	
        	Api.Pointer pagePointer = new Api.Pointer(pagePtr);
        	if (pagePointer.invalid())
        		return null;
        	
        	LCDFontPage page = new LCDFontPage(pagePointer);
        	pages.add(page);
        	return page;
        }

        public short getFontHeight() {
            return bridge.getFontHeight(ptr.getValue());
        }

        public int getTextWidth(String text, PDStringEncoding encoding, int tracking) {
        	return bridge.getTextWidth(ptr.getValue(), text, text.length(), encoding.getValue(), tracking);
        }
        
        private LCDFontPage findPage(long ptr) {
        	for (LCDFontPage page : pages) {
        		if (page.ptr.getValue() == ptr)
        			return page;
        	}
        	return null;
        }
    }
    
    public static class LCDFontPage {
    	
    	private final Api.Pointer ptr;
    	
    	private final List<LCDFontGlyph> glyphs = new ArrayList<LCDFontGlyph>();
    	
    	public LCDFontPage(Api.Pointer ptr) {
            this.ptr = ptr;
        }
         
        public Api.Pointer getPointer() {
        	return ptr;
        }
        
        public LCDFontGlyph getPageGlyph(long c) {
        	long glyphPtr = bridge.getPageGlyph(ptr.getValue(), c);
        	LCDFontGlyph found = findGlyph(glyphPtr);
        	if (found != null)
        		return found;
        	
        	Api.Pointer glyphPointer = new Api.Pointer(glyphPtr);
        	if (glyphPointer.invalid())
        		return null;
        	
        	LCDFontGlyph glyph = new LCDFontGlyph(glyphPointer);
        	glyphs.add(glyph);
        	return glyph;
        }
        
        private LCDFontGlyph findGlyph(long ptr) {
        	for (LCDFontGlyph glyph : glyphs) {
        		if (glyph.ptr.getValue() == ptr)
        			return glyph;
        	}
        	return null;
        }
    }
    
    public static class LCDFontGlyph {
    	
    	private final Api.Pointer ptr;
    	
    	public LCDFontGlyph(Api.Pointer ptr) {
            this.ptr = ptr;
        }
         
        public Api.Pointer getPointer() {
        	return ptr;
        }
        
        public int getGlyphKerning(long c1, long c2) {
        	return bridge.getGlyphKerning(ptr.getValue(), c1, c2);
        }
    }

    public static class LCDBitmap {

        private final Api.Pointer ptr;
        private String path;

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
            Graphics.freeBitmap(this);
        }

        public void clearBitmap(LCDSolidColor color) {
            Graphics.clearBitmap(this, color);
        }
    }
    
    public static class LCDBitmapTable {
    	
    	private final Api.Pointer ptr;
    	
    	public LCDBitmapTable(Api.Pointer ptr) {
            this.ptr = ptr;
        }

        public Api.Pointer getPointer() {
            return ptr;
        }

        public void free() {
        	Graphics.freeBitmapTable(this);
        }
        
        public LCDBitmap getTableBitmap(int idx) {
        	long bitmapPtr = bridge.getTableBitmap(ptr.getValue(), idx);
        	LCDBitmap found = findBitmap(bitmapPtr);
        	if (found != null)
        		return found;
        	
        	Api.Pointer bitmapPointer = new Api.Pointer(bitmapPtr);
        	if (bitmapPointer.invalid())
        		return null;
        	
        	LCDBitmap bitmap = new LCDBitmap(bitmapPointer, null);
        	bitmaps.add(bitmap);
        	return bitmap;
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
	
	public enum PDStringEncoding {
		ASCIIEncoding(0),
		UTF8Encoding(1),
		Bit16LEEncoding(2);
		
		final int value;

		PDStringEncoding(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
	}
}
