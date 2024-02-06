package com.am1goo.playdate4j;

public class Core {

    private static int frameCount;

    public static void init() {
        System.out.println("init");
    }

    public static void shutdown() {
        frameCount = 0;
        System.out.println("shutdown");
    }

    public static void loop() {
        frameCount++;
    }

    public static int getFrameCount() {
        return frameCount;
    }
}
