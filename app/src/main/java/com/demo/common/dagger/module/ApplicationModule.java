package com.demo.common.dagger.module;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

import com.demo.DemoApplication;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import rx.subscriptions.CompositeSubscription;

/**
 * @创建者 Piper
 * @创建时间 2016/7/18 22:57
 * @描述 TODO
 */
@Module
public class ApplicationModule {
    private DemoApplication mDemoApplication;
    public ApplicationModule(DemoApplication application){
        mDemoApplication = application;
    }

    @Provides @Singleton
    public Context provideContext(){
        return mDemoApplication;
    }

    @Provides @Singleton
    public  Resources provideResources(){
        return mDemoApplication.getResources();
    }
    @Provides @Singleton
    public OkHttpClient provideApiOkHttpClient(){
        return new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.MILLISECONDS).readTimeout(10000,TimeUnit.MILLISECONDS).addInterceptor(new HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY)).build();
    }

    @Singleton
    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(mDemoApplication);
    }

    @Singleton
    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
