package com.am1goo.playdate4j.sdk;

import com.am1goo.playdate4j.sdk.Api.Pointer;
import com.am1goo.playdate4j.sdk.Api.UInt8;
import com.am1goo.playdate4j.sdk.Graphics.LCDBitmap;
import com.am1goo.playdate4j.sdk.Graphics.LCDBitmapFlip;
import com.am1goo.playdate4j.sdk.Graphics.LCDDrawMode;
import com.am1goo.playdate4j.sdk.Graphics.LCDRect;
import com.am1goo.playdate4j.sdk.SpriteBridge.PDRect;

public class Sprite {

	private static final SpriteBridge bridge = new SpriteBridge();
	
	/* sprites */
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
	
	/* properties */
	public void setAlwaysRedraw(boolean value) {
		bridge.setAlwaysRedraw(value);
	}
	
	public void addDirtyRect(LCDRect dirtyRect) {
		bridge.addDirtyRect(dirtyRect.left(), dirtyRect.right(), dirtyRect.top(), dirtyRect.bottom());
	}
	
	/* display list */
	public static void addSprite(LCDSprite sprite) {
		bridge.addSprite(sprite.ptr.getValue());
	}

	public static void removeSprite(LCDSprite sprite) {
		bridge.removeSprite(sprite.ptr.getValue());
	}

	public static void removeAllSprites() {
		bridge.removeAllSprites();
	}

	public void setClipRectsInRange(LCDRect clipRect, int zStart, int zEnd) {
		bridge.setClipRectsInRange(clipRect.left(), clipRect.right(), clipRect.top(), clipRect.bottom(), zStart, zEnd);
	}
	
	public void clearClipRectsInRange(int zStart, int zEnd) {
		bridge.clearClipRectsInRange(zStart, zEnd);
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

	/* collisions */
	public static void resetCollisionWorld() {
		bridge.resetCollisionWorld();
	}
	
	public static class LCDSprite {

		private final SpriteBridge.PDRect bounds = new SpriteBridge.PDRect();
		private final SpriteBridge.PDRect collideRect = new SpriteBridge.PDRect();
		private final SpriteBridge.PDXY position = new SpriteBridge.PDXY();
		private final SpriteBridge.PDXY center = new SpriteBridge.PDXY();
		
		private final Pointer ptr;

		public LCDSprite(Pointer ptr) {
			this.ptr = ptr;
		}

		public Api.Pointer getPointer() {
			return ptr;
		}

		/* sprites */
		public void free() {
			if (ptr.invalid())
				return;

			Sprite.freeSprite(this);
		}
		
		/* properties */
		public void setBounds(PDRect bounds) {
			bridge.setBounds(ptr.getValue(), bounds.x(), bounds.y(), bounds.width(), bounds.height());
		}
		
		public PDRect getBounds() {
			bridge.getBounds(ptr.getValue(), bounds);
			return bounds;
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

		public SpriteBridge.PDXY getPosition() {
			bridge.getPosition(ptr.getValue(), position);
			return position;
		}

		public void setCenter(float x, float y) {
			bridge.setCenter(ptr.getValue(), x, y);
		}
		
		public SpriteBridge.PDXY getCenter() {
			bridge.getCenter(ptr.getValue(), center);
			return center;
		}

		public void setImage(Graphics.LCDBitmap bitmap, LCDBitmapFlip flip) {
			if (bitmap == null)
				return;

			bridge.setImage(ptr.getValue(), bitmap.getPointer().getValue(), flip.getValue());
		}
		
		public Graphics.LCDBitmap getImage() {
			long bitmapPtr = bridge.getImage(ptr.getValue());
			return Graphics.findBitmap(bitmapPtr);
		}
		
		public void setSize(float width, float height) {
			bridge.setSize(ptr.getValue(), width, height);
		}
		
		public void setZIndex(short zIndex) {
			bridge.setZIndex(ptr.getValue(), zIndex);
		}
		
		public short getZIndex() {
			return bridge.getZIndex(ptr.getValue());
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
		
		public void setStencil(LCDBitmap bitmap) {
			if (bitmap == null)
				return;
			
			bridge.setStencil(ptr.getValue(), bitmap.getPointer().getValue());
		}
		
		public void setStencilImage(LCDBitmap bitmap, int tile) {
			if (bitmap == null)
				return;
			
			bridge.setStencilImage(ptr.getValue(), bitmap.getPointer().getValue(), tile);
		}
		
		public void clearStencil() {
			bridge.clearStencil(ptr.getValue());
		}
		
		public void setClipRect(LCDRect clipRect) {
			bridge.setClipRect(ptr.getValue(), clipRect.left(), clipRect.right(), clipRect.top(), clipRect.bottom());
		}
		
		public void clearClipRect() {
			bridge.clearClipRect(ptr.getValue());
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
		
		public void setOpaque(boolean value) {
			bridge.setOpaque(ptr.getValue(), value);
		}
		
		public void markDirty() {
			bridge.markDirty(ptr.getValue());
		}
		
		public void setIgnoresDrawOffset(boolean value) {
			bridge.setIgnoresDrawOffset(ptr.getValue(), value);
		}
		
		/* collisions */
		public void setCollisionsEnabled(boolean value) {
			bridge.setCollisionsEnabled(ptr.getValue(), value);
		}
		
		public boolean isCollisionsEnabled() {
			return bridge.collisionsEnabled(ptr.getValue());
		}
		
		public void setCollideRect(PDRect rect) {
			bridge.setCollideRect(ptr.getValue(), rect.x(), rect.y(), rect.width(), rect.height());
		}
		
		public PDRect getCollideRect() {
			bridge.getCollideRect(ptr.getValue(), collideRect);
			return collideRect;
		}
		
		public void clearCollideRect() {
			bridge.clearCollideRect(ptr.getValue());
		}
	}
}
