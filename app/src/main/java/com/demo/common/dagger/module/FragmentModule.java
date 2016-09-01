package com.demo.common.dagger.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.demo.common.dagger.scope.PerFragment;
import com.demo.func.account.ui.InnerAccountFragment;
import com.demo.func.account.ui.WalletFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 piper
 * @创建时间 2016/8/22 16:27
 * @描述 TODO
 */
@Module
public class FragmentModule {
    public Fragment mFragment;
    public FragmentModule(Fragment fragment){
        mFragment = fragment;

    }

    @PerFragment
    @Provides
    public FragmentManager provideFragmentManager(){
        return  mFragment.getChildFragmentManager();
    }

    @PerFragment
    @Provides
    public WalletFragment provideWalletFragment(){
        return  new WalletFragment();
    }
    @PerFragment
    @Provides
    public InnerAccountFragment provideInnerAccountFragment(){
        return  new InnerAccountFragment();
    }
    @Provides
    @PerFragment
    public List<Fragment> provideListFragment() {
        return new ArrayList<Fragment>();
    }
    @Provides
    @PerFragment
    public List<String> provideListString() {
        return new ArrayList<String>();
    }
}
