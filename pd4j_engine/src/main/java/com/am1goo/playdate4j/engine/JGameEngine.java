package com.am1goo.playdate4j.engine;

import com.am1goo.playdate4j.engine.objects.JObjectsManager;
import com.am1goo.playdate4j.engine.render.JRenderManager;
import com.am1goo.playdate4j.sdk.Event;
import com.am1goo.playdate4j.sdk.GameEngine;

public class JGameEngine implements GameEngine {

	@Override
	public void start() {
		JRenderManager.start();
		JObjectsManager.start();
	}

	@Override
	public void stop() {
		JRenderManager.stop();
		JObjectsManager.stop();
	}

	@Override
	public void beforeLoop() {
		JRenderManager.beforeLoop();
		JObjectsManager.beforeLoop();
	}

	@Override
	public void afterLoop() {
		JRenderManager.afterLoop();
	}

	@Override
	public void event(Event event) {
	}
}
