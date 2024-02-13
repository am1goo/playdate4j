package com.am1goo.playdate4j.sdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Game {

	private static GameEngine engine;
    private static GameCycle cycle;
    private static int frameCount;
    private static long frameTimeMillis;

    public static void engine(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    	GameEngine engine = newEngine(className);
    	engine(engine);
    }
    
    public static <T extends GameEngine> void engine(Class<T> clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	GameEngine engine = newEngine(clazz);
    	engine(engine);
    }
    
    public static void engine(GameEngine cycle) {
    	Game.engine = cycle;
    }

    public static void create(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        GameCycle cycle = newCycle(className); 
        create(cycle);
    }
    
    public static <T extends GameCycle> void create(Class<T> clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	GameCycle cycle = newCycle(clazz);
    	create(cycle);
    }

    public static void create(GameCycle cycle) {
    	Game.cycle = cycle;
    }

    public static void init() {
        Sys.log("init: started");
        try {
            engine.start();
        }
        catch (Exception ex) {
            Sys.logError(ex);
        }
        try {

            cycle.start();
        }
        catch (Exception ex) {
            Sys.logError(ex);
        }
        Sys.log("init: finished");
    }

    public static void shutdown() {
        Sys.log("shutdown: started");
        try {
            cycle.stop();
        }
        catch (Exception ex) {
            Sys.logError(ex);
        }
        try {
            engine.stop();
        }
        catch (Exception ex) {
            Sys.logError(ex);
        }
        frameCount = 0;
        frameTimeMillis = 0;
        Sys.log("shutdown: finished");
    }

    public static void loop() {
        long millis = System.currentTimeMillis();
        frameTimeMillis = millis - frameTimeMillis;
        try {
            engine.beforeLoop();
        }
        catch (Exception ex) {
            Sys.logError(ex);
        }
        try {
            cycle.loop();
        }
        catch (Exception ex) {
            Sys.logError(ex);
        }
        try {
            cycle.ui();
        }
        catch (Exception ex) {
            Sys.logError(ex);
        }
        try {
            engine.afterLoop();
        }
        catch (Exception ex) {
            Sys.logError(ex);
        }
        frameCount++;
        frameTimeMillis = millis;
    }

    public static boolean isCycling() {
    	return cycle != null;
    }
    
    public static int getFrameCount() {
        return frameCount;
    }

    public static float getDeltaTime() {
        return frameTimeMillis * (1f / 1000f);
    }
    
    @SuppressWarnings("unchecked")
	private static <T extends GameEngine> GameEngine newEngine(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	 Class<T> clazz = (Class<T>) Class.forName(className);
    	 return newEngine(clazz);
    }
    
    @SuppressWarnings("deprecation")
	private static <T extends GameEngine> GameEngine newEngine(Class<T> clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Constructor<T> cctr = clazz.getDeclaredConstructor();
        boolean accessibleChanged = false;
        if (!cctr.isAccessible()) {
            cctr.setAccessible(true);
            accessibleChanged = true;
        }

        GameEngine engine = (GameEngine) cctr.newInstance();
        if (accessibleChanged)
            cctr.setAccessible(false);
        
        return engine;
    }

    @SuppressWarnings("unchecked")
    private static <T extends GameCycle> GameCycle newCycle(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	 Class<T> clazz = (Class<T>) Class.forName(className);
    	 return newCycle(clazz);
    }

    @SuppressWarnings("deprecation")
    private static <T extends GameCycle> GameCycle newCycle(Class<T> clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Constructor<T> cctr = clazz.getDeclaredConstructor();
        boolean accessibleChanged = false;
        if (!cctr.isAccessible()) {
            cctr.setAccessible(true);
            accessibleChanged = true;
        }

        GameCycle cycle = (GameCycle) cctr.newInstance();
        if (accessibleChanged)
            cctr.setAccessible(false);
        
        return cycle;
    }
}
