package com.am1goo.playdate4j.sdk;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.am1goo.playdate4j.sdk.Api.Pointer;
import com.am1goo.playdate4j.sdk.Api.UInt8;
import com.am1goo.playdate4j.sdk.Graphics.LCDBitmap;
import com.am1goo.playdate4j.sdk.Graphics.LCDBitmapFlip;
import com.am1goo.playdate4j.sdk.Graphics.LCDBitmapDrawMode;
import com.am1goo.playdate4j.sdk.Graphics.LCDRect;
import com.am1goo.playdate4j.sdk.SpriteBridge.CollisionPoint;
import com.am1goo.playdate4j.sdk.SpriteBridge.CollisionVector;
import com.am1goo.playdate4j.sdk.SpriteBridge.PDRect;

public class Sprite {

	private static final SpriteBridge bridge = new SpriteBridge();
	
	private static final List<LCDSprite> sprites = new ArrayList<LCDSprite>();
	
	private static final Map<Integer, long[]> cacheLongArrays = new HashMap<Integer, long[]>();
	private static final Map<Integer, SpriteBridge.SpriteQueryInfo[]> cacheSpriteQueryArrays = new HashMap<Integer, SpriteBridge.SpriteQueryInfo[]>();
	private static final Map<Integer, SpriteBridge.SpriteCollisionInfo[]> cacheSpriteCollisionArrays = new HashMap<Integer, SpriteBridge.SpriteCollisionInfo[]>();
	private static final SpriteBridge.SpriteActualInfo cacheActualInfo = new SpriteBridge.SpriteActualInfo();
	
	/* sprites */
	public static LCDSprite newSprite() {
		long ptr = bridge.newSprite();
		LCDSprite found = findSprite(ptr);
		if (found != null)
			return found;
		
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		LCDSprite sprite = new LCDSprite(pointer);
		sprites.add(sprite);
		return sprite;
	}
	
	public static LCDSprite copySprite(LCDSprite sprite) {
		long copyPtr = bridge.copy(sprite.ptr.getValue());
		LCDSprite found = findSprite(copyPtr);
		if (found != null)
			return found;
		
		Api.Pointer copyPointer = new Api.Pointer(copyPtr);
		if (copyPointer.invalid())
			return null;
		
		LCDSprite copy = new LCDSprite(copyPointer);
		sprites.add(copy);
		return copy;
	}
	
	public static LCDSprite freeSprite(LCDSprite sprite) {
		if (sprite == null)
			return null;
		
		if (sprite.ptr.invalid())
			return null;
		
		bridge.freeSprite(sprite.ptr.getValue());
		sprite.ptr.invalidate();
		sprites.remove(sprite);
		return null;
	}
	
	private static LCDSprite findSprite(long ptr) {
		for (LCDSprite sprite : sprites) {
			if (sprite.ptr.getValue() == ptr)
				return sprite;
		}
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
	
	public static int checkCollisions(LCDSprite sprite, float goalX, float goalY, SpriteCollisionInfo[] result, SpriteActualInfo actual) {
		if (sprite == null)
			return 0;
		
		if (sprite.ptr.invalid())
			return 0;
		
		SpriteBridge.SpriteCollisionInfo[] temp = getOrCreateSpriteCollisionArray(result.length);
		int tempLength = temp.length;
		
		int len = bridge.checkCollisions(sprite.ptr.getValue(), goalX, goalY, temp, tempLength, cacheActualInfo);
		actual.set(cacheActualInfo.actualX(), cacheActualInfo.actualY());
		return doCollisions(temp, result, len);
	}
	
	public static int moveWithCollisions(LCDSprite sprite, float goalX, float goalY, SpriteCollisionInfo[] result, SpriteActualInfo actual) {
		if (sprite == null)
			return 0;
		
		if (sprite.ptr.invalid())
			return 0;
		
		SpriteBridge.SpriteCollisionInfo[] temp = getOrCreateSpriteCollisionArray(result.length);
		int tempLength = temp.length;
		
		int len = bridge.moveWithCollisions(getSpriteCount(), goalX, goalY, temp, tempLength, cacheActualInfo);
		actual.set(cacheActualInfo.actualX(), cacheActualInfo.actualY());
		return doCollisions(temp, result, len);
	}
	
	private static int doCollisions(SpriteBridge.SpriteCollisionInfo[] temp, SpriteCollisionInfo[] result, int len) {
		for (int i = 0; i < len; ++i) {
			result[i] = SpriteCollisionInfo.valueOf(temp[i]);
		}
		return len;
	}
	
	public static int querySpritesAtPoint(float x, float y, LCDSprite[] result) {
		long[] temp = getOrCreateLongArray(result.length);
		int tempLength = temp.length;
		
		int len = bridge.querySpritesAtPoint(x, y, temp, tempLength);
		for (int i = 0; i < tempLength; ++i) {
			long ptr = temp[i];
			LCDSprite sprite = findSprite(ptr);
			result[i] = sprite;
		}
		return len;
	}
	
	public static int querySpritesInRect(float x, float y, float width, float height, LCDSprite[] result) {
		long[] temp = getOrCreateLongArray(result.length);
		int tempLength = temp.length;
		
		int len = bridge.querySpritesInRect(x, y, width, height, temp, tempLength);
		for (int i = 0; i < tempLength; ++i) {
			long ptr = temp[i];
			LCDSprite sprite = findSprite(ptr);
			result[i] = sprite;
		}
		return len;
	}
	
	public static int querySpritesAlongLine(float x1, float y1, float x2, float y2, LCDSprite[] result) {
		long[] temp = getOrCreateLongArray(result.length);
		int tempLength = temp.length;
		
		int len = bridge.querySpritesAlongLine(x1, y1, x2, y2, temp, tempLength);
		for (int i = 0; i < tempLength; ++i) {
			long ptr = temp[i];
			result[i] = findSprite(ptr);
		}
		return len;
	}
	
	public static int overlappingSprites(LCDSprite sprite, LCDSprite[] result) {
		if (sprite == null) {
			for (int i = 0; i < result.length; ++i) {
				result[i] = null;
			}
			return 0;
		}
		
		if (sprite.ptr.invalid()) {
			for (int i = 0; i < result.length; ++i) {
				result[i] = null;
			}
			return 0;
		}
		
		long[] temp = getOrCreateLongArray(result.length);
		int tempLength = temp.length;
		
		int len = bridge.overlappingSprites(sprite.ptr.getValue(), temp, tempLength);
		for (int i = 0; i < tempLength; ++i) {
			long ptr = temp[i];
			result[i] = findSprite(ptr);
		}
		return len;
	}
	
	public static int allOverlappingSprites(LCDSprite[] result) {
		long[] temp = getOrCreateLongArray(result.length);
		int tempLength = temp.length;
		
		int len = bridge.allOverlappingSprites(temp, tempLength);
		for (int i = 0; i < tempLength; ++i) {
			long ptr = temp[i];
			result[i] = findSprite(ptr);
		}
		return len;
	}
	
	public static int querySpriteInfoAlongLine(float x1, float y1, float x2, float y2, SpriteQueryInfo[] result) {
		SpriteBridge.SpriteQueryInfo[] temp = getOrCreateSpriteQueryArray(result.length);
		int tempLength = temp.length;

		for (int i = 0; i < tempLength; ++i) {
			SpriteBridge.SpriteQueryInfo info = temp[i];
			if (info == null) {
				info = new SpriteBridge.SpriteQueryInfo();
				temp[i] = info;
			}
		}
		int len = bridge.querySpriteInfoAlongLine(x1, y1, x2, y2, temp, tempLength);
		for (int i = 0; i < tempLength; ++i) {
			SpriteBridge.SpriteQueryInfo info = temp[i];
			result[i] = SpriteQueryInfo.valueOf(info);
		}
		return len;
	}
	
	private static long[] getOrCreateLongArray(int length) {
		synchronized (cacheLongArrays) {
			if (cacheLongArrays.containsKey(length)) {
				return cacheLongArrays.get(length);
			}
			else {
				long[] array = new long[length];
				cacheLongArrays.put(length, array);
				return array;
			}
		}
	}
	
	private static SpriteBridge.SpriteQueryInfo[] getOrCreateSpriteQueryArray(int length) {
		synchronized (cacheSpriteQueryArrays) {
			if (cacheSpriteQueryArrays.containsKey(length)) {
				return cacheSpriteQueryArrays.get(length);
			}
			else {
				SpriteBridge.SpriteQueryInfo[] array = new SpriteBridge.SpriteQueryInfo[length];
				cacheSpriteQueryArrays.put(length, array);
				return array;
			}
		}
	}
	
	private static SpriteBridge.SpriteCollisionInfo[] getOrCreateSpriteCollisionArray(int length) {
		synchronized (cacheSpriteCollisionArrays) {
			if (cacheSpriteCollisionArrays.containsKey(length)) {
				return cacheSpriteCollisionArrays.get(length);
			}
			else {
				SpriteBridge.SpriteCollisionInfo[] array = new SpriteBridge.SpriteCollisionInfo[length];
				cacheSpriteCollisionArrays.put(length, array);
				return array;
			}
		}
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
		
		public void setDrawMode(LCDBitmapDrawMode mode) {
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
	
	public enum SpriteCollisionResponseType {
		Slide(0),
		Freeze(1),
		Overlap(2),
		Bounce(3);
		
		final int value;
		
		SpriteCollisionResponseType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
		public static SpriteCollisionResponseType valueOf(int value) {
			for (SpriteCollisionResponseType type : values()) {
				if (type.getValue() == value)
					return type;
			}
			return null;
		}
	}
	
	public static class SpriteCollisionInfo {
		
		private LCDSprite sprite;
		private LCDSprite other;
		private SpriteCollisionResponseType responseType;
		private short overlaps;
		private float ti;
		private CollisionPoint move;
		private CollisionVector normal;
		private CollisionPoint touch;
		
		public SpriteCollisionInfo(LCDSprite sprite, LCDSprite other, SpriteCollisionResponseType responseType, short overlaps, float ti, CollisionPoint move, CollisionVector normal, CollisionPoint touch) {
			this.sprite = sprite;
			this.other = other;
			this.responseType = responseType;
			this.overlaps = overlaps;
			this.ti = ti;
			this.move = move;
			this.normal = normal;
			this.touch = touch;
		}
		
		public LCDSprite sprite() {
			return sprite;
		}
		
		public LCDSprite other() {
			return other;
		}
		
		public SpriteCollisionResponseType responseType() {
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
		
		public static SpriteCollisionInfo valueOf(SpriteBridge.SpriteCollisionInfo info) {
			if (info == null)
				return null;
			
			LCDSprite sprite = findSprite(info.spritePtr());
			LCDSprite other = findSprite(info.otherPtr());
			SpriteCollisionResponseType responseType = SpriteCollisionResponseType.valueOf(info.responseType());
			short overlaps = info.overlaps();
			float ti = info.ti();
			CollisionPoint move = CollisionPoint.copyOf(info.move());
			CollisionVector normal = CollisionVector.copyOf(info.normal());
			CollisionPoint touch = CollisionPoint.copyOf(info.touch());
			return new SpriteCollisionInfo(sprite, other, responseType, overlaps, ti, move, normal, touch);
		}
	}
	
	public static class SpriteQueryInfo {
		
		private LCDSprite sprite;
		private float ti1;
		private float ti2;
		private CollisionPoint entryPoint;
		private CollisionPoint exitPoint;
		
		public SpriteQueryInfo(LCDSprite sprite, float t1, float ti2, CollisionPoint entryPoint, CollisionPoint exitPoint) {
			this.sprite = sprite;
			this.ti1 = t1;
			this.ti2 = ti2;
			this.entryPoint = entryPoint;
			this.exitPoint = exitPoint;
		}
		
		public LCDSprite sprite() {
			return sprite;
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
		
		public static SpriteQueryInfo valueOf(SpriteBridge.SpriteQueryInfo info) {
			if (info == null)
				return null;
			
			LCDSprite sprite = findSprite(info.spritePtr());
			float ti1 = info.ti1();
			float ti2 = info.ti2();
			CollisionPoint entryPoint = CollisionPoint.copyOf(info.entryPoint());
			CollisionPoint exitPoint = CollisionPoint.copyOf(info.exitPoint());
			return new SpriteQueryInfo(sprite, ti1, ti2, entryPoint, exitPoint);
		}
	}
	
	public static class SpriteActualInfo {
		
		private float actualX;
		private float actualY;
		
		public SpriteActualInfo() {
			this(0, 0);
		}
		
		public SpriteActualInfo(float actualX, float actualY) {
			set(actualX, actualY);
		}
		
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
		
		public static SpriteActualInfo valueOf(SpriteBridge.SpriteActualInfo info) {
			if (info == null)
				return null;
			
			return new SpriteActualInfo(info.actualX(), info.actualY());
		}
	}
}
