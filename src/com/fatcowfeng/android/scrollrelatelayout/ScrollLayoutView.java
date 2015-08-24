
package com.fatcowfeng.android.scrollrelatelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

public class ScrollLayoutView extends TextView {

    public ScrollLayoutView(Context context) {
        super(context);
    }

    public ScrollLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            return false;
        } else {
            return true;
        }
    }

}
