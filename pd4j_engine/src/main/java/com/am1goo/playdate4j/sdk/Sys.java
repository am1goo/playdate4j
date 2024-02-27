package com.am1goo.playdate4j.sdk;

import com.am1goo.playdate4j.sdk.SysBridge.PDDateTime;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Sys {

    private static final SysBridge bridge = new SysBridge();

    private static final List<PDMenuItem> menuItems = new ArrayList<PDMenuItem>();
    private static final PDDateTime dateTime = new PDDateTime();

    private static final PrintStream logWriter = new PrintStream(new GameOutputStream(false));
    private static final PrintStream errorWriter = new PrintStream(new GameOutputStream(true));

    /* logging */
    public static void log(Object object) {
        if (object == null) {
            log("null");
            return;
        }
        log(object.toString());
    }

    public static void log(String log) {
        System.out.println(log);
        bridge.logToConsole(log);
    }
    
    public static void logError(Throwable throwable) {
        throwable.printStackTrace(errorWriter);
        errorWriter.flush();
    }

    public static void logError(String error) {
        System.err.println(error);
        bridge.error(error);
    }
    
    /* system menu */
    public static PDMenuItem addMenuItem(String title, Runnable callback) {
    	long ptr = bridge.addMenuItem(title, new SysBridge.PDMenuItemRunnable(callback));
        Api.Pointer pointer = new Api.Pointer(ptr);
        if (pointer.invalid())
            return null;

        PDMenuItem menuItem = new PDMenuItem(pointer);
        menuItems.add(menuItem);
        return menuItem;
    }
    
    public static PDMenuItem addCheckmarkMenuItem(String title, boolean value, Runnable callback) {
        long ptr = bridge.addCheckmarkMenuItem(title, value, new SysBridge.PDMenuItemRunnable(callback));
        Api.Pointer pointer = new Api.Pointer(ptr);
        if (pointer.invalid())
            return null;

        PDMenuItem menuItem = new PDMenuItem(pointer);
        menuItems.add(menuItem);
        return menuItem;
    }
    
    public static PDMenuItem addOptionsMenuItem(String title, String[] options, Runnable callback) {
    	long ptr = bridge.addOptionsMenuItem(title, options, options.length, new SysBridge.PDMenuItemRunnable(callback));
        Api.Pointer pointer = new Api.Pointer(ptr);
        if (pointer.invalid())
            return null;

        PDMenuItem menuItem = new PDMenuItem(pointer);
        menuItems.add(menuItem);
        return menuItem;
    }
    
    public static void removeMenuItem(PDMenuItem menuItem) {
        if (menuItem == null)
            return;

        if (menuItem.ptr.invalid())
            return;

    	bridge.removeMenuItem(menuItem.ptr.getValue());
        menuItem.ptr.invalidate();
        menuItems.remove(menuItem);
    }
    
    public static void removeAllMenuItems() {
    	bridge.removeAllMenuItems();
        for (PDMenuItem menuItem : menuItems) {
            menuItem.ptr.invalidate();
        }
        menuItems.clear();
    }
    
    /* date and time */
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
    
    public static PDDateTime convertEpochToDateTime(long epoch) {
    	convertEpochToDateTime(epoch, dateTime);
    	return dateTime;
    }
    
    public static void convertEpochToDateTime(long epoch, PDDateTime result) {
    	bridge.convertEpochToDateTime(epoch, dateTime);
    }
    
    public static long convertDateTimeToEpoch(PDDateTime dateTime) {
    	return bridge.convertDateTimeToEpoch(dateTime.year(), dateTime.month(), dateTime.day(), dateTime.weekday(), dateTime.hour(), dateTime.minute(), dateTime.second());
    }

    public static boolean shouldDisplay24HourTime() {
        return bridge.shouldDisplay24HourTime();
    }

    /* miscellaneous */
    public static boolean getFlipped() {
        return bridge.getFlipped();
    }

    public static boolean getReduceFlashing() {
        return bridge.getReduceFlashing();
    }

    public static void setMenuImage(Graphics.LCDBitmap bitmap, int xOffset) {
        long ptr = 0;
        if (bitmap != null)
            ptr = bitmap.getPointer().getValue();
        bridge.setMenuImage(ptr, xOffset);
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
    
    public static PDLanguage getLanguage() {
    	int language = bridge.getLanguage();
    	return PDLanguage.valueOf(language);
    }
    
    public enum PDLanguage {
    	English(0, "en"),
    	Japanese(1, "jp"),
    	Unknown(2, "");
    	
    	final int value;
    	final String code;
    	
    	PDLanguage(int value, String code) {
    		this.value = value;
    		this.code = code;
    	}
    	
    	public int getValue() {
    		return value;
    	}
    	
    	public String getCode() {
    		return code;
    	}
    	
    	public static PDLanguage valueOf(int value) {
    		for (PDLanguage language : values()) {
    			if (language.value == value) {
    				return language;
    			}
    		}
    		return PDLanguage.Unknown;
    	}
    }

    public static class PDMenuItem {

        private final Api.Pointer ptr;

        public PDMenuItem(Api.Pointer ptr) {
            this.ptr = ptr;
        }

        public Api.Pointer getPointer() {
            return ptr;
        }

        public void free() {
            Sys.removeMenuItem(this);
        }

        public String getMenuItemTitle() {
            return bridge.getMenuItemTitle(ptr.getValue());
        }

        public void setMenuItemTitle(String title) {
            bridge.setMenuItemTitle(ptr.getValue(), title);
        }

        public boolean getMenuItemValue() {
            return bridge.getMenuItemValue(ptr.getValue());
        }

        public void setMenuItemValue(boolean checked) {
            bridge.setMenuItemValue(ptr.getValue(), checked);
        }
    }
}
