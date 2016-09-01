package com.demo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.anupcowkur.reservoir.Reservoir;
import com.demo.common.config.Constant;
import com.demo.common.dagger.component.ApplicationComponent;
import com.demo.common.dagger.component.DaggerApplicationComponent;
import com.demo.common.dagger.module.ApplicationModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @创建者 Piper
 * @创建时间 2016/7/16 19:15
 * @描述 TODO
 */
public class DemoApplication extends Application {
    private        ApplicationComponent mComponent;
    private static Context              mContext;

    private static Thread  mMainThread;
    private static long    mMainThreadId;
    private static Looper  mMainLooper;
    private static Handler mMainHandler;
    public static final long ONE_KB = 1024L;
    public static final long ONE_MB = ONE_KB * 1024L;
    public static final long CACHE_DATA_MAX_SIZE = ONE_MB * 3L;
    public Gson gson;
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        // 主线程
        mMainThread = Thread.currentThread();

        // 主线程ID
        mMainThreadId = android.os.Process.myTid();

        mMainLooper = getMainLooper();

        // 主线程handler
        mMainHandler = new Handler();
        initComponent();
        injectApplication();
        initGson();

        initReservoir();
    }

    private void injectApplication() {
//        mComponent.inject(this);
    }

    private void initComponent(){
        mComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getComponent() {
        return ((DemoApplication)mContext.getApplicationContext()).mComponent;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static Handler getMainHandler() {
        return mMainHandler;
    }

    private void initGson() {
        this.gson = new GsonBuilder().setDateFormat(Constant.GANK_DATA_FORMAT).create();
    }
    private void initReservoir() {
        try {
            Reservoir.init(this, CACHE_DATA_MAX_SIZE, this.gson);
        } catch (Exception e) {
            //failure
            e.printStackTrace();
        }
    }
}
