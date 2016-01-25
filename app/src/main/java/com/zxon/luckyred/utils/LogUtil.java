package com.zxon.luckyred.utils;

import android.util.Log;

/**
 * Created by leon on 16/1/22.
 */
public class LogUtil {

    public static final String TAG = "debug_ang";
    public static final boolean DEBUG = true;

    public static void d(String msg) {
        if (DEBUG == false) return;
        Log.d(TAG, msg);
    }

    public static void i(String msg) {
        if (DEBUG == false) return;
        Log.i(TAG, msg);
    }

}