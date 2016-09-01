package com.demo.common.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @创建者 piper
 * @创建时间 2016/8/9 20:45
 * @描述 TODO
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
