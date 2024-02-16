package com.am1goo.playdate4j.sdk;

public interface GameCycle {
	
    void start();
    void stop();
    void loop();
    void ui();
	void event(Event event);
}
