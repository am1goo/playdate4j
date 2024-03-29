package com.am1goo.playdate4j.sdk;

import java.util.Collection;
import java.util.List;

public class FilesystemBridge {

    static {
        Sdk.loadRequiredLibraries();
    }

    /* filesystem */
	public native String getError();
	
	public native int unlink(String path, boolean recursive);
	public native int mkdir(String path);
	public native int rename(String from, String to);
	public native int stat(String path, FileStat stat);
	public native int listfiles(String path, boolean showHidden, FileList result);
	
	/* file handles */
	public native long open(String path, int mode);
	public native int close(long filePtr);
	public native int flush(long filePtr);
	public native int read(long filePtr, byte[] buf, long len);
	public native int seek(long filePtr, int pos, int whence);
	public native int tell(long filePtr);
	public native int write(long filePtr, byte[] buf, long len);
	
	public static class FileStat {
		private boolean isdir;
		private int size;
		private int year;
		private int month;
		private int day;
		private int hour;
		private int minute;
		private int second;
		
		public boolean isDir() {
			return isdir;
		}
		
		public int size() {
			return size;
		}
		
		public int year() {
			return year;
		}
		
		public int month() {
			return month;
		}
		
		public int day() {
			return day;
		}
		
		public int hour() {
			return hour;
		}
		
		public int minute() {
			return minute;
		}
		
		public int second() {
			return second;
		}
		
		public void set(boolean isdir, int size, int year, int month, int day, int hour, int minute, int second) {
			this.isdir = isdir;
			this.size = size;
			this.year = year;
			this.month = month;
			this.day = day;
			this.hour = hour;
			this.minute = minute;
			this.second = second;
		}

		@Override
		public String toString() {
			return "FileStat{" +
					"isdir=" + isdir +
					", size=" + size +
					", year=" + year +
					", month=" + month +
					", day=" + day +
					", hour=" + hour +
					", minute=" + minute +
					", second=" + second +
					'}';
		}
	}

	public static class FileList {

		private final Collection<String> list;

		public FileList(Collection<String> list) {
			this.list = list;
		}

		public boolean add(String str) {
			return list.add(str);
		}
	}
}