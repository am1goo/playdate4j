package com.am1goo.playdate4j.engine.objects;

public interface IObjectFactory {
	
	Class<?> getObjectClass();
	Object create();
}
