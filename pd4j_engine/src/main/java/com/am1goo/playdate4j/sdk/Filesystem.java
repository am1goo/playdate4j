package com.am1goo.playdate4j.sdk;

import com.am1goo.playdate4j.sdk.Api.Pointer;

import java.util.Collection;
import java.util.List;

public class Filesystem {

	private static final FilesystemBridge bridge = new FilesystemBridge();
	
    /* filesystem */
	public static String getError() {
		return bridge.getError();
	}
	
	public static boolean unlink(String path, boolean recursive) {
		int ret = bridge.unlink(path, recursive);
		return ret == 0;
	}
	
	public static boolean mkdir(String path) {
		int ret = bridge.mkdir(path);
		return ret == 0;
	}
	
	public static boolean rename(String from, String to) {
		int ret = bridge.rename(from, to);
		return ret == 0;
	}
	
	public static FilesystemBridge.FileStat stat(String path) {
		FilesystemBridge.FileStat stat = new FilesystemBridge.FileStat();
		boolean result = stat(path, stat);
		if (!result)
			return null;
		
		return stat;
	}

	public static boolean listfiles(String path, boolean showHidden, List<String> result) {
		int ret = bridge.listfiles(path, showHidden, new FilesystemBridge.FileList(result));
		return ret == 0;
	}
	
	public static boolean stat(String path, FilesystemBridge.FileStat stat) {
		int ret = bridge.stat(path, stat);
		return ret == 0;
	}
	
    /* file handles */
	public static SDFile open(String path, FileOptions mode) {
		long ptr = bridge.open(path, mode.getValue());
		Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		return new SDFile(pointer, path);
	}
	
	public static SDFile close(SDFile file) {
		int ret = bridge.close(file.ptr.getValue());
		if (ret != 0)
			return file;
		
		file.ptr.invalidate();
		return null;
	}
	
	public static class SDFile {
		
		private final Pointer ptr;
		private final String path;
		
		public SDFile(Pointer ptr, String path) {
			this.ptr = ptr;
			this.path = path;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public String getPath() {
			return path;
		}

		public void close() {
			Filesystem.close(this);
		}
		
		public boolean flush() {
			int ret = bridge.flush(ptr.getValue());
			return ret == 0;
		}
		
		public int read(byte[] buf) {
			return read(buf, buf.length);
		}
		
		public int read(byte[] buf, int len) {
			return bridge.read(ptr.getValue(), buf, len);
		}
		
		public boolean seek(int pos, int whence) {
			int ret = bridge.seek(ptr.getValue(), pos, whence);
			return ret == 0;
		}
		
		public boolean tell() {
			int ret = bridge.tell(ptr.getValue());
			return ret == 0;
		}
		
		public int write(byte[] buf) {
			return write(buf, buf.length);
		}
		
		public int write(byte[] buf, int len) {
			return bridge.write(ptr.getValue(), buf, len);
		}
	}
	
	public enum FileOptions {
		Read(0),
		ReadData(1),
		Write(2),
		Append(3);
		
		final int value;
		
		FileOptions(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
}