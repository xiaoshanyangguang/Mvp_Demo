package com.demo.func.home.presenter;

import com.demo.common.presenter.BasePresenter;
import com.demo.func.home.model.NavigationModelInterfaceInter;

import javax.inject.Inject;

/**
 * @创建者 piper
 * @创建时间 2016/8/21 22:36
 * @描述 TODO
 */
public class NavigationPresenter extends BasePresenter<NavigationContract.NavigationUiView,NavigationModelInterfaceInter> {

    @Inject
    public NavigationPresenter(NavigationModelInterfaceInter baseModel) {
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
