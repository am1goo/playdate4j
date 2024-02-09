package com.am1goo.playdate4j.sdk;

import com.am1goo.playdate4j.sdk.InputBridge.AccelerometerBridge;
import com.am1goo.playdate4j.sdk.InputBridge.ButtonStateBridge;

public class Input {

    private static final InputBridge bridge = new InputBridge();

    private static final AccelerometerBridge accelerometer = new AccelerometerBridge();
    private static int accelerometerSyncFrame = Integer.MIN_VALUE;
    
    private static final ButtonStateBridge buttonState = new ButtonStateBridge();
    private static final long buttonStateSyncDelay = 50;
    private static long buttonStateSyncTime = Long.MIN_VALUE;
    private static int buttonStateSyncFrame = Integer.MIN_VALUE;
    
    public static void setPeripheralsEnabled(PDPeripherals mask) {
    	setPeripheralsEnabled(mask.getValue());
    }
    
    public static void setPeripheralsEnabled(int mask) {
    	bridge.setPeripheralsEnabled(mask);
    }
    
    private static void syncAccelerometer() {
    	int frameCount = Game.getFrameCount();
    	if (accelerometerSyncFrame == frameCount)
    		return;
    	
   		bridge.getAccelerometer(accelerometer);
   		accelerometerSyncFrame = frameCount;
    }
    
    public static float getAccelerometerX() {
    	syncAccelerometer();
        return accelerometer.x();
    }
    
    public static float getAccelerometerY() {
    	syncAccelerometer();
        return accelerometer.y();
    }
    
    public static float getAccelerometerZ() {
    	syncAccelerometer();
        return accelerometer.z();
    }
    
    private static void syncButtonState() {
    	int frameCount = Game.getFrameCount();
    	long currentTimeMillis = System.currentTimeMillis();
    	if (buttonStateSyncFrame == frameCount && buttonStateSyncTime - currentTimeMillis < buttonStateSyncDelay)
    		return;
    	
    	bridge.getButtonState(buttonState);
    	buttonStateSyncFrame = frameCount;
    	buttonStateSyncTime = currentTimeMillis;
    }
    
    public static boolean isButtonDown(PDButtons button) {
    	syncButtonState();
    	return (buttonState.isPushed(button.getValue()));
    }
    
    public static boolean isButton(PDButtons button) {
    	syncButtonState();
    	return (buttonState.isCurrent(button.getValue()));
    }
    
    public static boolean isButtonUp(PDButtons button) {
    	syncButtonState();
    	return (buttonState.isReleased(button.getValue()));
    }
    
    public static float getCrankAngle() {
    	return bridge.getCrankAngle();
    }
    
    public static float getCrankChange() {
    	return bridge.getCrankChange();
    }
    
    public static boolean isCrankDocked() {
    	return bridge.isCrankDocked();
    }
    
    public enum PDPeripherals {
    	None(0),
    	Accelerometer(1 << 0);
    	
    	final int value;
    	
    	PDPeripherals(int value) {
    		this.value = value;
    	}
    	
    	public int getValue() {
    		return value;
    	}
    }
    
    public enum PDButtons {
    	Left(1<<0),
    	Right(1<<1),
    	Up(1<<2),
    	Down(1<<3),
    	B(1<<4),
    	A(1<<5);
    	
    	final int value;
    	
    	PDButtons(int value) {
    		this.value = value;
    	}
    	
    	public int getValue() {
    		return value;
    	}
    }
}
