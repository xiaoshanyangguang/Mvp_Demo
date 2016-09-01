package com.demo.common.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.demo.DemoApplication;
import com.demo.common.dagger.component.ActivityComponent;
import com.demo.common.dagger.component.ApplicationComponent;
import com.demo.common.dagger.component.DaggerActivityComponent;
import com.demo.common.dagger.module.ActivityModule;
import com.demo.common.manager.AppManager;
import com.demo.common.presenter.BasePresenter;
import com.demo.common.util.ToastUtil;

import javax.inject.Inject;

/**
 * @创建者 Piper
 * @创建时间 2016/6/21 23:32
 * @描述 TODO
 */
public abstract  class BaseNoToolbarActivity<T extends BasePresenter> extends BaseAppCompatActivity {

    @Inject
    T     mPresenter;
    //加载框
    private   ProgressDialog    progressDialog;
    protected ActivityComponent mActivityComponent;

    @Inject
    AppManager mAppManager;

    @Inject
    protected Context        mContext;
    @Inject
    protected LayoutInflater mInflater;

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        setContentView(getLayoutId());
        mActivityComponent = getActivityComponent();
//        initInjectApplicaionComponent();
        initInject(mActivityComponent);
        if(mPresenter==null){
            throw new NullPointerException("mPresenter 不能为空");
        }else{
            mPresenter.onCreate(savedInstanceState,mContext);
        }
    }

    protected  void initInjectApplicaionComponent(){
//        getApplicationComponent().inject(this);
    }


    protected abstract void initInject(ActivityComponent activityComponent);



    protected ApplicationComponent getApplicationComponent() {
        return ((DemoApplication) getApplication()).getComponent();
    }
    public  abstract  int getLayoutId() ;

    protected abstract BasePresenter getPresent();

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAppManager.finishActivity(this);
        mPresenter.onDestroy();
        mPresenter=null;
        ToastUtil.cancleToast();
    }

    @Override
    public void loadingComplete(Object object,int recode) {
        if (progressDialog != null&&progressDialog.isShowing())
        {
            progressDialog.dismiss();
            progressDialog=null;
        }
    }


    public void showLoading()
    {
        if (progressDialog == null)
        {
            progressDialog = new ProgressDialog(this);
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

    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder().applicationComponent(getApplicationComponent()).activityModule(new ActivityModule(this)).build();
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
}
