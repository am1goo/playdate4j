package com.am1goo.playdate4j.engine.objects;

public interface IObject {
	
	State getState();
	void onCreate();
	void onDestroy();
	void update();
	
	public enum State {
		None,
		Created,
		Destroyed
	}
}
