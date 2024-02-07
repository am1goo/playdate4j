package com.am1goo.playdate4j.sdk;

public class Display {

    private static final DisplayBridge bridge = new DisplayBridge();

    public static int getHeight() {
    	return bridge.getHeight();
    }
    
    public static int getWidth() {
    	return bridge.getWidth();
    }
    
    public static void setInverted(boolean value) {
    	bridge.setInverted(value);
    }
    
    public static void setMosaic(Mosaic x, Mosaic y) {
    	bridge.setMosaic(x.getValue(), y.getValue());
    }
    
    public static void setFlipped(int x, int y) {
    	bridge.setFlipped(x, y);
    }
    
    public static void setRefreshRate(int rate) {
        bridge.setRefreshRate(rate);
    }
    
    public static void setScale(Scale value) {
    	bridge.setScale(value.getValue());
    }
    
    public static void setOffset(int dx, int dy) {
    	bridge.setOffset(dx, dy);
    }
    
    public enum Mosaic {
    	Zero(0),
    	One(1),
    	Two(2),
    	Three(3);
    	
    	final int value;
    	
    	Mosaic(int value) {
			this.value = value;
		}
    	
    	int getValue() {
    		return value;
    	}
    }
    
    public enum Scale {
    	One(1),
    	Two(2),
    	Four(4),
    	Eight(8);
    	
    	final int value;
    	
    	Scale(int value) {
    		this.value = value;
    	}
    	
    	int getValue() {
    		return value;
    	}
    }
}
