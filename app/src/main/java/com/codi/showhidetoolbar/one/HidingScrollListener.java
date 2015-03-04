package com.codi.showhidetoolbar.one;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Codi on 2015/3/3.
 */
public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {

    private static final float HIDE_THRESHOLD = 10;
    private static final float SHOW_THRESHOLD = 70;

    private int mToolbarOffset = 0;
    private int mToolbarHeight;
    private boolean mControlVisible = true;

    protected HidingScrollListener(int toolbarHeight) {
        System.out.println(toolbarHeight + "");
        this.mToolbarHeight = toolbarHeight;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if(newState == RecyclerView.SCROLL_STATE_IDLE) {
            if(mControlVisible) {
                if(mToolbarOffset > SHOW_THRESHOLD) {
                    setInvisible();
                } else {
                    setVisible();
                }
            } else {
                if(mToolbarHeight - mToolbarOffset > HIDE_THRESHOLD) {
                    if ((mToolbarHeight - mToolbarOffset) > SHOW_THRESHOLD) {
                        setVisible();
                    } else {
                        setInvisible();
                    }
                }
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        clipToolbarOffset();
        onMove(mToolbarOffset);

        if((mToolbarOffset < mToolbarHeight && dy > 0) || (mToolbarOffset > 0 && dy < 0)) {
            mToolbarOffset += dy;
        }
    }

    private void clipToolbarOffset() {
        if(mToolbarOffset > mToolbarHeight) {
            mToolbarOffset = mToolbarHeight;
        } else if(mToolbarOffset < 0) {
            mToolbarOffset = 0;
        }
    }

    private void setVisible() {
        if(mToolbarOffset > 0) {
            onShow();
            mToolbarOffset = 0;
        }
        mControlVisible = true;
    }

    private void setInvisible() {
        if(mToolbarOffset < mToolbarHeight) {
            onHide();
            mToolbarOffset = mToolbarHeight;
        }
        mControlVisible = false;
    }

    public abstract void onMove(int distance);
    public abstract void onShow();
    public abstract void onHide();
}
