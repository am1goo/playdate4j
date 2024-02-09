package com.am1goo.playdate4j.sdk;

public class InputBridge {

	public native void setPeripheralsEnabled(int mask);
    public native void getAccelerometer(AccelerometerBridge acc);
    public native void getButtonState(ButtonStateBridge state);
    public native float getCrankAngle();
    public native float getCrankChange();
    public native boolean isCrankDocked();
    
    public static class AccelerometerBridge {

    	private float x;
    	private float y;
    	private float z;
    	
    	public AccelerometerBridge() {
    		
    	}
    	
    	public float x() {
    		return x;
    	}
    	
    	public float y() {
    		return y;
    	}
    	
    	public float z() {
    		return z;
    	}
    	
    	public void set(float x, float y, float z) {
    		this.x = x;
    		this.y = y;
    		this.z = z;
    	}
    }
    
    public static class ButtonStateBridge {
    	private int current;
    	private int pushed;
    	private int released;
    	
    	public boolean isCurrent(int mask) {
    		return hasMask(current, mask);
    	}
    	
    	public boolean isPushed(int mask) {
    		return hasMask(pushed, mask);
    	}
    	
    	public boolean isReleased(int mask) {
    		return hasMask(released, mask);
    	}
    	
    	public void set(int current, int pushed, int released) {
    		this.current = current;
    		this.pushed = pushed;
    		this.released = released;
    	}
    	
    	private static boolean hasMask(int state, int mask) {
    		return (state & mask) != 0;
    	}
    }
}