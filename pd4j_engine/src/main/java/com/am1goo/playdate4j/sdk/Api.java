package com.am1goo.playdate4j.sdk;

public class Api {

    private static final ApiBridge bridge = new ApiBridge();

    public static boolean isApiAvailable() {
        return bridge.isApiAvailable();
    }

    private static long apiAddress;

    public static long getApiAddress() {
        return apiAddress;
    }

    public static void setApiAddress(long apiAddress) {
        Api.apiAddress = apiAddress;
    }

}
