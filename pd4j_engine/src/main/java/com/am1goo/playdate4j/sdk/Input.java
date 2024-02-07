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
    
    public static void setPeripheralsEnabled(Peripherals mask) {
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
        return accelerometer.getX();
    }
    
    public static float getAccelerometerY() {
    	syncAccelerometer();
        return accelerometer.getY();
    }
    
    public static float getAccelerometerZ() {
    	syncAccelerometer();
        return accelerometer.getZ();
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
    
    public static boolean isButtonDown(Buttons button) {
    	syncButtonState();
    	return (buttonState.isPushed(button.getValue()));
    }
    
    public static boolean isButton(Buttons button) {
    	syncButtonState();
    	return (buttonState.isCurrent(button.getValue()));
    }
    
    public static boolean isButtonUp(Buttons button) {
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
    
    public enum Peripherals {
    	None(0),
    	Accelerometer(1 << 0);
    	
    	final int value;
    	
    	Peripherals(int value) {
    		this.value = value;
    	}
    	
    	public int getValue() {
    		return value;
    	}
    }
    
    public enum Buttons {
    	Left(1<<0),
    	Right(1<<1),
    	Up(1<<2),
    	Down(1<<3),
    	B(1<<4),
    	A(1<<5);
    	
    	final int value;
    	
    	Buttons(int value) {
    		this.value = value;
    	}
    	
    	public int getValue() {
    		return value;
    	}
    }
}
