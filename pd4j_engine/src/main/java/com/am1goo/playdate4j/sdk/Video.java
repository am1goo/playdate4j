package com.am1goo.playdate4j.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.am1goo.playdate4j.sdk.Graphics.LCDBitmap;
import com.am1goo.playdate4j.sdk.VideoBridge.PDVideoInfo;

public class Video {

    private static final VideoBridge bridge = new VideoBridge();
    
    private static List<LCDVideoPlayer> players = new ArrayList<LCDVideoPlayer>();
    
    public static LCDVideoPlayer loadVideo(String path) {
    	LCDVideoPlayer found = findPlayer(path);
    	if (found != null)
    		return found;
    	
    	long ptr = bridge.loadVideo(path);
    	found = findPlayer(ptr);
    	if (found != null)
    		return found;
    	
    	Api.Pointer pointer = new Api.Pointer(ptr);
    	if (pointer.invalid())
    		return null;
    	
    	LCDVideoPlayer player = new LCDVideoPlayer(pointer, path);
    	players.add(player);
    	return player;
    }
    
    public static LCDVideoPlayer freePlayer(LCDVideoPlayer player) {
    	if (player == null)
    		return null;
    	
    	if (player.ptr.invalid())
    		return null;
    	
    	bridge.freePlayer(player.ptr.getValue());
    	player.ptr.invalidate();
    	players.remove(player);
    	return null;
    }
    
    private static LCDVideoPlayer findPlayer(String path) {
    	for (LCDVideoPlayer player : players) {
    		if (Objects.equals(player.getPath(), path)) 
    			return player;
    	}
    	return null;
    }
    
    private static LCDVideoPlayer findPlayer(long ptr) {
    	for (LCDVideoPlayer player : players) {
    		if (player.ptr.getValue() == ptr) 
    			return player;
    	}
    	return null;
    }
    
    public static class LCDVideoPlayer {
    	
    	private static final PDVideoInfo info = new PDVideoInfo();
    	
    	private final Api.Pointer ptr;
    	private final String path;
    	
    	public LCDVideoPlayer(Api.Pointer ptr, String path) {
    		this.ptr = ptr;
    		this.path = path;
    	}
    	
    	public Api.Pointer getPointer() {
    		return ptr;
    	}
    	
    	public String getPath() {
    		return path;
    	}
    	
    	public void free() {
    		Video.freePlayer(this);
    	}
        
        public boolean setContext(LCDBitmap bitmap) {
        	if (bitmap == null)
        		return false;
        	
        	return bridge.setContext(ptr.getValue(), bitmap.getPointer().getValue());
        }
        
        public LCDBitmap getContext() {
        	long bitmapPtr = bridge.getContext(ptr.getValue());
        	LCDBitmap found = Graphics.findBitmap(bitmapPtr);
        	if (found != null)
        		return found;
        	
        	Api.Pointer bitmapPointer = new Api.Pointer(bitmapPtr) ;
        	if (bitmapPointer.invalid())
        		return null;
        	
        	LCDBitmap bitmap = new LCDBitmap(bitmapPointer, null);
        	Graphics.addBitmap(bitmap);
        	return bitmap;
        }
        
        public void useScreenContext() {
        	bridge.useScreenContext(ptr.getValue());
        }
        
        public void renderFrame(int n) {
        	bridge.renderFrame(ptr.getValue(), n);
        }
        
        public String getError() {
        	return bridge.getError(ptr.getValue());
        }
        
        public PDVideoInfo getInfo() {
        	getInfo(info);
        	return info;
        }
        
        public void getInfo(PDVideoInfo info) {
        	bridge.getInfo(ptr.getValue(), info);
        }
    }
}
