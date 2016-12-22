package com.zhang.weibodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.zhang.weibodemo.activity.MainActivity;
import com.zhang.weibodemo.api.MyWeiBoApi;

/**
 * Created by Mr.Z on 2016/10/28 0028.
 */

public class BaseFragment extends Fragment {

    protected MainActivity activity;
    protected MyWeiBoApi api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity) getActivity();
        api = activity.api;
    }

    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(activity, tarActivity);
        startActivity(intent);
    }
}
