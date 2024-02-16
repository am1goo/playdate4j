package com.am1goo.playdate4j.sdk;
    
public enum Event {
	Lock(0),
	Unlock(1),
	Pause(2),
	Resume(3),
	Terminate(4),
	//KeyPressed(5),
	//KeyReleased(6),
	LowerPower(7);
    	
	final int value;
    	
	Event(int value) {
		this.value = value;
	}
    	
	public int getValue() {
		return value;
	}
    	
	public static Event valueOf(int value) {
		for (Event event : values()) {
			if (event.value == value)
				return event;
		}
		return null;
	}
}