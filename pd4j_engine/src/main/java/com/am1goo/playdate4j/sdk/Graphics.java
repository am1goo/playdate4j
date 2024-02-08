package com.am1goo.playdate4j.sdk;

public class Graphics {

    private static final GraphicsBridge bridge = new GraphicsBridge();

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

    public static void drawText(String text, int x, int y) {
        bridge.drawText(text, x, y);
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
	
	public class LCDRect {
		int left;
		int right;
		int top;
		int bottom;
		
		public int getLeft() {
			return left;
		}
		
		public int getRight() {
			return right;
		}
		
		public int getTop() {
			return top;
		}
		
		public int getBottom() {
			return bottom;
		}
	}
}
