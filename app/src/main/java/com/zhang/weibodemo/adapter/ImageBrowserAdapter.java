package com.zhang.weibodemo.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.zhang.weibodemo.R;
import com.zhang.weibodemo.entity.PicUrls;
import com.zhang.weibodemo.utils.DisplayUtils;

import java.util.ArrayList;

/**
 * Created by Mr.Z on 2016/11/4 0004.
 */

public class ImageBrowserAdapter extends PagerAdapter {

    private Activity context;
    private ArrayList<PicUrls> picUrls;
    private ArrayList<View> picViews;

    private ImageLoader mImageLoader;

    public ImageBrowserAdapter(Activity context, ArrayList<PicUrls> picUrls) {
        this.context = context;
        this.picUrls = picUrls;
        this.mImageLoader = ImageLoader.getInstance();
        initImgs();
    }

    private void initImgs() {
        picViews = new ArrayList<>();

        for (int i = 0; i < picUrls.size(); i++) {
            // 填充显示图片的页面布局
            View view = View.inflate(context, R.layout.item_image_browser, null);
            picViews.add(view);
        }
    }

    @Override
    public int getCount() {
        if (picUrls.size() > 1) {
            return Integer.MAX_VALUE;
        }
        return picUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int index = position % picUrls.size();
        View view = picViews.get(index);
        final ImageView iv_image_browser = (ImageView) view.findViewById(R.id.iv_image_browser);
        PicUrls picUrl = picUrls.get(index);

        String url = picUrl.isShowOriImag() ? picUrl.getOriginal_pic() : picUrl.getBmiddle_pic();

        mImageLoader.loadImage(url, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                float scale = (float) bitmap.getHeight() / bitmap.getWidth();

                int screenWidthPixels = DisplayUtils.getScreenWidthPixels(context);
                int screenHeightPixels = DisplayUtils.getScreenHeightPixels(context);
                int height = (int) (screenWidthPixels * scale);

                if (height < screenHeightPixels) {
                    height = screenHeightPixels;
                }

                ViewGroup.LayoutParams params = iv_image_browser.getLayoutParams();
                params.height = height;
                params.width = screenWidthPixels;

                iv_image_browser.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public PicUrls getPic(int position) {
        return picUrls.get(position % picUrls.size());
    }

    public Bitmap getBitmap(int position) {
        Bitmap bitmap = null;
        View view = picViews.get(position % picViews.size());
        ImageView iv_image_browser = (ImageView) view.findViewById(R.id.iv_image_browser);
        Drawable drawable = iv_image_browser.getDrawable();
        if(drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            bitmap = bd.getBitmap();
        }

        return bitmap;
    }
}
