package com.demo.common.dagger.component;

import com.demo.common.dagger.module.ActivityModule;
import com.demo.common.dagger.scope.PerActivity;
import com.demo.func.home.ui.MainActivity;
import com.demo.func.login.ui.LoginActivity;

import dagger.Component;

/**
 * @创建者 Piper
 * @创建时间 2016/8/8 17:10
 * @描述 TODO
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);
    void inject(MainActivity mainActivity);
}
