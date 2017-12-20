package com.example.administrator.myweather.util;

import android.util.Log;

/**
 * Created by zhengwuy on 2017/11/23.
 * email: 13802885114@139.com
 * des:
 */

public class LogUtil {
    public static final int LOG_LEVEL_D = 1;
    public static final int LOG_LEVEL_E = 2;
    public static int sCurrentLogLevel = LOG_LEVEL_E;

    public static void setLogLeve(int leve) {
        sCurrentLogLevel = leve;
    }

    public static void d(String tag, String message) {
        if (sCurrentLogLevel <= LOG_LEVEL_D) {
            Log.d(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (sCurrentLogLevel <= LOG_LEVEL_E) {
            Log.e(tag, message);
        }
    }
}
