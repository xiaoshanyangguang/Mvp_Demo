package com.demo.common.base;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.demo.common.widget.BaseView;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by asi on 2016/5/26.
 * $desc$
 */
public abstract class BaseAppCompatActivity extends AutoLayoutActivity implements BaseView{
    //加载框
    private ProgressDialog    progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // setContentView()之前，初始化toolbar等操作
        initToolbar(savedInstanceState);
        ButterKnife.bind(this);
        // setContentView()之后，初始化view
        initViews();
        // setContentView()之后，初始化数据
        initData();
    }

    /**
     * Initialize the view in the layout
     *
     */
    protected abstract void initViews();

    /**
     * Initialize the toolbar in the layout
     * @param savedInstanceState
     */
    protected abstract void initToolbar(Bundle savedInstanceState);

    /**
     * Initialize the View of the listener
     */
//    protected abstract void initListeners();

    /**
     * Initialize the Activity data
     */
    protected abstract void initData();


    /**
     * @param cls 目标activity
     *            跳转并finish当前activity
     * @throws ActivityNotFoundException
     */
    public void skipActivity(Class<?> cls)
    {
        showActivity(cls);
        finish();
    }

    /***
     * @param it
     */
    public void skipActivity(Intent it)
    {
        super.startActivity(it);
        finish();
    }

    /***
     * @param it
     */
    public void showActivity(Intent it)
    {
        super.startActivity(it);
    }

    /**
     * @param cls
     * @param extras
     */
    public void skipActivity(Class<?> cls, Bundle extras)
    {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        super.startActivity(intent, extras);
        finish();
    }

    /**
     * @param cls
     */
    public void showActivity(Class<?> cls)
    {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        super.startActivity(intent);
    }

    /**
     * @param cls
     * @param extras
     */
    public void showActivity(Class<?> cls, Bundle extras)
    {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        super.startActivity(intent, extras);
    }

    /**
     * Call this when your activity is done and should be closed.  The
     * ActivityResult is propagated back to whoever launched you via
     * onActivityResult().
     */
    @Override
    public void finish()
    {
        super.finish();
    }
    /**
     * 隐藏软键盘
     */
    protected void hideIME() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
