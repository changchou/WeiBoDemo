package com.zhang.weibodemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.zhang.weibodemo.BaseActivity;
import com.zhang.weibodemo.R;
import com.zhang.weibodemo.constants.AccessTokenKeeper;

public class SplashActivity extends BaseActivity {

    private static final int WHAT_INTENT2LOGIN = 1;
    private static final int WHAT_INTENT2MAIN = 2;
    private static final int SPLASH_DUR_TIME = 1000;

    private Oauth2AccessToken accessToken;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_INTENT2LOGIN:
                    intent2Activity(LoginActivity.class);
                    finish();
                    break;
                case WHAT_INTENT2MAIN:
                    intent2Activity(MainActivity.class);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        accessToken = AccessTokenKeeper.readAccessToken(this);
        if (accessToken.isSessionValid()) {
            handler.sendEmptyMessageDelayed(WHAT_INTENT2MAIN, SPLASH_DUR_TIME);
        } else {
            handler.sendEmptyMessageDelayed(WHAT_INTENT2LOGIN, SPLASH_DUR_TIME);
        }
    }
}
