package com.am1goo.playdate4j.engine;

import com.am1goo.playdate4j.engine.objects.JObjectsManager;
import com.am1goo.playdate4j.sdk.Event;
import com.am1goo.playdate4j.sdk.GameEngine;

public class JGameEngine implements GameEngine {

	@Override
	public void start() {
		JObjectsManager.start();
	}

	@Override
	public void stop() {
		JObjectsManager.stop();
	}

	@Override
	public void beforeLoop() {
		JObjectsManager.beforeLoop();
	}

	@Override
	public void afterLoop() {
	}

	@Override
	public void event(Event event) {
	}
}
