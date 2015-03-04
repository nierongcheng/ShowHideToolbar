package com.codi.showhidetoolbar.one;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.codi.showhidetoolbar.R;

import java.util.ArrayList;
import java.util.List;


public class PartOneActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private int mToolbarHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppThemeGreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_one);

        mToolbarHeight = getActionbarHeight();

        initToolbar();
        initRecyclerView();

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(Color.WHITE);
    }

    private void initRecyclerView() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setOnScrollListener(new HidingScrollListener(mToolbarHeight) {
            @Override
            public void onMove(int distance) {
                System.out.println(distance + "");
                mToolbar.setTranslationY(-distance);
            }

            @Override
            public void onShow() {
                mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void onHide() {
                mToolbar.animate().translationY(-mToolbarHeight).setInterpolator(new AccelerateInterpolator(2)).start();
            }
        });
    }

    private List<String> createItemList() {
        List<String> itemList = new ArrayList<>();
        for(int i=0;i<20;i++) {
            itemList.add("Item "+i);
        }
        return itemList;
    }

    public int getActionbarHeight() {
        TypedValue tv = new TypedValue();
        int actionbarHeight = 0;
        if(getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return actionbarHeight;
    }
}
