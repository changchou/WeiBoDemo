package com.zhang.weibodemo.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by Mr.Z on 2016/11/2 0002.
 */

public class Pull2RefreshListView extends PullToRefreshListView {

    public Pull2RefreshListView(Context context) {
        super(context);
    }

    public Pull2RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Pull2RefreshListView(Context context, Mode mode) {
        super(context, mode);
    }

    public Pull2RefreshListView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onPlvScrollListener != null) {
            onPlvScrollListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    private OnPlvScrollListener onPlvScrollListener;

    public void setOnPlvScrollListener(OnPlvScrollListener onPlvScrollListener) {
        this.onPlvScrollListener = onPlvScrollListener;
    }

    public interface OnPlvScrollListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
