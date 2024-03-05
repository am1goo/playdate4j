package com.am1goo.playdate4j.sdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Game {

    private static boolean runningInSimulator;
	private static GameEngine engine;
    private static GameCycle cycle;
    private static int frameCount;
    private static long frameTimeMillis;
    private static long totalTimeMillis;

    private static final long INITIAL_DATE_MILLIS = System.currentTimeMillis();
    private static final float MILLIS = 1f / 1000f;

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
    
    public static <T extends GameCycle> void create(Class<T> clazz) {
    	GameCycle cycle = newCycle(clazz);
    	create(cycle);
    }

    public static void create(GameCycle cycle) {
    	Game.cycle = cycle;
    }

    public static void init(boolean runningInSimulator) {
        Game.runningInSimulator = runningInSimulator;
        Sys.log("init: started, runningInSimulator=" + runningInSimulator);
        try {
            engine.start();
        }
        catch (Throwable ex) {
            Sys.logError(ex);
        }
        try {
            cycle.start();
        }
        catch (Throwable ex) {
            Sys.logError(ex);
        }
        Sys.log("init: finished");
    }

    public static void shutdown() {
        event(Event.Terminate.getValue());

        Sys.log("shutdown: started");
        try {
            cycle.stop();
        }
        catch (Throwable ex) {
            Sys.logError(ex);
        }
        try {
            engine.stop();
        }
        catch (Throwable ex) {
            Sys.logError(ex);
        }
        frameCount = 0;
        frameTimeMillis = 0;
        Sys.log("shutdown: finished");
    }

    public static void loop() {
        totalTimeMillis = System.currentTimeMillis();
        frameTimeMillis = totalTimeMillis - frameTimeMillis;
        try {
            engine.beforeLoop();
        }
        catch (Throwable ex) {
            Sys.logError(ex);
        }
        try {
            cycle.loop();
        }
        catch (Throwable ex) {
            Sys.logError(ex);
        }
        try {
            cycle.ui();
        }
        catch (Throwable ex) {
            Sys.logError(ex);
        }
        try {
            engine.afterLoop();
        }
        catch (Throwable ex) {
            Sys.logError(ex);
        }
        frameCount++;
        frameTimeMillis = totalTimeMillis;
    }
    
    public static void event(int value) {
    	Event event = Event.valueOf(value);
    	if (event == null) {
    		Sys.logError("unsupported event type " + value);
    		return;
    	}
    	
    	try {
    		engine.event(event);
    	}
    	catch (Throwable ex) {
    		Sys.logError(ex);
    	}
    	try {
    		cycle.event(event);
    	}
    	catch (Throwable ex) {
    		Sys.logError(ex);
    	}
    }

    public static boolean isSimulator () {
        return runningInSimulator;
    }

    public static boolean isCycling() {
    	return cycle != null;
    }
    
    public static int getFrameCount() {
        return frameCount;
    }

    public static float getDeltaTime() {
        return frameTimeMillis * MILLIS;
    }

    public static float getTime() {
        return (totalTimeMillis - INITIAL_DATE_MILLIS) * MILLIS;
    }
    
    @SuppressWarnings("unchecked")
	private static <T extends GameEngine> GameEngine newEngine(String className) {
        try {
            Class<T> clazz = (Class<T>) Class.forName(className);
            return newEngine(clazz);
        }
        catch (Throwable ex) {
            Sys.logError(ex);
            return null;
        }
    }
    
    @SuppressWarnings("deprecation")
	private static <T extends GameEngine> GameEngine newEngine(Class<T> clazz) {
        if (clazz == null)
            return null;

        try {
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
        catch (Throwable ex) {
            Sys.logError(ex);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends GameCycle> GameCycle newCycle(String className) {
        try {
            Class<T> clazz = (Class<T>) Class.forName(className);
            return newCycle(clazz);
        }
        catch (Throwable ex) {
            Sys.logError(ex);
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    private static <T extends GameCycle> GameCycle newCycle(Class<T> clazz) {
        if (clazz == null)
            return null;

        try {
            Constructor<T> cctr = clazz.getDeclaredConstructor();
            boolean accessibleChanged = false;
            if (!cctr.isAccessible()) {
                cctr.setAccessible(true);
                accessibleChanged = true;
            }

            GameCycle cycle = cctr.newInstance();
            if (accessibleChanged)
                cctr.setAccessible(false);

            return cycle;
        }
        catch (Throwable ex) {
            Sys.logError(ex);
            return null;
        }
    }
}
