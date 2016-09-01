

package com.demo.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.DemoApplication;
import com.demo.common.dagger.component.ApplicationComponent;
import com.demo.common.dagger.component.DaggerFragmentComponent;
import com.demo.common.dagger.component.FragmentComponent;
import com.demo.common.dagger.module.FragmentModule;
import com.demo.common.helper.CustomToolbarHelper;
import com.demo.common.presenter.BasePresenter;
import com.demo.common.widget.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
/**
 *
 */
public abstract class BaseTooBarFragment extends Fragment implements BaseView {

//    protected FragmentComponent fragmentComponent;

    protected BaseToolbarHelper toolbarHelper;
    protected Toolbar             toolBar ;


    @Inject
    protected LayoutInflater inflater;

    @Inject
    Context mContext;

    protected BaseAppCompatActivity mActivity;

    protected BasePresenter presenter;

    protected boolean           isCanShowLoading;
    protected     FragmentComponent fragmentComponent;
    private View mContentView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity= (BaseAppCompatActivity) context;
        fragmentComponent = getFragmentComponent();
        initInjector(fragmentComponent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        if(mContentView==null){
            toolbarHelper = getToolbarHelper();
            toolBar = toolbarHelper.getToolBar();
            mContentView = toolbarHelper.getContentView();
            ButterKnife.bind(this, mContentView);
            initView(mContentView);
        }else{
            ViewGroup parent = (ViewGroup)mContentView.getParent();
            if(parent!=null){
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected abstract void initData();

    /**
     *
     */
    protected abstract void initView(View view);

    protected abstract void initInjector(FragmentComponent fragmentComponent);


    /**
     * 返回布局文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     *当toolbar修改时覆写此方法
     * @return
     */
    public BaseToolbarHelper getToolbarHelper(){
        return new CustomToolbarHelper(mContext, getLayoutId());
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化Presenter
        presenter = getPresenter();
        if (presenter == null) {
            throw new NullPointerException("mPresenter 不能为 Null");
        } else {
            presenter.onCreate(savedInstanceState,mContext);
        }

    }

    /**
     * 返回值不能为空
     * @return
     */
    public abstract BasePresenter getPresenter() ;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        presenter.onSave(outState);
    }
    protected ApplicationComponent getApplicationComponent() {
        return ((DemoApplication)(mActivity. getApplication())).getComponent();
    }
    public FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder().applicationComponent(getApplicationComponent()).fragmentModule(new FragmentModule(this)).build();
    }
    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
        isCanShowLoading=false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

        presenter=null;
    }

    @Override
    public void showLoading() {
        if (isCanShowLoading){
            mActivity.showLoading();
        }
    }

    @Override
    public void showEmpty(String msg) {
        mActivity.showEmpty(msg);
    }

    @Override
    public void showNetError() {
        mActivity.showNetError();
    }




    public void setToolbarHelper(CustomToolbarHelper toolbarHelper) {
        this.toolbarHelper = toolbarHelper;
    }

    /**
     * 获取view
     *
     * @param viewId
     * @return
     */
    public View find(View parent,int viewId) {
        return parent.findViewById(viewId);
    }

    public boolean isCanShowLoading() {
        return isCanShowLoading;
    }

    public void setIsCanShowLoading(boolean isCanShowLoading) {
        this.isCanShowLoading = isCanShowLoading;
    }
}
