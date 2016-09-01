package com.demo.func.login.presenter;

import com.demo.common.presenter.BasePresenter;
import com.demo.func.login.model.LoginModelInterface;
import com.demo.func.login.model.LoginModelInterfaceImpl;

import javax.inject.Inject;

/**
 * @创建者 Piper
 * @创建时间 2016/6/29 0:10
 * @描述 TODO
 */
public class LoginPresentImpl extends BasePresenter<LoginContract.LoginView,LoginModelInterfaceImpl> implements LoginContract.LoginPresenter, LoginModelInterface.OnloginFinishListener {


    @Inject
    public LoginPresentImpl(LoginModelInterfaceImpl mLoginModel){
        super(mLoginModel);
    }

    @Override
    protected void onsuccess(Object data, int recode) {

    }

    @Override
    protected void onFailed(Object e, int recode) {

    }

    @Override
    protected void onFinish(int recode) {

    }

    @Override
    public int[] getObservableCode() {
        return null;
    }

    @Override
    public void validateCredentials(String userName, String passWord) {
            mUiView.hidIME();
            mUiView.showLoading();
            mBaseModel.login(userName,passWord,this);
    }

    @Override
    public void onUserNameError() {
        if(mUiView!=null){
            mUiView.setPassWordError();
        }
    }

    @Override
    public void onPassWordError() {
        if(mUiView!=null){
            mUiView.setPassWordError();
        }
    }

    @Override
    public void onSuccess() {
        if(mUiView!=null){
            mUiView.loadingComplete(new Object(),0);
            mUiView.jumpToHome();
        }
    }

    @Override
    public void onFailed(String str) {
        if(mUiView!=null){
            mUiView.setFailed();
        }
    }

    @Override
    public void onDestroy() {
        mUiView=null;
    }

}
