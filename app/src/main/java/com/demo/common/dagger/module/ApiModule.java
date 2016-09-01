package com.demo.common.dagger.module;

import com.demo.func.home.model.forum.MainApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * @创建者 piper
 * @创建时间 2016/8/9 19:23
 * @描述 TODO
 */
@Module
public class ApiModule {
    @Singleton
    @Provides
    public MainApi provideMainApi(OkHttpClient okHttpClient){
        return new MainApi(okHttpClient);
    }
}
