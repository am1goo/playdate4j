package com.am1goo.playdate4j;

import com.am1goo.playdate4j.example.ExampleGameCycle;
import com.am1goo.playdate4j.game.IGameCycle;
import com.am1goo.playdate4j.sdk.Display;
import com.am1goo.playdate4j.sdk.Graphics;
import com.am1goo.playdate4j.sdk.Sys;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Core {

    private static IGameCycle cycle;
    private static int frameCount;

    public static void create(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        Constructor<?> cctr = clazz.getDeclaredConstructor();
        boolean accessibleChanged = false;
        if (!cctr.isAccessible()) {
            cctr.setAccessible(true);
            accessibleChanged = true;
        }

        IGameCycle cycle = (IGameCycle) cctr.newInstance();
        if (accessibleChanged)
            cctr.setAccessible(false);

        create(cycle);
    }

    public static void create(IGameCycle cycle) {
        Core.cycle = cycle;
    }

    public static void init() {
        create(new ExampleGameCycle());
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

    public static boolean isCycling() { return cycle != null; }
    public static int getFrameCount() {
        return frameCount;
    }
}
