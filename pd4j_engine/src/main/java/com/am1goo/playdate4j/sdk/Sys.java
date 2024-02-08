package com.am1goo.playdate4j.sdk;

public class Sys {

    private static final SysBridge bridge = new SysBridge();

    public static void log(String log) {
        System.out.println(log);
        bridge.logToConsole(log);
    }

    public static void logError(String error) {
        System.err.println(error);
        bridge.error(error);
    }

    public static long getCurrentTimeMilliseconds() {
        return bridge.getCurrentTimeMilliseconds();
    }

    public static long getSecondsSinceEpoch(long milliseconds) {
        return bridge.getSecondsSinceEpoch(milliseconds);
    }

    public static void resetElapsedTime(){
        bridge.resetElapsedTime();
    }

    public static float getElapsedTime() {
        return bridge.getElapsedTime();
    }

    public static int getTimezoneOffset() {
        return bridge.getTimezoneOffset();
    }

    public static boolean shouldDisplay24HourTime() {
        return bridge.shouldDisplay24HourTime();
    }

    public static boolean getFlipped() {
        return bridge.getFlipped();
    }

    public static boolean getReduceFlashing() {
        return bridge.getReduceFlashing();
    }

    public static void drawFps(int x, int y) {
        bridge.drawFps(x, y);
    }

    public static float getBatteryPercentage() {
        return bridge.getBatteryPercentage();
    }

    public static float getBatteryVoltage() {
        return bridge.getBatteryVoltage();
    }

    public static void clearICache() {
        bridge.clearICache();
    }
    
    public static void setAutoLockDisabled(boolean disable) {
    	bridge.setAutoLockDisabled(disable);
    }
    
    public static boolean setCrankSoundsDisabled(boolean disable) {
    	return bridge.setCrankSoundsDisabled(disable);
    }
}
