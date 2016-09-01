package com.demo.common.util;

/**
 * Simple data class, keeps track of when it was created
 * so that it knows when the its gone stale.
 */
public class DataUtils {

    private static final long STALE_MS = 50 * 1000; // DataUtils is stale after 5 seconds




    public static boolean isUpToDate(long timestamp) {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }
}
