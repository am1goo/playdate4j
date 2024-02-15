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
	
	public native int checkCollisions(long spritePtr, float goalX, float goalY, SpriteCollisionInfo[] result, int length, SpriteActualInfo actual);
	public native int moveWithCollisions(long spritePtr, float goalX, float goalY, SpriteCollisionInfo[] result, int length, SpriteActualInfo actual);
	
	public native int querySpritesAtPoint(float x, float y, long[] result, int length);
	public native int querySpritesInRect(float x, float y, float width, float height, long[] result, int length);
	public native int querySpritesAlongLine(float x1, float y1, float x2, float y2, long[] result, int length);
	public native int overlappingSprites(long spritePtr, long[] result, int length);
	public native int allOverlappingSprites(long[] result, int length);
	public native int querySpriteInfoAlongLine(float x1, float y1, float x2, float y2, SpriteQueryInfo[] result, int length);
	
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
	
	public static class SpriteQueryInfo {
		private long spritePtr;
		private float ti1;
		private float ti2;
		private CollisionPoint entryPoint;
		private CollisionPoint exitPoint;
		
		public SpriteQueryInfo() {
			entryPoint = new CollisionPoint();
			exitPoint = new CollisionPoint();
		}
		
		public long spritePtr() {
			return spritePtr;
		}
		
		public float ti1() {
			return ti1;
		}
		
		public float ti2() {
			return ti2;
		}
		
		public CollisionPoint entryPoint() {
			return entryPoint;
		}
		
		public CollisionPoint exitPoint() {
			return exitPoint;
		}
		
		public void set(long spritePtr, float ti1, float ti2, float xEntryPoint, float yEntryPoint, float xExitPoint, float yExitPoint) {
			this.spritePtr = spritePtr;
			this.ti1 = ti1;
			this.ti2 = ti2;
			this.entryPoint.set(xEntryPoint, yEntryPoint);
			this.exitPoint.set(xExitPoint, yExitPoint);
		}
	}
	
	public static class SpriteCollisionInfo {
		
		private long spritePtr;
		private long otherPtr;
		private int responseType;
		private short overlaps;
		private float ti;
		private CollisionPoint move;
		private CollisionVector normal;
		private CollisionPoint touch;
		private PDRect spriteRect;
		private PDRect otherRect;
		
		public SpriteCollisionInfo() {
			move = new CollisionPoint();
			normal = new CollisionVector();
			touch = new CollisionPoint();
			spriteRect = new PDRect();
			otherRect = new PDRect();
		}
		
		public long spritePtr() {
			return spritePtr;
		}
		
		public long otherPtr() {
			return otherPtr;
		}
		
		public int responseType() {
			return responseType;
		}
		
		public short overlaps() {
			return overlaps;
		}
		
		public float ti() {
			return ti;
		}
		
		public CollisionPoint move() {
			return move;
		}
		
		public CollisionVector normal() {
			return normal;
		}
		
		public CollisionPoint touch() {
			return touch;
		}
		
		public PDRect spriteRect() {
			return spriteRect;
		}
		
		public PDRect otherRect() {
			return otherRect; 
		}
		
		public void set(long spritePtr, long otherPtr, int responseType, short overlaps, float ti, float xMove, float yMove, int xNormal, int yNormal, float xTouch, float yTouch) {
			this.spritePtr = spritePtr;
			this.otherPtr = otherPtr;
			this.responseType = responseType;
			this.overlaps = overlaps;
			this.ti = ti;
			this.move.set(xMove, yMove);
			this.normal.set(xNormal, yNormal);
			this.touch.set(xTouch, yTouch);
		}
	}
	
	public static class CollisionPoint {
		
		private float x;
		private float y;
		
		public CollisionPoint() {
			this(0, 0);
		}
		
		public CollisionPoint(float x, float y) {
			set(x, y);
		}
		
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
		
		public static CollisionPoint copyOf(CollisionPoint p) {
			return new CollisionPoint(p.x, p.y);
		}
	}
	
	public static class CollisionVector {
		
		private int x;
		private int y;
		
		public CollisionVector() {
			this(0, 0);
		}
		
		public CollisionVector(int x, int y) {
			set(x, y);
		}
		
		public int x() {
			return x;
		}
		
		public int y() {
			return y;
		}
		
		public void set(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public static CollisionVector copyOf(CollisionVector p) {
			return new CollisionVector(p.x, p.y);
		}
	}
	
	public static class SpriteActualInfo {
		
		private float actualX;
		private float actualY;
		
		public float actualX() {
			return actualX;
		}
		
		public float actualY() {
			return actualY;
		}
		
		public void set(float actualX, float actualY) {
			this.actualX = actualX;
			this.actualY = actualY;
		}
	}
}
