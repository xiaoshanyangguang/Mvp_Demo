package com.demo.func.login.presenter;

import com.demo.common.widget.BaseView;

/**
 * @创建者 Piper
 * @创建时间 2016/6/28 23:56
 * @描述 TODO
 */
public interface LoginContract {
    interface LoginPresenter  {
        void validateCredentials(String userName,String passWord);
    }

    interface LoginView extends BaseView{
        void setUserNameError();
        void setPassWordError();
        void setFailed();
        void jumpToHome();
        void hidIME();
    }
}
