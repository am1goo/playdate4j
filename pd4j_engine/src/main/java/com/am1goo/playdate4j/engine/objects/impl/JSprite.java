package com.am1goo.playdate4j.engine.objects.impl;

import com.am1goo.playdate4j.sdk.Sprite.LCDSprite;
import com.am1goo.playdate4j.engine.objects.IObject;

public class JSprite implements IObject {

	private State state;
	private LCDSprite nativeSprite;

	@Override
	public void onCreate() {
		if (state != State.None)
			return;
		
		state = State.Created;
		nativeSprite = com.am1goo.playdate4j.sdk.Sprite.newSprite();
	}

	@Override
	public void onDestroy() {
		if (state != State.Created)
			return;
		
		nativeSprite = com.am1goo.playdate4j.sdk.Sprite.freeSprite(nativeSprite);
		state = State.Destroyed;
	}
	
	@Override
	public void update() {
		if (state != State.Created)
			return;
	}

	public enum State {
		None,
		Created,
		Destroyed
	}
}
