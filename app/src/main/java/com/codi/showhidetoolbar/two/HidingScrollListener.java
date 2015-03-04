package com.codi.showhidetoolbar.two;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Codi on 2015/3/3.
 */
public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {
    private int mToolbarOffset = 0;
    private int mToolbarHeight;

    protected HidingScrollListener(int toolbarHeight) {
        System.out.println(toolbarHeight + "");
        this.mToolbarHeight = toolbarHeight;
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

    public abstract void onMove(int distance);
}
