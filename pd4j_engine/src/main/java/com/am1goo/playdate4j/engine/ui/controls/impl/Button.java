package com.am1goo.playdate4j.engine.ui.controls.impl;

import com.am1goo.playdate4j.engine.ui.UI;
import com.am1goo.playdate4j.engine.ui.controls.Control;
import com.am1goo.playdate4j.sdk.Graphics.LCDBitmap;
import com.am1goo.playdate4j.sdk.Graphics.LCDBitmapFlip;
import com.am1goo.playdate4j.sdk.Input.PDButtons;

public class Button implements Control {

	private int x;
	private int y;
	
	private String text;
	private PDButtons button;
	
	private final LCDBitmap normal;
	private final LCDBitmap hover;
	private final LCDBitmap pressed;
	
	private LCDBitmapFlip flip;
	
	private final Runnable onClick;

	public Button(int x, int y, String text, PDButtons button, LCDBitmap normal, LCDBitmapFlip flip, Runnable onClick) {
		this(x, y, text, button, normal, normal, normal, flip, onClick);
	}
	
	public Button(int x, int y, String text, PDButtons button, LCDBitmap normal, LCDBitmap hover, LCDBitmap pressed, LCDBitmapFlip flip, Runnable onClick) {
		setPosition(x, y);
		setText(text);
		setButton(button);
		this.normal = normal;
		this.hover = hover;
		this.pressed = pressed;
		setFlip(flip);
		this.onClick = onClick;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setText(String text) {
		this.text = text != null ? text : "";
	}
	
	public void setButton(PDButtons button) {
		this.button = button != null ? button : PDButtons.A;
	}
	
	public void setFlip(LCDBitmapFlip flip) {
		this.flip = flip != null ? flip : LCDBitmapFlip.Unflipped;
	}
	
	public void draw() {
		if (UI.drawButton(x, y, text, button, normal, hover, pressed, flip, UI.isCurrent(this))) {
			if (onClick != null)
				onClick.run();
		}
	}
}
