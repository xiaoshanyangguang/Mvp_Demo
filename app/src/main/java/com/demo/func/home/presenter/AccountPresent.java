package com.demo.func.home.presenter;

import com.demo.common.dagger.scope.PerFragment;
import com.demo.common.presenter.BasePresenter;
import com.demo.func.account.model.AccountModel;
import com.demo.func.account.presenter.AccountContract;

import javax.inject.Inject;

/**
 * @创建者 piper
 * @创建时间 2016/8/31 23:13
 * @描述 TODO
 */
@PerFragment
public class AccountPresent extends BasePresenter<AccountContract.AccountView , AccountModel > {
    @Inject
    public AccountPresent(AccountModel baseModel) {
        super(baseModel);
    }

    @Override
    protected void onsuccess(Object data, int recode) {

    }

    @Override
    protected void onFailed(Object e, int recode) {

    }

    @Override
    protected void onFinish(int recode) {

    }

    @Override
    public int[] getObservableCode() {
        return null;
    }
}
