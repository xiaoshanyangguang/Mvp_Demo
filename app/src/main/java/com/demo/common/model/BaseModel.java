package com.demo.common.model;

import com.demo.common.presenter.BasePresenter;

import rx.Observable;

/**
 * @创建者 Piper
 * @创建时间 2016/6/24 0:52
 * @描述 TODO
 */
public abstract class BaseModel implements BaseModelInterface {



    public abstract Observable getObservable(int recode, BasePresenter.RequestMode mode);










}
