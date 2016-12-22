package com.zhang.weibodemo.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.zhang.weibodemo.R;

/**
 * Created by Mr.Z on 2016/10/27 0027.
 */

public class ImageOptHelper {

    public static DisplayImageOptions getImgOptions(){
        return new DisplayImageOptions.Builder()
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.drawable.timeline_image_loading)
                .showImageForEmptyUri(R.drawable.timeline_image_loading)
                .showImageOnFail(R.drawable.timeline_image_failure)
                .build();
    }

    public static DisplayImageOptions getAvatarOptions() {
        return new DisplayImageOptions.Builder()
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.drawable.avatar_default)
                .showImageForEmptyUri(R.drawable.avatar_default)
                .showImageOnFail(R.drawable.avatar_default)
                .displayer(new RoundedBitmapDisplayer(999))
                .build();
    }

    public static DisplayImageOptions getCornerOptions(int cornerRadiusPixels) {
        return new DisplayImageOptions.Builder()
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.drawable.timeline_image_loading)
                .showImageForEmptyUri(R.drawable.timeline_image_loading)
                .showImageOnFail(R.drawable.timeline_image_loading)
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels)).build();
    }
}
