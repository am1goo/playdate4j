package com.am1goo.playdate4j.engine.objects;

import com.am1goo.playdate4j.sdk.Sys;
import com.am1goo.playdate4j.engine.objects.impl.JSpriteFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class JObjectsManager {
	
	private static final HashMap<Class<?>, IObjectFactory> factories = new HashMap<>();
	private static final HashSet<IObject> objects = new HashSet<>();
	
	public JObjectsManager() {
		register(new JSpriteFactory());
	}
	
	public static void start() {
		//do nothing
	}
	
	public static void stop() {
		Iterator<IObject> itr = objects.iterator();
		while (itr.hasNext()) {
			IObject obj = itr.next();
			destroy(obj);
		}
	}
	
	public static void beforeLoop() {
		for (IObject obj : objects) {
			obj.update();
		}
	}
	
	public static boolean register(IObjectFactory factory) {
		if (factory == null)
			return false;
		
		if (factories.containsKey(factory.getObjectClass())) {
			Sys.logError("register: cannot register " + factory.getClass().getName() + ", because same factory already registered");
			return false;
		}
		
		factories.put(factory.getObjectClass(), factory);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IObject> T create(Class<T> clazz) {
		T obj = null;
		if (factories.containsKey(clazz)) {
			obj = (T)factories.get(clazz).create();
		}
		else {
			try {
				Constructor<T> cctr = clazz.getDeclaredConstructor();
				obj = (T)cctr.newInstance();
			}
			catch (Exception ex) {
				Sys.logError(ex);
			}
		}
		
		if (obj == null)
			return null;
		
		objects.add(obj);
		obj.onCreate();
		return obj;
	}
	
	public static <T extends IObject> boolean destroy(T obj) {
		if (obj == null)
			return false;
		
		if (!objects.contains(obj))
			return false;
		
		obj.onDestroy();
		objects.remove(obj);
		return true;
	}
}
