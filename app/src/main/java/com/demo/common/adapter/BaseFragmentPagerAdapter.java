package com.demo.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.demo.common.dagger.scope.PerFragment;

import java.util.List;

import javax.inject.Inject;
@PerFragment
public class BaseFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<? extends Fragment> mFragments;

    private List<String> mTabs;

    @Inject
    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setData(List<Fragment> fragments,List<String> tabs){
        mFragments = fragments;
        mTabs = tabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(mTabs==null){
            super.getPageTitle(position);
        }
        return mTabs.get(position);
    }



}
