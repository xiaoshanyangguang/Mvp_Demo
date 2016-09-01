package com.demo.func.home.ui;

import android.view.View;

import com.demo.R;
import com.demo.common.base.BaseNoToolBarFragment;
import com.demo.common.dagger.component.FragmentComponent;
import com.demo.common.presenter.BasePresenter;
import com.demo.func.home.model.NavigationModelInterfaceInter;
import com.demo.func.home.presenter.NavigationContract;
import com.demo.func.home.presenter.NavigationPresenter;

import javax.inject.Inject;

/**
 * @创建者 piper
 * @创建时间 2016/8/21 17:10
 * @描述 TODO
 */
public class NavigationFragment extends BaseNoToolBarFragment {

    @Inject
    NavigationPresenter mNavigationPresenter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View contentView) {

    }

    @Override
    protected void initInjector(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_drawer;
    }

    @Override
    public BasePresenter<NavigationContract.NavigationUiView, NavigationModelInterfaceInter> getPresenter() {
        return mNavigationPresenter;
    }

    @Override
    public View getLoadingTargetView() {
        return null;
    }

    @Override
    public void loadingComplete(Object data,int recode) {

    }


}
