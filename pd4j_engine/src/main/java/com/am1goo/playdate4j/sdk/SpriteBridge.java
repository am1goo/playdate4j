package com.am1goo.playdate4j.sdk;

public class SpriteBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    /* sprites */
	public native long newSprite();
	public native long copy(long spritePtr);
	public native void freeSprite(long spritePtr);
	
	/* properties */
	public native void setBounds(long spritePtr, float x, float y, float width, float height);
	public native void getBounds(long spritePtr, PDRect bounds);

	public native void moveTo(long spritePtr, float x, float y);
	public native void moveBy(long spritePtr, float dx, float dy);
	public native void getPosition(long spritePtr, PDXY xy);
	public native void setCenter(long spritePtr, float x, float y);
	public native void getCenter(long spritePtr, PDXY xy);

	public native void setImage(long spritePtr, long bitmapPtr, int flip);
	public native long getImage(long spritePtr);
	
	public native void setSize(long spritePtr, float width, float height);
	public native void setZIndex(long spritePtr, short zIndex);
	public native short getZIndex(long spritePtr);
	
	public native void setTag(long spritePtr, int tag);
	public native int getTag(long spritePtr);
	
	public native void setDrawMode(long spritePtr, int mode);
	
	public native void setImageFlip(long spritePtr, int flip);
	public native int getImageFlip(long spritePtr);
	
	public native void setStencil(long spritePtr, long bitmapPtr);
	public native void setStencilImage(long spritePtr, long bitmapPtr, int tile);
	public native void clearStencil(long spritePtr);
	
	public native void setClipRect(long spritePtr, int left, int right, int top, int bottom);
	public native void clearClipRect(long spritePtr);
	public native void setClipRectsInRange(int left, int right, int top, int bottom, int zStart, int zEnd);
	public native void clearClipRectsInRange(int zStart, int zEnd);
	
	public native void setUpdatesEnabled(long spritePtr, boolean flag);
	public native boolean updatesEnabled(long spritePtr);
	
	public native void setVisible(long spritePtr, boolean flag);
	public native boolean isVisible(long spritePtr);
	public native void setOpaque(long spritePtr, boolean flag);
	
	public native void setAlwaysRedraw(boolean value);
	public native void markDirty(long spritePtr);
	public native void addDirtyRect(int left, int right, int top, int bottom);
	public native void setIgnoresDrawOffset(long spritePtr, boolean flag);

	/* display list */
	public native void addSprite(long spritePtr);
	public native void removeSprite(long spritePtr);
	public native void removeAllSprites();
	public native int getSpriteCount();
	public native void drawSprites();
	public native void updateAndDrawSprites();
	
	/* collisions */
	public native void resetCollisionWorld();
	public native void setCollisionsEnabled(long spritePtr, boolean flag);
	public native boolean collisionsEnabled(long spritePtr);
	public native void setCollideRect(long spritePtr, float x, float y, float width, float height);
	public native void getCollideRect(long spritePtr, PDRect collideRect);
	public native void clearCollideRect(long spritePtr);

	public static class PDXY {
		private float x;
		private float y;

		public float x() {
			return x;
		}

		public float y() {
			return y;
		}

		public void set(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class PDRect {
		private float x;
		private float y;
		private float width;
		private float height;
		
		public float x() {
			return x;
		}
		
		public float y() {
			return y;
		}
		
		public float width() {
			return width;
		}
		
		public float height() {
			return height;
		}
		
		public void set(float x, float y, float width, float height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
	}
}
