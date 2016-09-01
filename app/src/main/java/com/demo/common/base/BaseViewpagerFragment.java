package com.demo.common.base;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.demo.R;
import com.demo.common.adapter.BaseFragmentPagerAdapter;
import com.demo.common.util.UIUtils;
import com.demo.common.widget.PagerSlidingTabStrip;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * @创建者 piper
 * @创建时间 2016/8/22 23:43
 * @描述 TODO
 */
public abstract class BaseViewpagerFragment extends BaseNoToolBarFragment{

    @Inject
    List<Fragment> mFragmentList;

    @Inject
    List<String> mStringList;

    @Inject
    Resources mResources;

    @Inject
    BaseFragmentPagerAdapter mPagerAdapter;

    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    @Bind(R.id.pagerSlidingTabStrip)
    PagerSlidingTabStrip mPagerSlidingTabStrip;

    @Override
    protected void initView(View view) {
        initData(mFragmentList,mStringList);
        mPagerSlidingTabStrip.setShouldExpand(true);
        mPagerSlidingTabStrip.setIndicatorColor(mResources.getColor(R.color.gray));
        mPagerSlidingTabStrip.setIndicatorHeight(4);
        mPagerSlidingTabStrip.setBackgroundColor(mResources.getColor(R.color.red));
        mPagerSlidingTabStrip.setTextSize(UIUtils.sp2px(mActivity,16));
        mPagerSlidingTabStrip.setUnderlineHeight(20);
        mPagerSlidingTabStrip.setTextColor(mResources.getColor(R.color.gray));
        mPagerSlidingTabStrip.setTextSelectColor(mResources.getColor(R.color.white));
        mPagerSlidingTabStrip.setDivider(false);
        mPagerAdapter.setData(mFragmentList,mStringList);
        mViewPager.setAdapter(mPagerAdapter);
        mPagerSlidingTabStrip.setViewPager(mViewPager);
    }

    /**
     * 提供viewpager的fragment和tab字符串的集合
     * @param fragmentList
     * @param stringList
     */
    protected abstract void initData(List<Fragment> fragmentList, List<String> stringList);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_viewpager;
    }
    @Override
    public void hasNoMoreDate(Object hasNoMore) {

    }

    @Override
    public void loadMoreFinish(Object morefinish) {

    }

    @Override
    public void showRefreshFinish(Object refreshFinish) {

    }

    @Override
    public void showToastError(Object error) {

    }

    @Override
    public View getLoadingTargetView() {
        return null;
    }

    @Override
    public void loadingComplete(Object data,int recode) {

    }

}
