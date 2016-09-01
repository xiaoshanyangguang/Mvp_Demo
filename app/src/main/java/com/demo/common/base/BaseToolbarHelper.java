package com.demo.common.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.demo.R;

/**
 * @创建者 piper
 * @创建时间 2016/8/22 23:47
 * @描述 TODO
 */
public abstract class BaseToolbarHelper<T extends Toolbar> {
    private LayoutInflater mInflater;
    private  Context        mContext;
    private  FrameLayout    mContentView;
    private  T        mToolBar;
    private int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };
    private View mUserView;

    public BaseToolbarHelper(Context context , int layoutId){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        initConterntView();
        initUserView(layoutId);
        initToolBar();
    }

    public void initToolBar() {
        View toolBar = mInflater.inflate(getToolBarLayoutId(), mContentView);
        mToolBar = (T) toolBar.findViewById(R.id.toolbar);
    }

    /**
     * 覆写此方法提供不同的自定义toolbar
     * @return
     */
    public abstract int getToolBarLayoutId();

    public void initUserView(int layoutId) {
        mUserView = mInflater.inflate(layoutId, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
        boolean overly = typedArray.getBoolean(0, false);
        int dimension = (int) typedArray.getDimension(1, mContext.getResources().getDimension(R.dimen.actionbar_size));
        typedArray.recycle();
        params.topMargin = overly?0:dimension;
        mContentView.addView(mUserView,params);
    }

    public void initConterntView() {
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);
    }

    public FrameLayout getContentView() {
        return mContentView;
    }

    public Toolbar getToolBar(){
        return mToolBar;
    }

}
