package com.demo.func.home.model;

import com.demo.common.model.BaseModel;

import javax.inject.Inject;

import rx.Observable;

/**
 * @创建者 piper
 * @创建时间 2016/8/21 22:41
 * @描述 TODO
 */
public class NavigationModelInterfaceInter extends BaseModel {

    @Inject
    public NavigationModelInterfaceInter(){}

    @Override
    public Observable getObservable(int recode) {
        return null;
    }
}

