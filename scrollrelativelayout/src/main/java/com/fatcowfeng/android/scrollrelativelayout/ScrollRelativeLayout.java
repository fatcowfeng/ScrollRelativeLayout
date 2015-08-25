
package com.fatcowfeng.android.scrollrelativelayout;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * @author fatcowfeng
 * @version date: 2014-10-29 11:41:38
 */
public class ScrollRelativeLayout extends RelativeLayout {
    private Scroller mScroller = new Scroller(getContext());

    private int topOffset = 0;

    public ScrollRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, com.fatcowfeng.android.R.styleable.ScrollRelativeLayout);
        topOffset = a.getDimensionPixelSize(com.fatcowfeng.android.R.styleable.ScrollRelativeLayout_limitheight, 0);
        a.recycle();
    }

    public final void resetPosition() {
        this.mScroller.abortAnimation();
        this.mScroller.startScroll(0, getScrollY(), 0, 0 - getScrollY(), 1000);
        invalidate();
    }

    public final void scrollToTop() {
        if (getScrollY() >= topOffset)
            return;
        this.mScroller.abortAnimation();
        this.mScroller.startScroll(0, getScrollY(), 0, topOffset - getScrollY(), 200);
        invalidate();
    }

    public int getLimmitHeight() {
        return topOffset;
    }

    public void setLimitHeight(int h) {
        this.topOffset = h;
    }

    @Override
    public void computeScroll() {
        if (!this.mScroller.isFinished()) {
            if (this.mScroller.computeScrollOffset()) {
                int i = getScrollX();
                int j = getScrollY();
                int k = this.mScroller.getCurrX();
                int m = this.mScroller.getCurrY();
                if ((i != k) || (j != m))
                    scrollTo(k, m);
                invalidate();
                return;
            }
            setCache();
            return;
        }
        setCache();
    }

    private void setCache() {
        int i = getChildCount();
        for (int j = 0;; j++) {
            if (j >= i)
                return;
            getChildAt(j).setDrawingCacheEnabled(false);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    public void setListViewPositon(ListView mListview) {
        int y = getScrollY();
        if (mListview != null && mListview.getFirstVisiblePosition() <= 0) {
            mListview.setSelectionFromTop(0, -y);
        }
    }

}
