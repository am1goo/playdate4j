package com.am1goo.playdate4j.engine.ui;

import com.am1goo.playdate4j.sdk.Graphics;

public class UI {

    private static Graphics.PDStringEncoding defaultEncoding = Graphics.PDStringEncoding.ASCIIEncoding;

    public static void setDefaultEncoding(Graphics.PDStringEncoding encoding) {
        if (encoding == null)
            return;

        UI.defaultEncoding = encoding;
    }

    public static void drawText(int x, int y, String text) {
        drawText(x, y, text, defaultEncoding);
    }

    public static void drawText(int x, int y, String text, Graphics.PDStringEncoding encoding) {
        Graphics.drawText(text, encoding, x, y);
    }
}
