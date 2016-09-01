package com.demo.func.home.ui;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.demo.R;
import com.demo.common.base.BaseNoToolbarActivity;
import com.demo.common.dagger.component.ActivityComponent;
import com.demo.common.presenter.BasePresenter;
import com.demo.func.home.Adapter.MainAdapter;
import com.demo.func.home.common.MainTab;
import com.demo.func.home.presenter.MainContract;
import com.demo.func.home.presenter.MainPresenter;
import com.demo.func.home.widget.MyFragmentTabHost;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * @创建者 Piper
 * @创建时间 2016/7/6 21:15
 * @描述 TODO
 */

public class MainActivity extends BaseNoToolbarActivity<MainPresenter> implements MainContract.MainView{




    NavigationFragment mNavigationFragment;

    @Bind(R.id.tabhost)
    MyFragmentTabHost mTab;

    @Inject
    MainPresenter mMainPresenter;


    private MainAdapter   mHomeAdapter;

    @Override
    protected void initInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initViews() {
//        mNavigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        //        mNavifragment.get
        mTab.setup(mContext,getSupportFragmentManager(),R.id.realtabcontent);
        initTab();
    }

    private void initTab() {
        MainTab[] values = MainTab.values();
        for (int i=0;i<values.length;i++){
            TabHost.TabSpec tabSpec = mTab.newTabSpec(getString(values[i].getResName()));
            View view = mInflater.inflate(R.layout.tab_indicator,null);
            TextView tab_title = (TextView) view.findViewById(R.id.tab_title);
            tab_title.setText(values[i].getResName());
            Drawable drawable = getResources().getDrawable(values[i].getResIcon());
            tab_title.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
            tabSpec.setIndicator(view);
            mTab.addTab(tabSpec,values[i].getClz(),null);
        }
    }

    @Override
    protected void initData() {
    }

    @Override
    protected BasePresenter getPresent() {
        return mMainPresenter;
    }


    @Override
    public void jumpToNext() {

    }



    @Override
    public void showEmpty(String msg) {

    }

    @Override
    public void showNetError() {

    }



}
