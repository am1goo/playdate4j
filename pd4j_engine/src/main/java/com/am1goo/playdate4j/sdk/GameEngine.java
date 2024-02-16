package com.am1goo.playdate4j.sdk;

public interface GameEngine {

	void start();
	void stop();
	void beforeLoop();
	void afterLoop();
	void event(Event event);
}
