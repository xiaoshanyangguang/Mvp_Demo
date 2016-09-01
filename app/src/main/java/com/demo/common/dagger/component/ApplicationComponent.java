package com.demo.common.dagger.component;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

import com.demo.common.dagger.module.ApiModule;
import com.demo.common.dagger.module.ApplicationModule;
import com.demo.common.manager.AppManager;
import com.demo.common.util.SpfManager;
import com.demo.func.home.model.forum.MainApi;

import javax.inject.Singleton;

import dagger.Component;
import rx.subscriptions.CompositeSubscription;

/**
 * @创建者 Piper
 * @创建时间 2016/7/11 23:21
 * @描述 TODO
 */
@Singleton
@Component(modules = {ApplicationModule.class,ApiModule.class})
public interface ApplicationComponent {
//    void inject(DemoApplication demoApplication);
//    void inject(BaseToolbarActivity baseToolbarActivity);
//    void inject(BaseNoToolbarActivity baseNoToolbarActivity);
    MainApi getMainApi();
    Context getContext();
    LayoutInflater getLayoutInflater();
    AppManager getAppManager();
    Resources getResources();
    SpfManager getSpfManager();
    CompositeSubscription getCompositeSubscription();


}
