package com.am1goo.playdate4j.sdk;

import com.am1goo.playdate4j.sdk.Api.Pointer;
import com.am1goo.playdate4j.sdk.Api.UInt8;
import com.am1goo.playdate4j.sdk.Graphics.LCDBitmapFlip;
import com.am1goo.playdate4j.sdk.Graphics.LCDDrawMode;

public class Sprite {

	private static final SpriteBridge bridge = new SpriteBridge();
	
	public static LCDSprite newSprite() {
		long ptr = bridge.newSprite();
		return new LCDSprite(new Pointer(ptr));
	}
	
	public static LCDSprite copySprite(LCDSprite sprite) {
		long ptr = bridge.copy(sprite.ptr.getValue());
		return new LCDSprite(new Pointer(ptr));
	}
	
	public static LCDSprite freeSprite(LCDSprite sprite) {
		bridge.freeSprite(sprite.ptr.getValue());
		sprite.ptr = Pointer.invalid;
		return null;
	}
	
	public static class LCDSprite {
		
		private Pointer ptr;
		
		public LCDSprite(Pointer ptr) {
			this.ptr = ptr;
		}
		
		public void setTag(byte tag) {
			int nativeValue = UInt8.getNative(tag);
			bridge.setTag(ptr.getValue(), nativeValue);
		}
		
		public byte getTag() {
			int nativeValue = bridge.getTag(ptr.getValue());
			return UInt8.getJava(nativeValue);
		}
		
		public void setDrawMode(LCDDrawMode mode) {
			bridge.setDrawMode(ptr.getValue(), mode.getValue());
		}
		
		public void setImageFlip(LCDBitmapFlip flip) {
			bridge.setImageFlip(ptr.getValue(), flip.getValue());
		}
		
		public LCDBitmapFlip getImageFlip() {
			int flipValue = bridge.getImageFlip(ptr.getValue());
			return LCDBitmapFlip.valueOf(flipValue);
		}
		
		public void setUpdatesEnabled(boolean value) {
			bridge.setUpdatesEnabled(ptr.getValue(), value);
		}
		
		public boolean isUpdatesEnabled() {
			return bridge.updatesEnabled(ptr.getValue());
		}
		
		public void setVisible(boolean value) {
			bridge.setVisible(ptr.getValue(), value);
		}
		
		public boolean isVisible() {
			return bridge.isVisible(ptr.getValue());
		}
	}
	
	public class LCDSpriteCoords {
		private int x;
		private int y;
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public void set(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
