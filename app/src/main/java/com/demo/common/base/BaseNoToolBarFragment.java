/*
 * Copyright (c) 2016. Naivor.All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo.common.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.demo.common.loading.VaryViewHelperController;
import com.demo.common.presenter.BasePresenter;
import com.demo.common.util.L;
import com.demo.common.widget.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseNoToolBarFragment extends Fragment implements BaseView {

//    protected FragmentComponent fragmentComponent;


    protected Toolbar  toolBar ;

    @Inject
    protected Context context;

    @Inject
    protected LayoutInflater inflater;
    protected FragmentComponent fragmentComponent;

    protected VaryViewHelperController mController;
    protected BaseAppCompatActivity    mActivity;

    BasePresenter mPresenter;

    protected boolean isCanShowLoading;
    private View mContentView;
    //加载框
    private   ProgressDialog    progressDialog;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = (BaseAppCompatActivity) context;
        fragmentComponent = getFragmentComponent();
        initInjector(fragmentComponent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d(this.getClass().getName(),"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        if(mContentView==null){
            mContentView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mContentView);
            if(null!= getLoadingTargetView()){
                mController = new VaryViewHelperController(getLoadingTargetView());
            }
            initView(mContentView);
        }else{
            ViewGroup parent = (ViewGroup)mContentView.getParent();
            if(parent!=null){
                parent.removeView(mContentView);
            }
        }
        L.d(this.getClass().getName(),"onCreateView");
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化Presenter
        mPresenter = getPresenter();
        if (mPresenter == null) {
            throw new NullPointerException(this.getClass().getName()+"中mPresenter 不能为 Null");
        } else {
            mPresenter.onCreate(savedInstanceState,context);
        }
        initData();
        L.d(this.getClass().getName(),"onViewCreated");
    }

    /**
     * 在onCreateView()中调用,无需获取网络数据初始化view
     * @param contentView
     */
    protected abstract void initView(View contentView);

    /**
     * 在onViewCreated()中调用,用于请求网络数据
     *
     */
    protected abstract void initData();




    protected abstract void initInjector(FragmentComponent fragmentComponent);


    protected abstract int getLayoutId();



    protected ApplicationComponent getApplicationComponent() {
        return ((DemoApplication)(mActivity. getApplication())).getComponent();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        L.d(this.getClass().getName(),"onActivityCreated");
    }


    public abstract BasePresenter getPresenter() ;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mPresenter.onSave(outState);
        L.d(this.getClass().getName(),"onSaveInstanceState");
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
        isCanShowLoading=false;
        L.d(this.getClass().getName(),"onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.d(this.getClass().getName(),"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();

        mPresenter =null;
        L.d(this.getClass().getName(),"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        L.d(this.getClass().getName(),"onDetach");
    }

    @Override
    public void showLoading() {

        if (progressDialog == null)
        {
            progressDialog = new ProgressDialog(mActivity);
            progressDialog.setMessage("正在加载中");
            progressDialog.setCancelable(true);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mPresenter.cancellAllRequest();
                }
            });
        }
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    @Override
    public void showEmpty(String msg) {
    }

    @Override
    public void loadingComplete(Object data,int recode) {
        if (progressDialog != null&&progressDialog.isShowing())
        {
            progressDialog.dismiss();
            progressDialog=null;
        }
    }

    @Override
    public void showNetError() {

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

    public FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder().applicationComponent(getApplicationComponent()).fragmentModule(new FragmentModule(this)).build();
    }

    public abstract View getLoadingTargetView();
}
