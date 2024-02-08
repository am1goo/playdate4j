package com.am1goo.playdate4j.sdk;

public class Filesystem {

	private static final FilesystemBridge bridge = new FilesystemBridge();
	
	public static String getError() {
		return bridge.getError();
	}
}