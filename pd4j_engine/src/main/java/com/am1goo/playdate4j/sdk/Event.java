package com.am1goo.playdate4j.sdk;
    
public enum Event {
	//Init(0),
	//InitLua(1),
	Lock(2),
	Unlock(3),
	Pause(4),
	Resume(5),
	Terminate(6),
	//KeyPressed(7),
	//KeyReleased(8),
	LowerPower(9);
    	
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