package com.zhang.weibodemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Mr.Z on 2016/10/26 0026.
 */

public class ToastUtils {

    private static Toast toast;

    public static void showToast(Context context, CharSequence text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
            toast.setDuration(duration);
        }
        toast.show();
    }

}
