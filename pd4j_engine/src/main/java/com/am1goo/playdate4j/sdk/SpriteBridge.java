package com.am1goo.playdate4j.sdk;

public class SpriteBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

	public native long newSprite();
	public native long copy(long ptr);
	public native void freeSprite(long ptr);
	
	public native void setTag(long ptr, int tag);
	public native int getTag(long ptr);
	
	public native void setDrawMode(long ptr, int mode);
	
	public native void setImageFlip(long ptr, int flip);
	public native int getImageFlip(long ptr);
	
	public native void setUpdatesEnabled(long ptr, boolean value);
	public native boolean updatesEnabled(long ptr);
	
	public native void setVisible(long ptr, boolean value);
	public native boolean isVisible(long ptr);
	
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
