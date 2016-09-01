package com.demo.func.login.model;

import android.text.TextUtils;

import com.demo.common.model.BaseModel;
import com.demo.common.presenter.BasePresenter;
import com.demo.common.util.UIUtils;

import javax.inject.Inject;

import rx.Observable;

/**
 * @创建者 Piper
 * @创建时间 2016/6/24 0:59
 * @描述 TODO
 */
public class LoginModelInterfaceImpl extends BaseModel implements LoginModelInterface {
    @Inject
    public LoginModelInterfaceImpl(){
        super();
    }

    @Override
    public void login(String userName, String passWord, final OnloginFinishListener listener) {
        if(TextUtils.isEmpty(userName)){
            listener.onUserNameError();
            return;
        }
        if(TextUtils.isEmpty(passWord)){
            listener.onPassWordError();
            return;
        }

            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(2000);
                        UIUtils.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onSuccess();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

    }

    @Override
    public Observable getObservable(int recode, BasePresenter.RequestMode mode) {
        return null;
    }

}
