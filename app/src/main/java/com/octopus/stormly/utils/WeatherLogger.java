package com.octopus.stormly.utils;

import android.util.Log;

public class WeatherLogger {
    private static final String TAG = WeatherLogger.class.getSimpleName();

    public static void error(String msg, Throwable t){
        if (t == null)
            Log.e(TAG, msg);
        else
            Log.e(TAG, msg, t);
    }

    public static void debug(String msg){
        Log.d(TAG, msg);
    }
}
