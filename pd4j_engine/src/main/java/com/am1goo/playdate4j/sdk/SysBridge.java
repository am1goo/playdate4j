package com.am1goo.playdate4j.sdk;

public class SysBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    /* logging */
    public native void logToConsole(String log);
    public native void error(String error);
    
    /* system menu */
    public native long addMenuItem(String title);
    public native long addCheckmarkMenuItem(String title, boolean value);
    public native long addOptionsMenuItem(String title, String[] options, int optionsCount);
    public native void removeMenuItem(long menuItemPtr);
    public native void removeAllMenuItems();
    public native String getMenuItemTitle(long menuItemPtr);
    public native void setMenuItemTitle(long menuItemPtr, String title);
    public native int getMenuItemValue(long menuItemPtr);
    public native void setMenuItemValue(long menuItemPtr, int value);
    
    /* miscellaneous */
    public native void drawFps(int x, int y);
    public native boolean getFlipped();
    public native boolean getReduceFlashing();
    public native void setMenuImage(long bitmapPtr, int xOffset);
    public native float getBatteryPercentage();
    public native float getBatteryVoltage();
    public native void clearICache();

    /* time and date */
    public native long getCurrentTimeMilliseconds();
    public native long getSecondsSinceEpoch(long milliseconds);
    public native void resetElapsedTime();
    public native float getElapsedTime();
    public native int getTimezoneOffset();
    public native void convertEpochToDateTime(long epoch, PDDateTime dateTime);
    public native long convertDateTimeToEpoch(int year, short month, short day, short weekday, short hour, short minute, short second);
    public native boolean shouldDisplay24HourTime();

    public native void setAutoLockDisabled(boolean disable);
    public native boolean setCrankSoundsDisabled(boolean disable);
    
    public native int getLanguage();
    
    public static class PDDateTime {
    	int year;
    	short month;	// 1-12
    	short day;		// 1-31
    	short weekday;	// 1=monday-7=sunday
    	short hour;
    	short minute;
    	short second;
    	
    	public int year() {
    		return year;
    	}
    	
    	public short month() {
    		return month;
    	}
    	
    	public short day() {
    		return day;
    	}
    	
    	public short weekday() {
    		return weekday;
    	}
    	
    	public short hour() {
    		return hour;
    	}
    	
    	public short minute() {
    		return minute;
    	}
    	
    	public short second() {
    		return second;
    	}
    	
    	public void set(int year, short month, short day, short weekday, short hour, short minute, short second) {
    		this.year = year;
    		this.month = month;
    		this.day = day;
    		this.weekday = weekday;
    		this.hour = hour;
    		this.minute = minute;
    		this.second = second;
    	}
    }
}
