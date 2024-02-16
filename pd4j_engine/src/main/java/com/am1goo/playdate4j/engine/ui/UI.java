package com.am1goo.playdate4j.engine.ui;

import java.util.Objects;

import com.am1goo.playdate4j.engine.ui.controls.Control;
import com.am1goo.playdate4j.sdk.Graphics;
import com.am1goo.playdate4j.sdk.Graphics.LCDBitmap;
import com.am1goo.playdate4j.sdk.Graphics.LCDBitmapFlip;
import com.am1goo.playdate4j.sdk.Input;
import com.am1goo.playdate4j.sdk.Input.PDButtons;

public class UI {

    private static Graphics.PDStringEncoding defaultEncoding = Graphics.PDStringEncoding.ASCIIEncoding;

    private static Control current = null;
    
    public static void setCurrent(Control current) {
    	UI.current = current;
    }
    
    public static boolean isCurrent(Control control) {
    	if (current == null)
    		return false;
    	
    	return Objects.equals(current, control);
    }
    
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
    
    public static boolean drawButton(int x, int y, String text, PDButtons button, LCDBitmap bitmap, LCDBitmapFlip flip, boolean isCurrent) {
    	return drawButton(x, y, text, button, bitmap, bitmap, bitmap, flip, isCurrent);
    }
    
    public static boolean drawButton(int x, int y, String text, PDButtons button, LCDBitmap normal, LCDBitmap hover, LCDBitmap pressed, LCDBitmapFlip flip, boolean isCurrent) {
    	boolean clicked = false;
    	LCDBitmap bitmap = isCurrent ? hover : normal;
    	
    	if (isCurrent) {
	    	if (Input.isButton(button)) {
	    		bitmap = pressed;
	    	}
	    	else if (Input.isButtonUp(button)) {
	    		clicked = true;
	    		bitmap = isCurrent ? hover : normal;
	    	}
    	}
    	
   		Graphics.drawBitmap(bitmap, x, y, flip);
  		Graphics.drawText(text, defaultEncoding, x, y);
    	return clicked;
    }
}
