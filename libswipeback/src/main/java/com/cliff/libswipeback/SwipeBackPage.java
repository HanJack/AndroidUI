package com.cliff.libswipeback;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;

/**
 * Created by Mr.Jude on 2015/8/3.
 * 每个滑动页面的管理
 */
public class SwipeBackPage {
    Activity mActivity;
    SwipeBackLayout mSwipeBackLayout;
    RelateSlider slider;

    SwipeBackPage(Activity activity) {
        this.mActivity = activity;
    }

    //
    void onCreate() {
        mActivity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mActivity.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        mSwipeBackLayout = new SwipeBackLayout(mActivity);
        mSwipeBackLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        slider = new RelateSlider(this);
    }

    void onPostCreate() {
        mSwipeBackLayout.attachToActivity(mActivity);
    }


    public SwipeBackPage setSwipeRelateEnable(boolean enable) {
        slider.setEnable(enable);
        return this;
    }

    public SwipeBackPage setSwipeRelateOffset(int offset) {
        slider.setOffset(offset);
        return this;
    }

    public SwipeBackPage setSwipeBackEnable(boolean enable) {
        mSwipeBackLayout.setEnableGesture(enable);
        return this;
    }

    public SwipeBackPage setSwipeEdge(int swipeEdge) {
        mSwipeBackLayout.setEdgeSize(swipeEdge);
        return this;
    }

    public SwipeBackPage setSwipeEdgePercent(float swipeEdgePercent) {
        mSwipeBackLayout.setEdgeSizePercent(swipeEdgePercent);
        return this;
    }

    public SwipeBackPage setSwipeSensitivity(float sensitivity) {
        mSwipeBackLayout.setSensitivity(mActivity, sensitivity);
        return this;
    }

    public SwipeBackPage setScrimColor(int color) {
        mSwipeBackLayout.setScrimColor(color);
        return this;
    }

    public SwipeBackPage setClosePercent(float percent) {
        mSwipeBackLayout.setScrollThreshold(percent);
        return this;
    }

    public SwipeBackPage addListener(SwipeListener listener) {
        mSwipeBackLayout.addSwipeListener(listener);
        return this;
    }

    public SwipeBackPage removeListener(SwipeListener listener) {
        mSwipeBackLayout.removeSwipeListener(listener);
        return this;
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return mSwipeBackLayout;
    }

    public void scrollToFinishActivity() {
        mSwipeBackLayout.scrollToFinishActivity();
    }

}
