package com.zhang.weibodemo.utils;

import android.util.Log;

import com.zhang.weibodemo.constants.MyConstants;


/**
 * Created by Mr.Z on 2016/10/26 0026.
 */

public class Logger {
    /**
     * 显示LOG(默认info级别)
     *
     * @param TAG
     * @param msg
     */
    public static void show(String TAG, String msg) {
        if (!MyConstants.isShowLog) {
            return;
        }
        show(TAG, msg, Log.INFO);
    }

    /**
     * 显示LOG
     *
     * @param TAG
     * @param msg
     * @param level 1-info; 2-debug; 3-verbose
     */
    private static void show(String TAG, String msg, int level) {
        if (!MyConstants.isShowLog) {
            return;
        }
        switch (level) {
            case Log.VERBOSE:
                Log.v(TAG, msg);
                break;
            case Log.DEBUG:
                Log.d(TAG, msg);
                break;
            case Log.INFO:
                Log.i(TAG, msg);
                break;
            case Log.WARN:
                Log.w(TAG, msg);
                break;
            case Log.ERROR:
                Log.e(TAG, msg);
                break;
            default:
                Log.i(TAG, msg);
                break;
        }
    }
}
