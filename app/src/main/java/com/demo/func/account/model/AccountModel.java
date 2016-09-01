package com.demo.func.account.model;

import com.demo.common.dagger.scope.PerFragment;
import com.demo.common.model.BaseModel;

import javax.inject.Inject;

import rx.Observable;

/**
 * @创建者 piper
 * @创建时间 2016/8/23 13:00
 * @描述 TODO
 */
@PerFragment
public class AccountModel extends BaseModel {
    @Inject
    public AccountModel(){
    }
    @Override
    public Observable getObservable(int recode) {
        return null;
    }
}
