package com.demo.func.home.ui;

import android.support.v4.app.Fragment;

import com.demo.R;
import com.demo.common.base.BaseViewpagerFragment;
import com.demo.common.dagger.component.FragmentComponent;
import com.demo.common.presenter.BasePresenter;
import com.demo.func.account.ui.InnerAccountFragment;
import com.demo.func.account.ui.WalletFragment;
import com.demo.func.home.presenter.AccountPresent;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * @创建者 piper
 * @创建时间 2016/8/22 23:10
 * @描述 TODO
 */
public class AddressBookFragment extends BaseViewpagerFragment {

    @Inject
    InnerAccountFragment mInnerAccountFragment;

    @Inject
    WalletFragment mWalletFragment;

    @Inject
    AccountPresent mWalletPresenter;

    @Override
    protected void initData(List<Fragment> fragmentList, List<String> stringList) {
        fragmentList.add(mInnerAccountFragment);
        fragmentList.add(mWalletFragment);
        String[] stringArray = getResources().getStringArray(R.array.account_tab);
        List<String> strings = Arrays.asList(stringArray);
        stringList.addAll(strings);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initInjector(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    public BasePresenter getPresenter() {
        return mWalletPresenter;
    }




}
