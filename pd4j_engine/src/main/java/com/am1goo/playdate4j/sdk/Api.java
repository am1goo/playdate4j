package com.am1goo.playdate4j.sdk;

public class Api {

    private static final ApiBridge bridge = new ApiBridge();

    public static boolean isApiAvailable() {
        return bridge.isApiAvailable();
    }

    private static Pointer apiAddress;

    public static long getApiAddress() {
        return apiAddress.getValue();
    }

    public static void setApiAddress(long apiAddress) {
        Api.apiAddress = new Pointer(apiAddress);
    }
    
    public static class Pointer {
    	
    	public static final long invalidAddr = 0;
    	public static final Pointer invalid = new Pointer(invalidAddr);
    	
    	private final long ptr;
    	
    	public Pointer(long ptr) {
    		this.ptr = ptr;
    	}
    	
    	public long getValue() {
    		return ptr;
    	}
    	
    	public boolean invalid() {
    		return ptr == invalidAddr;
    	}
    }

    public static class UInt8 {
    	
    	public static byte getJava(int nativeValue) {
    		return (byte)nativeValue;
    	}
    	
    	public static int getNative(byte javaValue) {
    		return javaValue & 0xFF;
    	}
    }
}
