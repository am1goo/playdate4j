package com.am1goo.playdate4j.sdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Game {

    private static GameCycle cycle;
    private static int frameCount;

    public static void create(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        Constructor<?> cctr = clazz.getDeclaredConstructor();
        boolean accessibleChanged = false;
        if (!cctr.isAccessible()) {
            cctr.setAccessible(true);
            accessibleChanged = true;
        }

        GameCycle cycle = (GameCycle) cctr.newInstance();
        if (accessibleChanged)
            cctr.setAccessible(false);

        create(cycle);
    }

    public static void create(GameCycle cycle) {
    	Game.cycle = cycle;
    }

    public static void init() {
        System.out.println("init: started");
        try {
            cycle.start();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        System.out.println("init: finished");
    }

    public static void shutdown() {
        System.out.println("shutdown: started");
        try {
            cycle.stop();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        frameCount = 0;
        System.out.println("shutdown: finished");
    }

    public static void loop() {
        try {
            cycle.loop();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        frameCount++;
    }

    public static boolean isCycling() {
    	return cycle != null;
    }
    
    public static int getFrameCount() {
        return frameCount;
    }
}
