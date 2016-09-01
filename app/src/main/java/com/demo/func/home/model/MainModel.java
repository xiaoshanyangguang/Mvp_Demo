package com.demo.func.home.model;

import com.demo.common.dagger.scope.PerActivity;
import com.demo.common.model.BaseModel;
import com.demo.common.presenter.BasePresenter;
import com.demo.func.home.common.EasyDate;
import com.demo.func.home.model.forum.MainApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * @创建者 Piper
 * @创建时间 2016/7/6 21:57
 * @描述 TODO
 */
@PerActivity
public class MainModel extends BaseModel implements MainModelInter {
    MainApi mMainApi;
    @Inject
    public MainModel(MainApi mainApi){
        mMainApi = mainApi;
    }

    @Override
    public EasyDate getEasyData() {
        return new EasyDate();
    }

    @Override
    public Observable getObservable(int recode, BasePresenter.RequestMode mode) {
        if(recode<0){
            throw new IllegalArgumentException("api接口的recode不能小于0");
        }

        throw new IllegalArgumentException("没有找到对应recode的api");
    }



}
