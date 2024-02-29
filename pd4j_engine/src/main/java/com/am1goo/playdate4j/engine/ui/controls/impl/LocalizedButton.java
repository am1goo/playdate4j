package com.am1goo.playdate4j.engine.ui.controls.impl;

import com.am1goo.playdate4j.engine.localization.Localization;
import com.am1goo.playdate4j.sdk.Graphics;
import com.am1goo.playdate4j.sdk.Input;

public class LocalizedButton extends Button {

    public LocalizedButton(int x, int y, String text, Graphics.LCDBitmap normal, Runnable onClick) {
        super(x, y, text, normal, onClick);
    }

    public LocalizedButton(int x, int y, String text, Input.PDButtons button, Graphics.LCDBitmap normal, Runnable onClick) {
        super(x, y, text, button, normal, onClick);
    }

    public LocalizedButton(int x, int y, String text, Graphics.LCDBitmap normal, Graphics.LCDBitmap hover, Graphics.LCDBitmap pressed, Runnable onClick) {
        super(x, y, text, normal, hover, pressed, onClick);
    }

    public LocalizedButton(int x, int y, String text, Input.PDButtons button, Graphics.LCDBitmap normal, Graphics.LCDBitmap hover, Graphics.LCDBitmap pressed, Runnable onClick) {
        super(x, y, text, button, normal, hover, pressed, onClick);
    }

    public LocalizedButton(int x, int y, String text, Input.PDButtons button, Graphics.LCDBitmap normal, Graphics.LCDBitmap hover, Graphics.LCDBitmap pressed, Graphics.LCDBitmapFlip flip, Runnable onClick) {
        super(x, y, text, button, normal, hover, pressed, flip, onClick);
    }

    @Override
    public void setText(String text) {
        String localizedText = Localization.getString(text, null);
        super.setText(localizedText);
    }
}
