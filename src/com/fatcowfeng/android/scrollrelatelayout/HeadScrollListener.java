
package com.fatcowfeng.android.scrollrelatelayout;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/**
 * @author fatcowfeng
 * @version date: 2014-10-30 上午10:09:09
 */

public class HeadScrollListener implements OnScrollListener {
    protected ScrollRelativeLayout scrollLayout;

    protected boolean isListViewVisibility = true;

    protected boolean isNeedScroll = true;

    public boolean isNeedScroll() {
        return isNeedScroll;
    }

    public void setNeedScroll(boolean isNeedScroll) {
        this.isNeedScroll = isNeedScroll;
    }

    public boolean isListViewVisibility() {
        return isListViewVisibility;
    }

    public void setListViewVisibility(boolean isListViewVisibility) {
        this.isListViewVisibility = isListViewVisibility;
    }

    public ScrollRelativeLayout getScrollLayout() {
        return scrollLayout;
    }

    public void setScrollLayout(ScrollRelativeLayout scrollLayout) {
        this.scrollLayout = scrollLayout;
    }

    public HeadScrollListener() {
    }

    public HeadScrollListener(ScrollRelativeLayout scrollLayout) {
        this.scrollLayout = scrollLayout;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
            int totalItemCount) {
        if (view.getVisibility() != View.VISIBLE) {
            return;
        }
        if (!isListViewVisibility()) {
            return;
        }

        if (!isNeedScroll())
            return;

        if (scrollLayout != null) {
            if (firstVisibleItem <= 0) {
                View v = view.getChildAt(0);
                if (v != null) {
                    int firstTop = v.getTop();

                    if (firstTop <= 0) {
                        // listview scroll
                        if (-firstTop <= scrollLayout.getLimmitHeight()) {
                            scrollTo(0, 0 - firstTop);
                        } else {
                            scrollTo(0, scrollLayout.getLimmitHeight());
                        }
                    } else {
                        scrollTo(0, 0);
                    }
                }
            } else {
                // listview scroll really fast,sometime we should check position
                if (scrollLayout.getLimmitHeight() > scrollLayout.getScrollY()) {
                    scrollTo(0, scrollLayout.getLimmitHeight());
                }
            }
        }
    }

    private void scrollTo(int x, int y) {
        scrollLayout.scrollTo(x, y);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (view.getVisibility() != View.VISIBLE) {
            return;
        }
        if (!isListViewVisibility()) {
            return;
        }

        if (!isNeedScroll())
            return;

        if (scrollLayout != null) {
            switch (scrollState) {
            // case SCROLL_STATE_FLING:
                case SCROLL_STATE_IDLE: {
                    int first = view.getFirstVisiblePosition();
                    if (first <= 0) {
                        View firstview = view.getChildAt(0);
                        if (firstview != null) {
                            int firstTop = firstview.getTop();
                            if (firstTop <= 0 && -firstTop <= scrollLayout.getLimmitHeight()) {
                                scrollTo(0, 0 - firstTop);
                            }
                        }

                    } else {
                    }
                }
                    break;

                case SCROLL_STATE_TOUCH_SCROLL: {

                    int first = view.getFirstVisiblePosition();
                    if (first > 0) {
                        scrollTo(0, scrollLayout.getLimmitHeight());
                    } else {
                        View firstview = view.getChildAt(0);
                        if (firstview != null) {
                            int firstTop = firstview.getTop();
                            if (firstTop <= 0 && -firstTop <= scrollLayout.getLimmitHeight()) {
                                scrollTo(0, 0 - firstTop);
                            }
                        }
                    }

                }
                    break;

                default:
                    break;
            }
        }
    }

}
