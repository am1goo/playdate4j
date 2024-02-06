package com.am1goo.playdate4j.sdk;

public class Graphics {

    private static final GraphicsBridge bridge = new GraphicsBridge();

    public static void clear(SolidColor color) {
        bridge.clear(color.getValue());
    }

    public static void setDrawMode(DrawMode mode) {
        bridge.setDrawMode(mode.getValue());
    }

    public static void drawText(String text, int x, int y) {
        bridge.drawText(text, x, y);
    }

    public enum SolidColor {
        Black(0),
        White(1),
        Clear(2),
        XOR(3);

        final int value;

        SolidColor(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum DrawMode {
        Copy(0),
        WhiteTransparent(1),
        BlackTransparent(2),
        FillWhite(3),
        FillBlack(4),
        XOR(5),
        NXOR(6),
        Inverted(7);

        final int value;

        DrawMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
