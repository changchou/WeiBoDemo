package com.zhang.weibodemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Mr.Z on 2016/10/27 0027.
 */

public class WrapHeightGridView extends GridView {

    public WrapHeightGridView(Context context) {
        super(context);
    }

    public WrapHeightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapHeightGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
