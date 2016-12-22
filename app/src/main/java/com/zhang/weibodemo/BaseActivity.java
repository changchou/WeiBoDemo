package com.zhang.weibodemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhang.weibodemo.api.MyWeiBoApi;
import com.zhang.weibodemo.constants.AccessTokenKeeper;
import com.zhang.weibodemo.constants.MyConstants;
import com.zhang.weibodemo.utils.Logger;
import com.zhang.weibodemo.utils.ToastUtils;

/**
 * Created by Mr.Z on 2016/10/28 0028.
 */

public class BaseActivity extends AppCompatActivity {

    protected String TAG;
    protected BaseApplication application;
    protected SharedPreferences sp;

    protected MyWeiBoApi api;
    protected Gson gson;
    protected ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = this.getClass().getSimpleName();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        application = (BaseApplication) getApplication();
        sp = getSharedPreferences(MyConstants.SP_NAME, MODE_PRIVATE);

        api = new MyWeiBoApi(this,
                MyConstants.APP_KEY,
                AccessTokenKeeper.readAccessToken(this));
        imageLoader = ImageLoader.getInstance();
        gson = new Gson();
    }


    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(this, tarActivity);
        startActivity(intent);
    }


    protected void showToast(String msg) {
        ToastUtils.showToast(this, msg, Toast.LENGTH_SHORT);
    }


    protected void showLog(String msg) {
        Logger.show(TAG, msg);
    }
}
