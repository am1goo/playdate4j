package com.am1goo.playdate4j.engine.objects.impl;

import com.am1goo.playdate4j.engine.objects.IObjectFactory;

public class JSpriteFactory implements IObjectFactory {

	@Override
	public Class<?> getObjectClass() {
		return JSprite.class;
	}

	@Override
	public java.lang.Object create() {
		return new JSprite();
	}	
}
