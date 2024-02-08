package com.am1goo.playdate4j.sdk;

public class Sound {

	private static final SoundBridge bridge = new SoundBridge();
	
	public static int getCurrentTime() {
		return bridge.getCurrentTime();
	}
}
