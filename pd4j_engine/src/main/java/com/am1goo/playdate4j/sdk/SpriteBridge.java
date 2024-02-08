package com.am1goo.playdate4j.sdk;

public class SpriteBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

	public native long newSprite();
	public native long copy(long ptr);
	public native void freeSprite(long ptr);

	public native void moveTo(long ptr, float x, float y);
	public native void moveBy(long ptr, float dx, float dy);
	public native void getPosition(long ptr, PDXY xy);

	public native void setImage(long spritePtr, long bitmapPtr, int flip);

	public native void setTag(long ptr, int tag);
	public native int getTag(long ptr);
	
	public native void setDrawMode(long ptr, int mode);
	
	public native void setImageFlip(long ptr, int flip);
	public native int getImageFlip(long ptr);
	
	public native void setUpdatesEnabled(long ptr, boolean value);
	public native boolean updatesEnabled(long ptr);
	
	public native void setVisible(long ptr, boolean value);
	public native boolean isVisible(long ptr);

	public native void addSprite(long ptr);
	public native void removeSprite(long ptr);
	public native void removeAllSprites();
	public native int getSpriteCount();
	public native void drawSprites();
	public native void updateAndDrawSprites();

	public static class PDXY {
		private float x;
		private float y;

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}

		public void set(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}

	public class PDRect {
		private float x;
		private float y;
		private float width;
		private float height;
		
		public float getX() {
			return x;
		}
		
		public float getY() {
			return y;
		}
		
		public float getWidth() {
			return width;
		}
		
		public float getHeight() {
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
