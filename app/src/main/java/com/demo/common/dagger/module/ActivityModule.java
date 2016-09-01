package com.demo.common.dagger.module;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.demo.common.dagger.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 Piper
 * @创建时间 2016/7/19 22:40
 * @描述 TODO
 */
@Module
public class ActivityModule {
    private AppCompatActivity mActivity;
    public ActivityModule(AppCompatActivity activity){
        mActivity = activity;
    }
    @Provides
    @PerActivity
    public Activity provideContext(){
        return mActivity;
    }
    @PerActivity
    @Provides
    FragmentManager provideFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

}
