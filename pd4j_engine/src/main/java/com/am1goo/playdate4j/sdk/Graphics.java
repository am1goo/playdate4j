package com.am1goo.playdate4j.sdk;

public class Graphics {

    private static final GraphicsBridge bridge = new GraphicsBridge();

    public static void setDrawMode(DrawMode mode) {
        bridge.setDrawMode(mode.getValue());
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
