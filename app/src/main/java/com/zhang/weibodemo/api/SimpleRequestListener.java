package com.zhang.weibodemo.api;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.zhang.weibodemo.utils.Logger;
import com.zhang.weibodemo.utils.ToastUtils;

/**
 * Created by Mr.Z on 2016/10/28 0028.
 */

public class SimpleRequestListener implements RequestListener {

    private Context context;
    private Dialog progressDialog;

    public SimpleRequestListener(Context context, Dialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    @Override
    public void onComplete(String s) {
        onAllDone();
        Logger.show("REQUEST onComplete", s);
    }

    @Override
    public void onWeiboException(WeiboException e) {
        onAllDone();
        ToastUtils.showToast(context, e.getMessage(), Toast.LENGTH_SHORT);
        Logger.show("REQUEST onIOException", e.toString());
    }

    public void onAllDone() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
