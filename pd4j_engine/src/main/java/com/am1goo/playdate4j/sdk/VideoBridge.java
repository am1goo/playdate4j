package com.am1goo.playdate4j.sdk;

public class VideoBridge {
	
    static {
        Sdk.loadRequiredLibraries();
    }
    
    public native long loadVideo(String path);
    public native void freePlayer(long playerPtr);
    public native boolean setContext(long playerPtr, long bitmapPtr);
    public native long getContext(long playerPtr);
    public native void useScreenContext(long playerPtr);
    public native void renderFrame(long playerPtr, int n);
    public native String getError(long playerPtr);
    public native void getInfo(long playerPtr, PDVideoInfo info);
    
    public static class PDVideoInfo {
    	
    	private int outWidth;
    	private int outHeight;
    	private float outFrameRate;
    	private int outFrameCount;
    	private int outCurrentFrame;
    	
    	public int width() {
    		return outWidth;
    	}
    	
    	public int height() {
    		return outHeight;
    	}
    	
    	public float frameRate() {
    		return outFrameRate;
    	}
    	
    	public int frameCount() {
    		return outFrameCount;
    	}
    	
    	public int currentFrame() {
    		return outCurrentFrame;
    	}
    	
    	public void set(int outWidth, int outHeight, float outFrameRate, int outFrameCount, int outCurrentFrame) {
    		this.outWidth = outWidth;
    		this.outHeight = outHeight;
    		this.outFrameRate = outFrameRate;
    		this.outFrameCount = outFrameCount;
    		this.outCurrentFrame = outCurrentFrame;
    	}
    }
}
