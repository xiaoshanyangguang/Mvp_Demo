package com.demo.common.dagger.component;

import com.demo.common.dagger.module.FragmentModule;
import com.demo.common.dagger.scope.PerFragment;
import com.demo.func.account.ui.InnerAccountFragment;
import com.demo.func.account.ui.WalletFragment;
import com.demo.func.home.ui.AccountFragment;
import com.demo.func.home.ui.AddressBookFragment;
import com.demo.func.home.ui.BuyWithFragment;
import com.demo.func.home.ui.HallFragment;
import com.demo.func.home.ui.NavigationFragment;

import dagger.Component;

/**
 * @创建者 piper
 * @创建时间 2016/8/22 0:03
 * @描述 TODO
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(NavigationFragment mNavigationFragment);
    void inject(AccountFragment mAccountFragment);
    void inject(BuyWithFragment mBuyWithFragment);
    void inject(AddressBookFragment mAddressBookFragment);
    void inject(HallFragment mHallFragment);
    void inject(InnerAccountFragment mHallFragment);
    void inject(WalletFragment mWalletFragment);
}
