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
		sprite.ptr.invalidate();
		return null;
	}

	public static void addSprite(LCDSprite sprite) {
		bridge.addSprite(sprite.ptr.getValue());
	}

	public static void removeSprite(LCDSprite sprite) {
		bridge.removeSprite(sprite.ptr.getValue());
	}

	public static void removeAllSprites() {
		bridge.removeAllSprites();
	}

	public static int getSpriteCount() {
		return bridge.getSpriteCount();
	}

	public static void drawSprites() {
		bridge.drawSprites();
	}

	public static void updateAndDrawSprites() {
		bridge.updateAndDrawSprites();
	}
	
	public static class LCDSprite {

		private static final SpriteBridge.PDXY position = new SpriteBridge.PDXY();
		private static final SpriteBridge.PDXY center = new SpriteBridge.PDXY();
		
		private final Pointer ptr;

		public LCDSprite(Pointer ptr) {
			this.ptr = ptr;
		}

		public Api.Pointer getPointer() {
			return ptr;
		}

		public void moveTo(float x, float y) {
			bridge.moveTo(ptr.getValue(), x, y);
		}

		public void moveBy(float x, float y) {
			bridge.moveBy(ptr.getValue(), x, y);
		}

		public void setPosition(float x, float y){
			moveTo(x, y);
		}

		public void deltaPosition(float dx, float dy) {
			moveBy(dx, dy);
		}

		public float getPositionX() {
			bridge.getPosition(ptr.getValue(), position);
			return position.getX();
		}

		public float getPositionY() {
			bridge.getPosition(ptr.getValue(), position);
			return position.getY();
		}

		public void setTag(byte tag) {
			int nativeValue = UInt8.getNative(tag);
			bridge.setTag(ptr.getValue(), nativeValue);
		}
		
		public byte getTag() {
			int nativeValue = bridge.getTag(ptr.getValue());
			return UInt8.getJava(nativeValue);
		}

		public void setImage(Graphics.LCDBitmap bitmap, LCDBitmapFlip flip) {
			if (bitmap == null)
				return;

			bridge.setImage(ptr.getValue(), bitmap.getPointer().getValue(), flip.getValue());
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
}
